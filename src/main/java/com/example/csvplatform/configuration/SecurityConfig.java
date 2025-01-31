package com.example.csvplatform.configuration;

import com.example.csvplatform.services.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    private final PasswordEncoder passwordEncoder;

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService,
                                    PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
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
                        .requestMatchers("/organisationlogin","/register","/css/**","/images/**","/js/**","/auth/**").permitAll()
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
                        .requestMatchers("/volunteerlogin","/register","/css/**","/images/**","/js/**","/auth/**").permitAll()
                        .requestMatchers("/volunteer/**").hasRole("VOLUNTEER")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form
                        .loginPage("/volunteerlogin")
                        .loginProcessingUrl("/volunteer_login")
                        .defaultSuccessUrl("/volunteer/home")
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
