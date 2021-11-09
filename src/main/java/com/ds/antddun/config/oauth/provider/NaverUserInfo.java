package com.ds.antddun.config.oauth.provider;

import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
public class NaverUserInfo implements OAuth2UserInfo{


    private Map<String, Object> attributes; // getAttributes()


    //{id=1234, email=danbi@com, mobile=1234, name=박단비}
    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return(String) attributes.get("id");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }


    //이름/ given_name
    @Override
    public String getFirstName() {
        String str = (String) attributes.get("name");
        String firstName;
        if (str.length() < 4) {
            firstName = str.substring(1);
        }  else {
            firstName = str.substring(2);
        }
        log.info(">>>>>>>>",firstName);
        return firstName;
    }

    // 성/family_name
    @Override
    public String getLastName() {
        String str = (String) attributes.get("name");
        String lastName;
        if (str.length() < 4) {
            lastName = str.substring(0,1);
        }  else {
            lastName = str.substring(0,2);
        }
        log.info(">>>>>>>>",lastName);
        return lastName;
    }


    @Override
    public String getMobile() {
        String mobile = (String) attributes.get("mobile");
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        mobile = mobile.replaceAll(match, "");
        return mobile;
    }

}

