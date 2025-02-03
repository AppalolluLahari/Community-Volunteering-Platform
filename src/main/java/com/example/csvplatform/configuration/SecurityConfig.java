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
public class SecurityConfig  {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsService;

    private final CustomSuccessHandler customSuccessHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                          PasswordEncoder passwordEncoder, CustomSuccessHandler customSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.customSuccessHandler = customSuccessHandler;
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
////                .httpBasic(Customizer.withDefaults());
////                .authenticationManager(authenticationManager());
//        return http.build();
//    }

    @Bean
    @Order(1)
    public SecurityFilterChain organizationFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/organization/**","/auth/**")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home","/organisationlogin","/volunteer_login","/organisation_login","/register","/css/**","/images/**","/js/**","/auth/**").permitAll()
                        .requestMatchers("/task/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/organizationlogin")
                        .loginProcessingUrl("/organization_login")
                        .defaultSuccessUrl("/organisation/home")
                        .usernameParameter("email")  // Match your form field
                        .passwordParameter("password")
                )
                .logout(logout -> logout
                        .logoutUrl("/organization/logout")
                        .logoutSuccessUrl("/home")
                )
                .authenticationProvider(organizationAuthenticationProvider());

        return http.build();
    }

    // Volunteer Security Configuration
    @Bean
    @Order(2)
    public SecurityFilterChain volunteerFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home","/organisationlogin","/volunteerlogin","/volunteer_login","/register","/css/**","/images/**","/js/**","/auth/**").permitAll()
                        .requestMatchers("/task/**").authenticated()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .loginPage("/volunteerlogin")
                        .loginProcessingUrl("/volunteer_login")
                        .defaultSuccessUrl("/volunteer/home")
                        .successHandler(customSuccessHandler)
                        .usernameParameter("email")  // Match your form field
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
