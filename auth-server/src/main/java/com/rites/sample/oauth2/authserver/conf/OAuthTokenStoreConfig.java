package com.rites.sample.oauth2.authserver.conf;

import com.rites.sample.oauth2.authserver.library.MongoTokenStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuthTokenStoreConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthTokenStoreConfig.class);

    @Bean
    @Profile("!jwttoken")
    public TokenStore tokenStore() {
        LOGGER.info("Initializing with Mongo token store ...");
        return new MongoTokenStore();
    }

    @Bean
    @Profile("jwttoken")
    public TokenStore jwtTokenStore() {
        LOGGER.info("Initializing with JWT token store ...");
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Profile("jwttoken")
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }
}
