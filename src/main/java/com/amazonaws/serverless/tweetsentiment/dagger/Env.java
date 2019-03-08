package com.amazonaws.serverless.tweetsentiment.dagger;

/**
 * Environment Variable Helpers
 */
public final class Env {
    private static final String MY_VAR_KEY = "MY_VAR";

    private Env() {

    }

    public static int myVar() {
        return Integer.parseInt(System.getenv(MY_VAR_KEY));
    }
}
