package com.tutofinder.customer.config.security;

import com.tutofinder.customer.entities.User;
import com.tutofinder.customer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInformation implements TokenEnhancer {
    @Autowired
    private UserService userService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        Map<String,Object> info = new HashMap<>();
        info.put("aditional_info","Welcome : ".concat(authentication.getName()));
        info.put("Username :" , user.getUsername());
        info.put("Email :" , user.getEmail());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
