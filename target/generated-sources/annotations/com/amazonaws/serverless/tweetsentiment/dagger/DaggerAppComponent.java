package com.amazonaws.serverless.tweetsentiment.dagger;

import com.amazonaws.serverless.tweetsentiment.TweetSentiment;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerAppComponent implements AppComponent {
  private Provider<TweetSentiment> provideTweetSentimentProvider;

  private DaggerAppComponent(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static AppComponent create() {
    return new Builder().build();
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.provideTweetSentimentProvider =
        DoubleCheck.provider(AppModule_ProvideTweetSentimentFactory.create(builder.appModule));
  }

  @Override
  public TweetSentiment tweetProcessor() {
    return provideTweetSentimentProvider.get();
  }

  public static final class Builder {
    private AppModule appModule;

    private Builder() {}

    public AppComponent build() {
      if (appModule == null) {
        this.appModule = new AppModule();
      }
      return new DaggerAppComponent(this);
    }

    public Builder appModule(AppModule appModule) {
      this.appModule = Preconditions.checkNotNull(appModule);
      return this;
    }
  }
}
