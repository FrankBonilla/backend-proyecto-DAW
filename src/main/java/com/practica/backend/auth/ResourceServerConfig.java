package com.practica.backend.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //Aqui damos permisos a todos a acceder a esta ruta de la api al método GET
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/empleados").permitAll()
                .anyRequest().authenticated();
    }
}
