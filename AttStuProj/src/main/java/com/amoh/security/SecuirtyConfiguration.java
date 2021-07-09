package com.amoh.security;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecuirtyConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("doctor")
                .password("1234")
                .roles("Doctor")
                .and()
                .withUser("student")
                .password("1234")
                .roles("Student");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/chome/all/").hasRole("Doctor")
//                .antMatchers("/chome/all/id/*").hasRole("Student")
//                .antMatchers("/#").authenticated().and().formLogin();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .defaultSuccessUrl("/",true);

        http.authorizeRequests()
                .mvcMatchers("/chome/all").hasRole("Doctor")
                .mvcMatchers("/chome/all/id/*").hasRole("Student")
                .mvcMatchers("/").permitAll()
                .anyRequest().authenticated();
    }
}
