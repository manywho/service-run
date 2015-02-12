package com.manywho.services.run.entities;

import com.manywho.sdk.entities.draw.flow.FlowId;
import com.manywho.sdk.entities.run.Request;
import com.manywho.sdk.entities.security.AuthenticationCredentials;

public class EngineStartFlowRequest implements Request {
    private FlowId flowId;
    private AuthenticationCredentials authenticationCredentials;

    public FlowId getFlowId() {
        return flowId;
    }

    public void setFlowId(FlowId flowId) {
        this.flowId = flowId;
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
