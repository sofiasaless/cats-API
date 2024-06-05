package com.example.cats.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

//    filtro de segurança (sem erros de exceção)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests
                        (registry->{
            registry.requestMatchers("/cats/admin/home").hasRole("ADMIN");
            registry.requestMatchers("/cats/user/home").hasRole("USER");
            registry.requestMatchers("/cats/home", "/auth/**").permitAll();
            registry.anyRequest().authenticated();
        })
                .formLogin(formLogin -> formLogin.permitAll())
        .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails normalUser = User.builder()
//                .username("sofia")
//                .password("$2a$12$ikma7SUh.I4anzyHDaA3X.QDUXrPwbUlrD4/jsezLW1knS.JbZWlW")
//                .roles("USER")
//        .build();
//
//        UserDetails adminUser = User.builder()
//                .username("ned")
//                .password("$2a$12$ikma7SUh.I4anzyHDaA3X.QDUXrPwbUlrD4/jsezLW1knS.JbZWlW")
//                .roles("ADMIN", "USER")
//        .build();
//
//        return new InMemoryUserDetailsManager(normalUser, adminUser);
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService;
    }

// autenticação com os usuarios salvos no banco de dados, admin ou user
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
