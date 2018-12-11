package com.amazonaws.serverless.tweetsentiment.dagger;

import com.amazonaws.serverless.tweetsentiment.TweetProcessor;
import com.amazonaws.serverless.tweetsentiment.lambda.TweetProcessorHandler;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * DI Module
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    public TweetProcessor provideTweetProcessor() {
        return new TweetProcessor();
    }
}
