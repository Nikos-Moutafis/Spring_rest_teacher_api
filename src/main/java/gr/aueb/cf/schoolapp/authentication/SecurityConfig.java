package gr.aueb.cf.schoolapp.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationProvider authProvider;

    @Autowired
    public SecurityConfig(CustomAuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
//                .authorizeRequests().antMatchers("/login").hasRole("ADMIN")
                .authorizeRequests().antMatchers("/api/teachers/**").authenticated()
//                .and().authorizeRequests().antMatchers("/api/teachers/**").hasRole("ADMIN")
                .anyRequest().authenticated().and().formLogin()
                .loginPage("/login").defaultSuccessUrl("/api/teachers?lastname=").permitAll()
                .and().httpBasic()
                .and()
//                .authorizeRequests().antMatchers(HttpMethod.POST,"/api/teachers").hasRole("ADMIN")
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        return http.build();

//                .and()
//                .authorizeRequests().antMatchers(HttpMethod.GET,"/api/teachers").permitAll()
//                .authorizeRequests().antMatchers(HttpMethod.POST,"/api/teachers").hasRole("ADMIN")

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring().antMatchers("/styles/**", "/img/**", "/js/**");
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration)
        throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
