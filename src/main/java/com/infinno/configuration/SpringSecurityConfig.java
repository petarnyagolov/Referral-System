package com.infinno.configuration;

import com.infinno.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/","/register/**","/bootstrap/**","/jquery/**","/connect/**","/tether/**","/css/**","/campaigns/all").permitAll()
               .antMatchers("/referrals/**","/campaigns/show/{id}").hasRole("USER")
               .antMatchers("/campaigns/**").hasRole("ADMIN")
               .antMatchers("/profile","/campaigns/all?page=${i}").authenticated()
               .anyRequest().authenticated()
               .and()
               .formLogin().loginPage("/login").permitAll()
               .usernameParameter("username")
               .passwordParameter("password")
               .and()
               .rememberMe()
               .rememberMeCookieName("RememberMe")
               .rememberMeParameter("rememberMe")
               .key("Shhh")
               .tokenValiditySeconds(100000)
               .and()
               .logout().logoutSuccessUrl("/login?logout")
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
               .and().exceptionHandling().accessDeniedPage("/unautorized")
               .and().csrf().csrfTokenRepository(csrfTokenRepository());

    }
    private CsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository repository = new
                HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
