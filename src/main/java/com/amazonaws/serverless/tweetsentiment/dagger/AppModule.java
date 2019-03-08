package com.amazonaws.serverless.tweetsentiment.dagger;

import com.amazonaws.serverless.tweetsentiment.Repository.TweetRepository;
import com.amazonaws.serverless.tweetsentiment.TweetSentiment;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
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
    public TweetSentiment provideTweetSentiment() {
        AmazonComprehend comprehend = AmazonComprehendClientBuilder.standard().build();
        AmazonCloudWatch cloudWatch = AmazonCloudWatchClientBuilder.standard().build();
        TweetRepository tweetRepository = new TweetRepository();
        return new TweetSentiment(comprehend, cloudWatch, tweetRepository);
    }
}
