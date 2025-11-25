package com.test.oauth2.dto;

import java.util.Map;

public class GoogleResponse {
    private final Map<String,Object> attribute;

    public GoogleResponse(Map<String,Object> attribute) {
        this.attribute = attribute;
    }

    //제공자
    public String getProvider() {
        return "google";
    }

    //제공자에서 발급하는 아이디(번호)
    public String getProviderId() {
        return attribute.get("sub").toString();
    }

    public String getEmail() {
        return attribute.get("email").toString();
    }

    //사용자명
    public String getName() {
        return attribute.get("name").toString();
    }
}
