package com.example.SistemaGIS.Security;


import com.example.SistemaGIS.Filter.CustomBasicAuthAuthenticationEntryPoint;
import com.example.SistemaGIS.Filter.CustomJWTAuthenticationFilter;
import com.example.SistemaGIS.Filter.CustomJWTAuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomBasicAuthAuthenticationEntryPoint basicAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        CustomJWTAuthenticationFilter customJWTAuthenticationFilter = new CustomJWTAuthenticationFilter(authenticationManagerBean());
        customJWTAuthenticationFilter.setFilterProcessesUrl("/users/login");
        httpSecurity.cors().and().csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/users/login/**", "/users/register/**", "/drivers/get-report-penalties/**", "/users/refresh-token/**").permitAll();
        httpSecurity.httpBasic().authenticationEntryPoint(basicAuthenticationEntryPoint);
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        httpSecurity.addFilter(customJWTAuthenticationFilter);
        httpSecurity.sessionManagement().sessionCreationPolicy(STATELESS);
        httpSecurity.addFilterBefore(new CustomJWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("Content-Type");
        configuration.addAllowedHeader("Authorization");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
