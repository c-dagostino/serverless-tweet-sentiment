package com.amazonaws.serverless.tweetsentiment.lambda;

import java.util.List;

import com.amazonaws.serverless.tweetsentiment.ReturnTweet;
import com.amazonaws.serverless.tweetsentiment.TweetSentiment;
import com.amazonaws.serverless.tweetsentiment.dagger.AppComponent;
import com.amazonaws.serverless.tweetsentiment.dagger.DaggerAppComponent;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;


/**
 * Lambda entrypoint.
 */
public class TweetSentimentHandler implements RequestHandler<List<String>, List<ReturnTweet>> {
    private final TweetSentiment tweetSentiment;

    public TweetSentimentHandler() {
        AppComponent component = DaggerAppComponent.create();
        tweetSentiment = component.tweetProcessor();
    }

    @Override
    public List<ReturnTweet> handleRequest(List<String> tweets, Context context) {

        List<ReturnTweet> returnTweets = tweetSentiment.publishSentimentMetrics(tweets);

        return returnTweets;
    }
}
