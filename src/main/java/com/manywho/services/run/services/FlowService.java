package com.manywho.services.run.services;

import com.manywho.sdk.RunService;
import com.manywho.sdk.entities.draw.flow.FlowId;
import com.manywho.sdk.entities.run.EngineInitializationRequest;
import com.manywho.sdk.entities.run.EngineInitializationResponse;
import com.manywho.sdk.entities.run.EngineInvokeRequest;
import com.manywho.sdk.entities.run.EngineInvokeResponse;
import com.manywho.sdk.entities.run.elements.map.MapElementInvokeRequest;
import com.manywho.sdk.entities.security.AuthenticatedWho;
import com.manywho.sdk.enums.InvokeType;
import com.manywho.services.run.entities.EngineStartFlowRequest;

import javax.inject.Inject;

public class FlowService {
    @Inject
    private RunService runService;

    public EngineInvokeResponse startFlow(EngineStartFlowRequest startFlowRequest, String tenantId) throws Exception {
        EngineInitializationResponse engineInitializationResponse = this.runService.initializeFlow(null, null, tenantId, new EngineInitializationRequest() {{
            setFlowId(startFlowRequest.getFlowId());
        }});

        AuthenticatedWho authenticatedWho = null;
        if (startFlowRequest.hasAuthenticationCredentials()) {
            authenticatedWho = this.runService.login(null, tenantId, engineInitializationResponse.getStateId(), startFlowRequest.getAuthenticationCredentials());
        }

        return this.runService.executeFlow(null, authenticatedWho, tenantId, new EngineInvokeRequest() {{
            setCurrentMapElementId(engineInitializationResponse.getCurrentMapElementId());
            setInvokeType(InvokeType.Forward);
            setMapElementInvokeRequest(new MapElementInvokeRequest() {{
                setSelectedOutcomeId(null);
            }});
            setStateId(engineInitializationResponse.getStateId());
            setStateToken(engineInitializationResponse.getStateToken());
        }});
    }
}
