package com.manywho.services.run.services;

import com.manywho.sdk.RunService;
import com.manywho.sdk.entities.run.EngineInitializationResponse;
import com.manywho.sdk.entities.run.EngineInvokeRequest;
import com.manywho.sdk.entities.run.EngineInvokeResponse;
import com.manywho.sdk.entities.run.elements.map.MapElementInvokeRequest;
import com.manywho.sdk.entities.draw.flow.FlowResponse;
import com.manywho.sdk.entities.security.AuthenticatedWho;
import com.manywho.sdk.enums.InvokeType;
import com.manywho.sdk.enums.StatusCode;
import com.manywho.services.run.entities.EngineStartFlowRequest;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

public class FlowService {
    @Inject
    private RunService runService;

    public EngineInvokeResponse startFlow(EngineStartFlowRequest startFlowRequest, String tenantId) throws Exception {
        AuthenticatedWho authenticatedWho = null;

        if (startFlowRequest == null) {
            throw new Exception("The StartFlowRequest object cannot be null.");
        }

        if (tenantId == null || tenantId.isEmpty()) {
            throw new Exception("The TenantId cannot be null or blank.");
        }

        if (startFlowRequest.getFlowId() == null) {
            throw new Exception("The StartFlowRequest.FlowId parameter cannot be null.");
        }

        if (startFlowRequest.getFlowId().getId() == null || startFlowRequest.getFlowId().getId().isEmpty()) {
            throw new Exception("The StartFlowRequest.FlowId.Id parameter cannot be null or blank.");
        }

        if (startFlowRequest.getFlowId().getVersionId() == null || startFlowRequest.getFlowId().getVersionId().isEmpty()) {
            FlowResponse flowResponse = this.runService.loadFlow(null, null, tenantId, startFlowRequest.getFlowId().getId());

            if (flowResponse == null) {
                throw new Exception("A Flow could not be found for the provided identifier.");
            }

            startFlowRequest.setFlowId(flowResponse.getId());
        }

        EngineInitializationResponse engineInitializationResponse = this.runService.initializeFlow(null, null, tenantId, startFlowRequest);

        if (engineInitializationResponse.getStatusCode().equalsIgnoreCase(StatusCode.Unauthorized.toString())) {
            if (!startFlowRequest.hasAuthenticationCredentials()) {
                throw new Exception("The Flow is requesting authentication and credentials have not been provided.");
            }

            authenticatedWho = this.runService.login(null, tenantId, engineInitializationResponse.getStateId(), startFlowRequest.getAuthenticationCredentials());

            if (authenticatedWho == null) {
                throw new Exception("The authentication credentials provided did not login successfully for an unknown reason.");
            }

            if (authenticatedWho.getManyWhoUserId() == null || authenticatedWho.getManyWhoUserId().isEmpty()) {
                throw new Exception("The authentication credentials provided did not login successfully.");
            }

            // Send back the state identifier so we re-use the already created state, but this time authenticated
            startFlowRequest.setStateId(engineInitializationResponse.getStateId());

            // Re-initialize the flow with the authentication details
            engineInitializationResponse = this.runService.initializeFlow(null, authenticatedWho, tenantId, startFlowRequest);

            if (engineInitializationResponse.getStatusCode().equalsIgnoreCase(StatusCode.Unauthorized.toString())) {
                throw new Exception("The authentication credentials have an unknown problem. Authentication has failed on the second initialization.");
            }
        }

        // Need to do this slightly longer form as the engine initialization request is not final
        EngineInvokeRequest engineInvokeRequest = new EngineInvokeRequest();
        engineInvokeRequest.setCurrentMapElementId(engineInitializationResponse.getCurrentMapElementId());
        engineInvokeRequest.setInvokeType(InvokeType.Forward);
        engineInvokeRequest.setMapElementInvokeRequest(new MapElementInvokeRequest() {{
            setSelectedOutcomeId(null);
        }});
        engineInvokeRequest.setStateId(engineInitializationResponse.getStateId());
        engineInvokeRequest.setStateToken(engineInitializationResponse.getStateToken());

        return this.runService.executeFlow(null, authenticatedWho, tenantId, engineInvokeRequest);
    }
}
