package com.manywho.services.run;

import com.manywho.sdk.RunService;
import com.manywho.services.run.services.FlowService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(FlowService.class).to(FlowService.class);
        bind(RunService.class).to(RunService.class);
    }
}
