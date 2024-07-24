package v.o.r.ecommerce.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import v.o.r.ecommerce.common.filter.JwtAuthenticationFilter;
import v.o.r.ecommerce.common.filter.JwtValidationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    //NOTE: this method is for encoder password
    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //this endpoint is for provide request without validate
        return http.authorizeHttpRequests((auth) -> auth
        .requestMatchers("api/v1/user/register").permitAll()//FIX: must use env for api/v1
        //.requestMatchers("api/v1/seed").permitAll()//NOTE: use only for first time start project then comments this
        .anyRequest().authenticated())
        
        //NOTE: call class
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))//add logic of class authFilter
        .addFilter(new JwtValidationFilter(authenticationManager()))//add logic of class authValidationFilter
        .csrf(config -> config.disable())
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
    }
}
