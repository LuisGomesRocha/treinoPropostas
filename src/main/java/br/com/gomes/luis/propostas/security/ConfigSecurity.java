package br.com.gomes.luis.propostas.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                //.antMatchers(HttpMethod.GET, "/proposta").hasAuthority("SCOPE_proposta-scope:read")
                .antMatchers(HttpMethod.GET, "/proposta").permitAll()
                .antMatchers(HttpMethod.POST, "/proposta/**").permitAll()
                .antMatchers( "/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and().csrf().disable()
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
