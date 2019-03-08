package com.amazonaws.serverless.tweetsentiment;

import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

/**
 * Encapsulates parsing of tweet.
 */
public class Tweet {
    private final String rawTweet;
    private final Status status;
    private DetectSentimentResult detectSentimentResult;

    public Tweet(String tweetString) throws TwitterException {
        rawTweet = tweetString;
        status =TwitterObjectFactory.createStatus(tweetString);
    }

    public String getRawTweet() {
        return rawTweet;
    }

    public Status getStatus() {
        return status;
    }

    public DetectSentimentResult sentimentResult() {
        return this.detectSentimentResult;
    }

    public void setsentimentResult(DetectSentimentResult detectSentimentResult) {
        this.detectSentimentResult = detectSentimentResult;
    }
}