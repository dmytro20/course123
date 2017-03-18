package ua.nure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.err.println("configure");
        http.httpBasic().and().csrf().disable()
//                .authorizeRequests()
//                    .antMatchers("/resources/**", "/registration").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                .authorizeRequests().antMatchers("/a/**").access("hasRole('ADMIN')")
//                    .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                    .and()
//                .logout()
//                    .permitAll();
        .authorizeRequests()
                .antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/a").access("hasRole('ADMIN')")
                //.antMatchers("/confidential/**").access("hasRole('ROLE_SUPERADMIN')")
                .and().formLogin().loginPage("/login").permitAll();//.defaultSuccessUrl("/", false);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.err.println("Gl_configure");
        auth.userDetailsService(userDetailsService);// .passwordEncoder(bCryptPasswordEncoder());
        /*auth.inMemoryAuthentication().withUser("admin1").password("user").roles("ADMIN");*/
    }
}