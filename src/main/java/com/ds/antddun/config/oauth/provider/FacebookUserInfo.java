package com.ds.antddun.config.oauth.provider;

import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class FacebookUserInfo implements OAuth2UserInfo{

    private Map<String, Object> attributes; // getAttributes()

    //여기서 받음
    public FacebookUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;

    }
    @Override
    public String getProviderId() {
        return(String) attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "facebook";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }


    //이름 given_name
    @Override
    public String getFirstName() {
        String str = (String) attributes.get("name");
        String firstName;
        if (str.length() < 4) {
            firstName = str.substring(1);
        }  else {
            firstName = str.substring(2);
        }
        return firstName;
    }

    // 성 family_name
    @Override
    public String getLastName() {
        String str = (String) attributes.get("name");
        String lastName;
        if (str.length() < 4) {
            lastName = str.substring(0,1);
        }  else {
            lastName = str.substring(0,2);
        }
        return lastName;
    }

    @Override
    public String getMobile() {
        return null;
    }
}

