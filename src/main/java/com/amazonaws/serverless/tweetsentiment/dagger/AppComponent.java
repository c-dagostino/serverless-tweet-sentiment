package com.amazonaws.serverless.tweetsentiment.dagger;

import com.amazonaws.serverless.tweetsentiment.TweetSentiment;
import dagger.Component;
import dagger.Module;

import javax.inject.Singleton;

/**
 * Dagger will create a class at runtime that implements this interface
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    TweetSentiment tweetProcessor();
}
