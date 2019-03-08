package com.amazonaws.serverless.tweetsentiment;

import com.amazonaws.serverless.tweetsentiment.dagger.AppComponent;
import com.amazonaws.serverless.tweetsentiment.dagger.DaggerAppComponent;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.AmazonComprehendClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.*;

import static org.junit.Assert.*;

@Slf4j
public class TweetSentimentTest {
    @BeforeClass
    public static void setLogger() throws MalformedURLException
    {
        System.setProperty("log4j.configurationFile","log4j2-test.xml");
        //LOGGER = LogManager.getLogger();
    }
//    @Test
//    public void publishSentimentMetrics() {
//        JSONObject tweet1 = new JSONObject("{\"id\":\"9393939393\",\"full_text\":\"test tweet 1\"}");
//        JSONObject tweet2 = new JSONObject("{\"id\":\"484848484\",\"full_text\":\"test tweet 2\"}");
//        List<JSONObject> tweets = new ArrayList<JSONObject>();
//        tweets.add(tweet1);
//        tweets.add(tweet2);
//
//
//        AppComponent component = DaggerAppComponent.create();
//        TweetSentiment tweetSentiment = component.tweetProcessor();
//        tweetSentiment.publishSentimentMetrics(tweets);
//
//    }
}