package com.manywho.services.run.entities;

import com.manywho.sdk.entities.draw.flow.FlowId;
import com.manywho.sdk.entities.run.EngineValueCollection;
import com.manywho.sdk.entities.run.Request;
import com.manywho.sdk.entities.security.AuthenticationCredentials;
import org.apache.commons.collections4.CollectionUtils;

public class EngineStartFlowRequest implements Request {
    private FlowId flowId;
    private EngineValueCollection inputs;
    private AuthenticationCredentials authenticationCredentials;

    public FlowId getFlowId() {
        return flowId;
    }

    public void setFlowId(FlowId flowId) {
        this.flowId = flowId;
    }

    public EngineValueCollection getInputs() {
        return inputs;
    }

    public boolean hasInputs() {
        return CollectionUtils.isNotEmpty(inputs);
    }

    public void setInputs(EngineValueCollection inputs) {
        this.inputs = inputs;
    }

    public AuthenticationCredentials getAuthenticationCredentials() {
        return authenticationCredentials;
    }

    public boolean hasAuthenticationCredentials() {
        return authenticationCredentials != null;
    }

    public void setAuthenticationCredentials(AuthenticationCredentials authenticationCredentials) {
        this.authenticationCredentials = authenticationCredentials;
    }
}
