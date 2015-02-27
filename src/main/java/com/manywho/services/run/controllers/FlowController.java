package com.manywho.services.run.controllers;

import com.manywho.sdk.entities.draw.flow.FlowResponseCollection;
import com.manywho.sdk.entities.run.EngineInvokeResponse;
import com.manywho.services.run.entities.EngineStartFlowRequest;
import com.manywho.services.run.services.FlowService;

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
}
