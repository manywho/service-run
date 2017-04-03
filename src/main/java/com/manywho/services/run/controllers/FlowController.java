package com.manywho.services.run.controllers;

import com.manywho.sdk.entities.draw.flow.FlowId;
import com.manywho.sdk.entities.draw.flow.FlowResponseCollection;
import com.manywho.sdk.entities.run.EngineInvokeResponse;
import com.manywho.sdk.entities.security.AuthenticationCredentials;
import com.manywho.sdk.enums.AuthorizationType;
import com.manywho.services.run.entities.EngineStartFlowRequest;
import com.manywho.services.run.entities.EngineStartFlowSimpleRequest;
import com.manywho.services.run.services.FlowService;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

@Path("/flow")
@Consumes("application/json")
@Produces("application/json")
public class FlowController {
    @Inject
    private FlowService flowService;

    @GET
    public FlowResponseCollection getFlows(@NotNull @HeaderParam("ManyWhoTenant") String tenantId, @QueryParam("filter") String filter) throws Exception {
        return this.flowService.getFlows(tenantId, filter);
    }

    @Path("/start")
    @POST
    public EngineInvokeResponse startFlow(@Valid EngineStartFlowRequest engineStartFlowRequest, @NotNull @HeaderParam("ManyWhoTenant") String tenantId) throws Exception {
        return this.flowService.startFlow(engineStartFlowRequest, tenantId);
    }

    @Path("/startsimple")
    @POST
    public EngineInvokeResponse startFlowSimple(@Valid EngineStartFlowSimpleRequest engineStartFlowSimpleRequest, @NotNull @HeaderParam("ManyWhoTenant") String tenantId) throws Exception {
        if (StringUtils.isBlank(engineStartFlowSimpleRequest.getPlayer())) {
            engineStartFlowSimpleRequest.setPlayer("default");
        }

        String url = String.format("https://flow.manywho.com/%s/play/%s", tenantId, engineStartFlowSimpleRequest.getPlayer());

        FlowId flowId = new FlowId();
        flowId.setId(engineStartFlowSimpleRequest.getId());
        flowId.setVersionId(engineStartFlowSimpleRequest.getVersionId());

        AuthenticationCredentials authenticationCredentials = null;
        if (StringUtils.isNotBlank(engineStartFlowSimpleRequest.getUsername()) && StringUtils.isNotBlank(engineStartFlowSimpleRequest.getPassword())) {
            authenticationCredentials = new AuthenticationCredentials();
            authenticationCredentials.setAuthenticationType(AuthorizationType.UsernamePassword);
            authenticationCredentials.setUsername(engineStartFlowSimpleRequest.getUsername());
            authenticationCredentials.setPassword(engineStartFlowSimpleRequest.getPassword());
        }

        EngineStartFlowRequest engineStartFlowRequest = new EngineStartFlowRequest();
        engineStartFlowRequest.setFlowId(flowId);
        engineStartFlowRequest.setPlayerUrl(url);
        engineStartFlowRequest.setJoinPlayerUrl(url);
        engineStartFlowRequest.setAuthenticationCredentials(authenticationCredentials);

        return this.flowService.startFlow(engineStartFlowRequest, tenantId);
    }
}
