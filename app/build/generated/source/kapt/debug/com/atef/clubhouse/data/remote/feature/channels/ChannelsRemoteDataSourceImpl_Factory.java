package com.atef.clubhouse.data.remote.feature.channels;

import com.atef.clubhouse.data.remote.feature.channels.service.ChannelsApiHandler;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ChannelsRemoteDataSourceImpl_Factory implements Factory<ChannelsRemoteDataSourceImpl> {
  private final Provider<ChannelsApiHandler> apiProvider;

  public ChannelsRemoteDataSourceImpl_Factory(Provider<ChannelsApiHandler> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ChannelsRemoteDataSourceImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ChannelsRemoteDataSourceImpl_Factory create(
      Provider<ChannelsApiHandler> apiProvider) {
    return new ChannelsRemoteDataSourceImpl_Factory(apiProvider);
  }

  public static ChannelsRemoteDataSourceImpl newInstance(ChannelsApiHandler api) {
    return new ChannelsRemoteDataSourceImpl(api);
  }
}
