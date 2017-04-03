package com.manywho.services.run.entities;

import java.util.UUID;

public class EngineStartFlowSimpleRequest {

    private String id;
    private String versionId;
    private String username;
    private String password;
    private String player;

    public String getId() {
        return id;
    }

    public void String(String id) {
        this.id = id;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
