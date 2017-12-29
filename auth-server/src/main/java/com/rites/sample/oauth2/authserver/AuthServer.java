package com.rites.sample.oauth2.authserver;

import com.google.common.collect.Sets;
import com.rites.sample.oauth2.authserver.library.document.MongoAccessToken;
import com.rites.sample.oauth2.authserver.library.document.MongoAuthorizationCode;
import com.rites.sample.oauth2.authserver.library.document.MongoClientDetails;
import com.rites.sample.oauth2.authserver.library.document.MongoUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.authority.AuthorityUtils;

@SpringBootApplication
@ComponentScan("com.rites.sample.oauth2.authserver")
public class AuthServer {

    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(AuthServer.class, args);

        if (args .length > 0 && "init".equalsIgnoreCase(args[0])) {

            MongoTemplate mongoTemplate = (MongoTemplate) context.getBean(MongoTemplate.class);

            mongoTemplate.dropCollection(MongoUser.class);
            mongoTemplate.dropCollection(MongoClientDetails.class);
            mongoTemplate.dropCollection(MongoAccessToken.class);
            mongoTemplate.dropCollection(MongoAuthorizationCode.class);

            // init the users
            MongoUser mongoUser = new MongoUser();
            mongoUser.setUsername("user");
            mongoUser.setPassword("user");
            mongoUser.setRoles(Sets.newHashSet(("ROLE_USER")));
            mongoTemplate.save(mongoUser);

            // init the client details
            MongoClientDetails clientDetails = new MongoClientDetails();
            clientDetails.setClientId("web-client");
            clientDetails.setClientSecret("web-client-secret");
            clientDetails.setSecretRequired(true);
            clientDetails.setResourceIds(Sets.newHashSet("foo"));
            clientDetails.setScope(Sets.newHashSet("read-foo"));
            clientDetails.setAuthorizedGrantTypes(Sets.newHashSet("authorization_code", "refresh_token",
                    "password", "client_credentials"));
            clientDetails.setRegisteredRedirectUri(Sets.newHashSet("http://localhost:8082/resource-service"));
            clientDetails.setAuthorities(AuthorityUtils.createAuthorityList("ROLE_USER"));
            clientDetails.setAccessTokenValiditySeconds(60);
            clientDetails.setRefreshTokenValiditySeconds(14400);
            clientDetails.setAutoApprove(false);
            mongoTemplate.save(clientDetails);

        }
    }
}
