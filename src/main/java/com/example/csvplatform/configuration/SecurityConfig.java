package com.example.csvplatform.configuration;

import com.example.csvplatform.services.serviceImpl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final CustomSuccessHandler customSuccessHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          PasswordEncoder passwordEncoder, CustomSuccessHandler customSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.customSuccessHandler = customSuccessHandler;
    }

    // Organization Security Configuration
    @Bean
    @Order(2)
    public SecurityFilterChain organizationFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Entering Organisation Filter Chain");
        http
                .securityMatcher("/organisation/**", "/auth/**", "/organisationlogin","/organisation_login")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/organisationlogin", "/volunteer_login", "/register", "/css/**", "/images/**", "/js/**", "/auth/**").permitAll()
                        .requestMatchers("/task/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/organisationlogin")
                        .loginProcessingUrl("/organisation_login")
                        .defaultSuccessUrl("/organisation/home")
                        .successHandler(customSuccessHandler)
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutUrl("/organisation/logout")
                        .logoutSuccessUrl("/home")
                )
                .authenticationProvider(organizationAuthenticationProvider());
        return http.build();
    }

    // Volunteer Security Configuration
    @Bean
    @Order(1)
    public SecurityFilterChain volunteerFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Entering Volunteer Filter Chain");
        http
                .securityMatcher("/volunteer/**", "/volunteerlogin", "/volunteer_login")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/organisationlogin", "/volunteerlogin", "/volunteer_login", "/register", "/css/**", "/images/**", "/js/**", "/auth/**").permitAll()
                        .requestMatchers("/organisationlogin").permitAll()
                        .requestMatchers("/task/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/volunteerlogin")
                        .loginProcessingUrl("/volunteer_login")
                        .defaultSuccessUrl("/volunteer/home")
                        .successHandler(customSuccessHandler)
                        .usernameParameter("email")
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutUrl("/volunteer/logout")
                        .logoutSuccessUrl("/volunteer/login?logout")
                )
                .authenticationProvider(volunteerAuthenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider organizationAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider volunteerAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
