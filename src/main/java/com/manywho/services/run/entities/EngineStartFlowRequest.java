package com.manywho.services.run.entities;

import com.manywho.sdk.entities.run.EngineInitializationRequest;
import com.manywho.sdk.entities.security.AuthenticationCredentials;

public class EngineStartFlowRequest extends EngineInitializationRequest {
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
