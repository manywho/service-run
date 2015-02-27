package com.manywho.services.run.entities;

import com.manywho.sdk.entities.run.EngineInitializationRequest;
import com.manywho.sdk.entities.run.Request;
import com.manywho.sdk.entities.security.AuthenticationCredentials;

import javax.validation.constraints.NotNull;

public class EngineStartFlowRequest extends EngineInitializationRequest implements Request {
    @NotNull
    private AuthenticationCredentials authenticationCredentials;

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
