package v.o.r.ecommerce.common.filter;

import java.io.IOException;
import static v.o.r.ecommerce.common.utils.Statics.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import v.o.r.ecommerce.users.entities.UserEntity;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//NOTE: this class if for create token at endpoint login
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
    
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //NOTE: this method try validate data and create token for spring security
    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response
    )throws AuthenticationException{
        
        UserEntity user = null;
        String userEmail = null;
        String password = null;
     
        try {
            user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
            userEmail = user.getEmail();
            password = user.getPassword();
        } catch (StreamReadException e) {
            e.printStackTrace();
        }catch (DatabindException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userEmail,password);

        return authenticationManager.authenticate(authToken);
    }


    //NOTE: this method if responses is successfully and create token jwt
    @Override
    protected void successfulAuthentication(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain chain,
        Authentication authResult
    ) throws IOException, ServletException {

        User user = (User) authResult.getPrincipal();
        String userEmail = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

    
        Claims claims = Jwts.claims()
            .add("authorities", new ObjectMapper().writeValueAsString(roles))
        .   build();
        String token = Jwts.builder()
            .subject(userEmail)
            .claims(claims)//NOTE: permission of user
            .expiration(new Date(System.currentTimeMillis() + 3600000))//NOTE: Token expired one hour
            .issuedAt(new Date())//NOTE: date at create token
            .signWith(SECRET_KEY)
            .compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String,String> json = new HashMap<>();
        json.put("token", token);
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(json));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }


    //NOTE: this method is for auth is invalid
    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request, 
        HttpServletResponse response,
        AuthenticationException failed
    ) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error in authentication, email or password is incorrect");
        body.put("error", failed.getMessage());
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }

    
    
}
