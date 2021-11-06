package ru.magiccamp.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf();

        http.authorizeRequests()
                .antMatchers("/", "/user/log-in", "/user/register", "/log-in").permitAll()
                .antMatchers("/**/*.css").permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers( "/user/**", "/edit-profile", "/profile", "/horoscope/*").authenticated()
                .antMatchers("/api/*").authenticated()
                .anyRequest().denyAll();

        http.formLogin()
                .loginPage("/user/log-in")
                .loginProcessingUrl("/user/log-in")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll();

        http.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
