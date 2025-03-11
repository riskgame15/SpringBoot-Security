package com.springbootsecurity.config;


import com.springbootsecurity.service.user.IAppUserService;
import com.springbootsecurity.service.user.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // xac thuc
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private IAppUserService userService;
    //    xac thuc
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        service phai la 1 instance cua UserDetailService
        authProvider.setUserDetailsService((UserDetailsService) userService);
        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        // builder pattern
//        //        chuyen tu user trong db -  userdetail
////      UserDetails user = User.withUsername("admin").password("$2a$10$AhRVCCzzASq6tMtLrm8WyuncN4YVOEeEmeoxGRskQxBgdsdXtWEiS").roles("USER").build();
//      UserDetails admin = User.withUsername("mck").password(passwordEncoder().encode("123456")).roles("ADMIN").build();
//      UserDetails user = User.withUsername("j97").password(passwordEncoder().encode("123456")).roles("USER").build();
//      return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(
                        author -> author
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasRole("USER")
//                                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}


