package com.amazonaws.serverless.tweetsentiment.lambda;

import com.amazonaws.serverless.tweetsentiment.TweetProcessor;
import com.amazonaws.serverless.tweetsentiment.dagger.AppComponent;
import com.amazonaws.serverless.tweetsentiment.dagger.DaggerAppComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.List;

/**
 * Entrypoint for twee processor lambda
 */

public class TweetProcessorHandler implements RequestHandler<List<String>, Void>{
    private final TweetProcessor tweetProcessor;


    public TweetProcessorHandler() {
        //Dagger creates class at runtime for AppComponent Interface called DaggerAppComponent
        AppComponent component = DaggerAppComponent.create();
        tweetProcessor = component.tweetProcessor();
    }


    @Override
    public Void handleRequest(List<String> tweets, Context context) {
        //call tweet processor
        tweetProcessor.process(tweets);
        return null;
    }


}
