package com.sunflower.onlinetest.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("rest")
public class AppConfig extends Application {

    public static final String SECRET_KEY = "this is my secret key";

    public static final int TIME_TO_LIVE = 100 * 60 * 1000;

    public static final String ISSUER = "Sunflower";
}
