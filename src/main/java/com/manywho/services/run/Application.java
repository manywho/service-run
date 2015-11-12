package com.manywho.services.run;

import com.manywho.sdk.services.BaseApplication;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Application extends BaseApplication {
    public Application() {
        registerSdk()
                .packages("com.manywho.services.run")
                .register(new ApplicationBinder());
    }

    public static void main(String[] args) {
        startServer(new Application(), "api/run/1");
    }
}
