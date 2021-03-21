package com.atef.clubhouse.data.remote.feature.channels.mapper;

import com.atef.clubhouse.data.remote.feature.auth.mapper.ProfileMapper;
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
public final class ChannelMapper_Factory implements Factory<ChannelMapper> {
  private final Provider<ProfileMapper> listMapperProvider;

  public ChannelMapper_Factory(Provider<ProfileMapper> listMapperProvider) {
    this.listMapperProvider = listMapperProvider;
  }

  @Override
  public ChannelMapper get() {
    return newInstance(listMapperProvider.get());
  }

  public static ChannelMapper_Factory create(Provider<ProfileMapper> listMapperProvider) {
    return new ChannelMapper_Factory(listMapperProvider);
  }

  public static ChannelMapper newInstance(ProfileMapper listMapper) {
    return new ChannelMapper(listMapper);
  }
}
