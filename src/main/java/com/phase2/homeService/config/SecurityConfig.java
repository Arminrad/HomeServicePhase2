package com.phase2.homeService.config;

import com.phase2.homeService.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //private final CustomUserDetailService customUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomSuccessHandler customSuccessHandler;

    public SecurityConfig(PasswordEncoder passwordEncoder, UserRepository userRepository, CustomSuccessHandler customSuccessHandler) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.customSuccessHandler = customSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
                .and()
                .formLogin()
                .successHandler(customSuccessHandler);
                //.and()
                //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((email) -> userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("This " + email +" notFound!")));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
