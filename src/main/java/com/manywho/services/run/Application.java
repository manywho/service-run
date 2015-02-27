package com.manywho.services.run;

import com.manywho.sdk.services.ObjectMapperProvider;
import com.manywho.sdk.services.listeners.ReflectionListener;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class Application extends ResourceConfig {
    public Application() {
        packages("com.manywho.sdk.services", "com.manywho.services.run")
                .register(new ApplicationBinder())
                .register(ReflectionListener.class)
                .register(LoggingFilter.class)
                .register(ObjectMapperProvider.class);
    }
}
