package com.task_app.task_app.Config;


import com.task_app.task_app.Security.JwtTokenFilter;
import com.task_app.task_app.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ApplicationContext applicationContext;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenFilter jwtTokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling( configurer ->
                        configurer
                                .authenticationEntryPoint(((request, response, authException) -> {
                                    response.getStatus();
                                    response.getWriter().write("Unauthorized");
                                }))
                                .accessDeniedHandler(((request, response, accessDeniedException) -> {
                                    response.getStatus();
                                    response.getStatus();
                                    response.getWriter().write("Forbidden");
                                }))
                        )
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .anyRequest().authenticated()
                        )
                .anonymous(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}

//deprecated version
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity
//            .csrf().disable()
//            .cors()
//            .and()
//            .httpBasic().disable()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .exceptionHandling()
//            .authenticationEntryPoint(((request, response, authException) -> {
//                response.getStatus(HttpStatus.UNAUTHORIZED.value());
//                response.getWriter().write("Unauthorized");
//            }))
//            .accessDeniedHandler(((request, response, accessDeniedException) -> {
//                response.getStatus();
//                response.getStatus(HttpStatus.FORBIDDEN.value());
//                response.getWriter().write("Forbidden");
//            }))
//            .and()
//            .authorizeHttpRequests()
//            .requestMatchers("/api/v1/auth/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .anonymous().disable()
//            .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
//
//    return httpSecurity.build();
//}