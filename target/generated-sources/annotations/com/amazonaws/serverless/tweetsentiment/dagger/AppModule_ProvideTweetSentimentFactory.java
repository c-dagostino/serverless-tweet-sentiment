package com.amazonaws.serverless.tweetsentiment.dagger;

import com.amazonaws.serverless.tweetsentiment.TweetSentiment;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideTweetSentimentFactory implements Factory<TweetSentiment> {
  private final AppModule module;

  public AppModule_ProvideTweetSentimentFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public TweetSentiment get() {
    return Preconditions.checkNotNull(
        module.provideTweetSentiment(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static AppModule_ProvideTweetSentimentFactory create(AppModule module) {
    return new AppModule_ProvideTweetSentimentFactory(module);
  }

  public static TweetSentiment proxyProvideTweetSentiment(AppModule instance) {
    return Preconditions.checkNotNull(
        instance.provideTweetSentiment(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
