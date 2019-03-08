package com.amazonaws.serverless.tweetsentiment.Repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.serverless.tweetsentiment.Tweet;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import org.joda.time.DateTime;
import twitter4j.Status;

public class TweetRepository {
    private AmazonDynamoDB client;
    private String DYNAMODB_TABLE_NAME = "tweets";

    public TweetRepository() {
        this.client = AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public PutItemOutcome saveTweet(Tweet tweet)
            throws ConditionalCheckFailedException {
        DynamoDB dynamoDb = new DynamoDB(client);
        return dynamoDb.getTable(DYNAMODB_TABLE_NAME)
                .putItem(
                        new PutItemSpec().withItem(new Item()
                                .withPrimaryKey("id", tweet.getStatus().getId())
                                .with("created_at", tweet.getStatus().getCreatedAt().toString())
                                .with("user_id", tweet.getStatus().getUser().getId())
                                .with("user_screen_name", tweet.getStatus().getUser().getScreenName())
                                .with("full_text", tweet.getStatus().getText())
                                .with("raw_tweet", tweet.getRawTweet())
                                .with("sentiment", tweet.sentimentResult().getSentiment())
                        )
                );
    }
}
