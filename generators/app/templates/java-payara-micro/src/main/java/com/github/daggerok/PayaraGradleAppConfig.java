package com.github.daggerok;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("v1")
public class PayaraGradleAppConfig extends Application { }
