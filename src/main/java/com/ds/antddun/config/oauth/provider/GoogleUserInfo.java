package com.ds.antddun.config.oauth.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes;

    //여기서 받음
    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;

    }
    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("given_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("family_name");
    }

    @Override
    public String getMobile() {
        return null;
    }
}
