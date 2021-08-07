package br.com.gomes.luis.propostas.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class ConfigSecurity extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers("/h2-console/**/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/cartoes/**").hasAuthority("SCOPE_cartoes:read")
                        .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_cartoes:write")
                        .antMatchers(HttpMethod.POST, "/propostas/**").hasAuthority("SCOPE_proposta:write")
                        .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_proposta:read")
                        .antMatchers(HttpMethod.POST, "/bloquear_cartao/**").hasAuthority("SCOPE_proposta:write")
                        .antMatchers(HttpMethod.POST, "/nova_biometria/**").hasAuthority("SCOPE_proposta:write")
                        .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                        .anyRequest().authenticated()
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**/**");
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.headers().frameOptions().sameOrigin();


    }
}
