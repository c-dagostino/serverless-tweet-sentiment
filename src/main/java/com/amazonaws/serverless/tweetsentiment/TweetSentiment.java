package com.amazonaws.serverless.tweetsentiment;

import java.util.*;
import com.amazonaws.serverless.tweetsentiment.Repository.TweetRepository;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.*;

import com.google.gson.Gson;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Core tweet sentiment logic.
 */
@Slf4j
@RequiredArgsConstructor
public class TweetSentiment {
    @NonNull
    private final AmazonComprehend comprehend;
    @NonNull
    private final AmazonCloudWatch cloudWatch;
    @NonNull
    private final TweetRepository tweetRepository;

    public List<ReturnTweet> publishSentimentMetrics(List<String> input) {


        List<Tweet> tweets = makeTweetsFromJson(input);
        this.populateStatusesWithSentiment(tweets);
        // this.saveTweetsWithSentiment(tweets);

        List<ReturnTweet> returnTweets = makeReturnTweets(tweets);
        return returnTweets;
        //return converReturnTweetsToJson(returnTweets);

//        List<MetricDatum> metrics = toMetricData(statuses);
//       Lists.partition(metrics, 20).forEach(this::putMetricData);
    }

    private List<String> converReturnTweetsToJson(List<ReturnTweet> returnTweets) {
        List<String> tweets = new ArrayList<>();
        returnTweets.stream().forEach(rt ->
                {
                    tweets.add(new Gson().toJson(rt).replace("\\", ""));
                }
        );

                    return tweets;
    }

    private List<ReturnTweet> makeReturnTweets(List<Tweet> tweets) {
        List<ReturnTweet> returnTweets = new ArrayList<>();
        tweets.stream().forEach(t -> {
            ReturnTweet returnTweet = new ReturnTweet();
            returnTweet.id = t.getStatus().getId();
            returnTweet.createdAt = t.getStatus().getCreatedAt();
            returnTweet.fullText = t.getStatus().getText();
            returnTweet.username = t.getStatus().getUser().getScreenName();
            returnTweet.name = t.getStatus().getUser().getName();
            returnTweet.sentiment = t.sentimentResult().getSentiment();
            returnTweets.add(returnTweet);
        });

        return returnTweets;
    }



    private List<Tweet> makeTweetsFromJson(List<String> tweetsAsStrings) {
        List<Tweet> tweets = new ArrayList<Tweet>();


        tweetsAsStrings.stream().forEach(t -> {
                try {
                    tweets.add(new Tweet(t));
                }
                catch (Exception e) {
                    log.error(e.toString());
                }
            }

            );

        return tweets;
    }


    private void populateStatusesWithSentiment(List<Tweet> tweets) {

        tweets.stream().forEach(t ->
                {
                    DetectSentimentResult detectSentimentResult = comprehend.detectSentiment(new DetectSentimentRequest()
                            .withLanguageCode("en")
                            .withText(t.getStatus().getText())
                    );
                    t.setsentimentResult(detectSentimentResult);
                }

        );

    }

    private void saveTweetsWithSentiment(List<Tweet> statuses) {

        statuses.stream().forEach(t ->
                {

                    tweetRepository.saveTweet(t);
                }

        );

    }


    //    private void putMetricData(List<MetricDatum> metricData) {
//        cloudWatch.putMetricData(new PutMetricDataRequest()
//                .withNamespace("TweetSentiment")
//                .withMetricData(metricData));
//        metricData.stream().forEach(item -> log.info(item.toString()));
//    }
//    private List<MetricDatum> toMetricData(List<Status> tweets) {
//
//
//        List<String> tweetText = tweets.stream()
//                .map(Status::getText)
//                .collect(Collectors.toList());
//
//        BatchDetectSentimentResult result = comprehend.batchDetectSentiment(new BatchDetectSentimentRequest()
//                .withLanguageCode("en")
//                .withTextList(tweetText));
//
//        return result.getResultList().stream()
//                .map(this::toSentimentMetrics)
//                .flatMap(List::stream)
//                .collect(Collectors.toList());
//
//    }
//
//    private List<MetricDatum> toSentimentMetrics(BatchDetectSentimentItemResult sentimentItemResult) {
//        List<MetricDatum> metrics = new ArrayList<>();
//        SentimentScore sentimentScore = sentimentItemResult.getSentimentScore();
//
//        metrics.add(toSentimentMetricDatum("Mixed", sentimentScore.getMixed().doubleValue()));
//        metrics.add(toSentimentMetricDatum("Negative", sentimentScore.getNegative().doubleValue()));
//        metrics.add(toSentimentMetricDatum("Neutral", sentimentScore.getNeutral().doubleValue()));
//        metrics.add(toSentimentMetricDatum("Positive", sentimentScore.getPositive().doubleValue()));
//
//        log.info("metric mixed: {}", toSentimentMetricDatum("Mixed", sentimentScore.getMixed().doubleValue()));
//        log.info("metric negative: {}", toSentimentMetricDatum("Negative", sentimentScore.getNegative().doubleValue()));
//        log.info("metric neutral: {}", toSentimentMetricDatum("Neutral", sentimentScore.().doubleValue()));
//        log.info("metric positive: {}", toSentimentMetricDatum("Positive", sentimentScore.getPositive().doubleValue()));
//        return metrics;
//    }
//
//
//
//    private MetricDatum toSentimentMetricDatum(String sentimentType, Double value) {
//        return new MetricDatum()
//                .withMetricName("SentimentScore")
//                .withDimensions(new Dimension()
//                        .withName("SentimentType")
//                        .withValue(sentimentType))
//                .withValue(value);
//    }


}
