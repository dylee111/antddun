package com.ds.antddun.config.oauth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; // getAttributes()

    //여기서 받음
    public FacebookUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;

    }

    @Override
    public Long getProviderId() {
        return (Long) attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }



    @Override
    public String getFirstName() {
        String firstName = (String) attributes.get("name");
        if (firstName.length() < 4) {
            return firstName.substring(1,2);
        }  else {
            return firstName.substring(2);
        }
    }

    @Override
    public String getLastName() {
        String lastName = (String) attributes.get("name");
        if (lastName.length() < 4) {
            return lastName.substring(0,0);
        }  else {
            return lastName.substring(0,1);
        }
    }
}

