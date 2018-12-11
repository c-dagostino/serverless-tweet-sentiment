package com.amazonaws.serverless.tweetsentiment.dagger;

import com.amazonaws.serverless.tweetsentiment.TweetProcessor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class AppModule_ProvideTweetProcessorFactory implements Factory<TweetProcessor> {
  private final AppModule module;

  public AppModule_ProvideTweetProcessorFactory(AppModule module) {
    this.module = module;
  }

  @Override
  public TweetProcessor get() {
    return Preconditions.checkNotNull(
        module.provideTweetProcessor(), "Cannot return null from a non-@Nullable @Provides method");
  }

  public static AppModule_ProvideTweetProcessorFactory create(AppModule module) {
    return new AppModule_ProvideTweetProcessorFactory(module);
  }

  public static TweetProcessor proxyProvideTweetProcessor(AppModule instance) {
    return Preconditions.checkNotNull(
        instance.provideTweetProcessor(),
        "Cannot return null from a non-@Nullable @Provides method");
  }
}
