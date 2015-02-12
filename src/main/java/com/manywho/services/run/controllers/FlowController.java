package com.manywho.services.run.controllers;

import com.manywho.sdk.entities.run.EngineInvokeResponse;
import com.manywho.services.run.entities.EngineStartFlowRequest;
import com.manywho.services.run.services.FlowService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("/flow")
@Consumes("application/json")
@Produces("application/json")
public class FlowController {
    @Inject
    private FlowService flowService;

    @Path("/start")
    @POST
    public EngineInvokeResponse startFlow(EngineStartFlowRequest engineStartFlowRequest, @HeaderParam("ManyWhoTenant") String tenantId) throws Exception {
        return this.flowService.startFlow(engineStartFlowRequest, tenantId);
    }
}
