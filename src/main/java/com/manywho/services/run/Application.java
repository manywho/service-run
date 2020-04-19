package com.manywho.services.run;

import com.manywho.sdk.services.BaseApplication;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Application extends BaseApplication {
    static {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    public Application() {
        registerSdk()
                .packages("com.manywho.services.run")
                .register(new ApplicationBinder());
    }

    public static void main(String[] args) {
        startServer(new Application());
    }
}
