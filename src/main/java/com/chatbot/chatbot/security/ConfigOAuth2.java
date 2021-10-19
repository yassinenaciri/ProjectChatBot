package com.chatbot.chatbot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{

    private String clientId = "pixeltrice";
    private String clientSecret = "pixeltrice-secret-key";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpQIBAAKCAQEAuYtGVC5oOfs3s0hd8BXu1c1yV9pWnBVch5uCjqqqlAOnviET\n" +
            "2CLNECDByc6KQg3YZ8sRfmUJ1HI2pdCWSI3gQj9gto0oTTJ7Mjhxkk/up3r3BQEQ\n" +
            "XKi9n/4kF/oW0b72HOq4CJvlRLjMcG8LGYWTO8SUj8ca+Lj//5TZ9kJer2gbE0Fy\n" +
            "32aS3LgBag4kREbu9pSrZ+U1k1aO1einYB/qxBBtMQM2pIxDyyn6zgaVrPtixhoo\n" +
            "2c1f3TeWm8TT8IAMSq8hXNpMFcTxtOlejfelQ/lGlw48E4aGY+mv1dZ9SgXdoE1b\n" +
            "f4yfUEgAVjc2roP0OJgjkJmHZ++3cv4yifs+JQIDAQABAoIBAA+DBEsvYglO5lY3\n" +
            "u5rByqjXNzL/DvSXmJfBuYWB4Sbks1Zi34zXQ/uQMYNUoBxrmeb7hCfj/JFlRwpP\n" +
            "yPCh49jGAajhXjgt+zy0mVIZGqOn1RT3BM4qUUgZ8ByNDPFWHK9INECL//vh0z2n\n" +
            "Gu90Sl3Zzm3CwI+Ar+5HHH3JCK9zSSctbzC5fzrnWDvUJK+/C1n0Mriun+Zfgzqq\n" +
            "A6atM3o+Y2Q4TUq6cZgunOXQIhJ3ityTi6yNA8S6gSMtyaULt2IFtUeQDz/Qcfq0\n" +
            "oJUHYd0Hw4saUevbq21XCQjVymjOV20x2Mekjgvw+qbzZkGjairsD+Nx6iApe/wo\n" +
            "BgsoxhUCgYEA5xf3ghWA4JFIgkR5S4kmiSKDOts/DNev7sS41o69Mqi5nU4y/B2U\n" +
            "2Mqg1GMhTE9vTc2PZF0uHKuctmoB9yDZWgRAH1DUy62SJ/gbkeVyagDF7lXW6fIT\n" +
            "tMdejxb1HIPmGHHxbPIuQ+rFkmc/JfDNGXrkrLleteOxDnd+M63dYDsCgYEAzYqQ\n" +
            "PifrlfYXRo5djFe19M2wIQfv6Ky6CMRchASyAJn3jyUg/YEjarFISytZinhucZJu\n" +
            "cPCm6QdalwokkgOXWdbOW3a5EfUttGyk5xLFqzGObfDbgd/GHTXSpQbj18xlkJd5\n" +
            "mklGurCj5xd55rwMLmhfXsEXg+eZEFo1di8bVR8CgYEAkHv0nNly9XUAX6oOKEL8\n" +
            "2/q/D7pqhk4ISoGmi0c9VYIWYxq0V9rq6ExwF7pO5FY54ewmvfpgTT6iwY4v4mIg\n" +
            "Yt6LdMjl8t53phPCsQpC6bHIDsaOk0BuP7XzEgWphIWXBCuhZZtZf882bd2tprwS\n" +
            "j969QJy5cYGFfuUI+6i142cCgYEAqmMLX/tH2HDAqsSp2AV92QoJz9f8kS1eqpXc\n" +
            "+7PIu6bnCcsFpiC/c1qzS10MRTXAmHWDCCz2sIZgGoVV+5TuipL/TeMFWFWfJcpK\n" +
            "iSOOqXPTVmAsF2WhneVYUWV8yw+twkdPjw7fizOTK6bSbuvETVRIewwmqjn7MQsW\n" +
            "9nnmdpECgYEAqSKQLMawO0nJwOVEPcwz+NdG2Q/8B9UitHTfa03/rcs7z+++yUVY\n" +
            "4wFO/S3WdLDPoCbGxYNDk4M8rrdvaiXehwHRqfKA3KF0+7ICItzxw+mRc6y9kqBn\n" +
            "JPE9HwJi7pzQThBFuNT3/OiIBKhWZ7n4vuDXx03Y0omETi1XHruieo0=\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuYtGVC5oOfs3s0hd8BXu\n" +
            "1c1yV9pWnBVch5uCjqqqlAOnviET2CLNECDByc6KQg3YZ8sRfmUJ1HI2pdCWSI3g\n" +
            "Qj9gto0oTTJ7Mjhxkk/up3r3BQEQXKi9n/4kF/oW0b72HOq4CJvlRLjMcG8LGYWT\n" +
            "O8SUj8ca+Lj//5TZ9kJer2gbE0Fy32aS3LgBag4kREbu9pSrZ+U1k1aO1einYB/q\n" +
            "xBBtMQM2pIxDyyn6zgaVrPtixhoo2c1f3TeWm8TT8IAMSq8hXNpMFcTxtOlejfel\n" +
            "Q/lGlw48E4aGY+mv1dZ9SgXdoE1bf4yfUEgAVjc2roP0OJgjkJmHZ++3cv4yifs+\n" +
            "JQIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
                .accessTokenConverter(tokenEnhancer());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
                .refreshTokenValiditySeconds(20000);

    }

}