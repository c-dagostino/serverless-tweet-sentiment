package com.amazonaws.serverless.tweetsentiment;

import java.util.List;
import lombok.extern.slf4j.Slf4j;



/**
 *  Process tweets and get a score from comprehend and publish sentiment scores to
 *  Cloudwatch Metrics
 */

/**
 * lombok magic creates log object
 */
@Slf4j
public class TweetProcessor {
    public void process(List<String> tweets) {
        log.info(tweets.toString());
    }
}
