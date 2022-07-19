package com.example.ecommerceapp.security;

import com.example.ecommerceapp.security.jwt.AuthEntryPoint;
import com.example.ecommerceapp.security.jwt.AuthTokenFilter;
import com.example.ecommerceapp.service.implementations.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
// NOTE: this is the new way of doing things, I couldn't find enough tutorials/examples. Doing it the old, deperecated (?) way
@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        //httpSecurity
        //        .authorizeRequests()
        //        .antMatchers("/").permitAll();
        //return httpSecurity.build();

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        return httpSecurity.build();
    }
}
*/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPoint unauthorizedHandler;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().permitAll();
         */
        /*
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll();
         */
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated().and()
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
