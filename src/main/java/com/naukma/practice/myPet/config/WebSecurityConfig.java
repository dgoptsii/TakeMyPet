package com.naukma.practice.myPet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
public class WebSecurityConfig
        extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Bean
  public BCryptPasswordEncoder bpasswordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("owner").password(bpasswordEncoder().encode("ownerPass")).roles("OWNER")
            .and()
            .withUser("host").password(bpasswordEncoder().encode("hostPass")).roles("HOST");
//    auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .usersByUsernameQuery(
//                    "select login, password, 'true' from user " +
//                            "where login=?")
//            .authoritiesByUsernameQuery(
//                    "select login, role from user " +
//                            "where login=?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
//            .antMatchers("/owner/**").hasRole("OWNER")
//            .antMatchers("/host/**").hasRole("HOST")
            .antMatchers("/**").permitAll()
            .and()
            .formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/hello")
            .permitAll();

  }
  
}
