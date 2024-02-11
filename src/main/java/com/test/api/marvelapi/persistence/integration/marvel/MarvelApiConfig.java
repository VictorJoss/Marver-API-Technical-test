package com.test.api.marvelapi.persistence.integration.marvel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelApiConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;

    private long timestamp = new Date(System.currentTimeMillis()).getTime();
    @Value("${marvel.api.public-key}")
    private String publicKey;
    @Value("${marvel.api.private-key}")
    private String privateKey;

    private String getHash(){
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);

        return md5Encoder.encode(hashDecoded);
    }

    public Map<String, String>getAuthenticationQueryParams(){
        Map<String, String> securityQueryParams = new HashMap<>();

        securityQueryParams.put("ts", Long.toString(timestamp));
        securityQueryParams.put("apikey", publicKey);
        securityQueryParams.put("hash", this.getHash());

        return securityQueryParams;
    }
}
