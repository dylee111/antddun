package com.ds.antddun.config.oauth.provider;

public interface OAuth2UserInfo {
    Long getProviderId();
    String getProvider();
    String getEmail();
    String getName();
    String getFirstName();
    String getLastName();
}
