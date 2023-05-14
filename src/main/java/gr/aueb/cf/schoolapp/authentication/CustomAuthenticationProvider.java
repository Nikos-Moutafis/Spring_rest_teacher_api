package gr.aueb.cf.schoolapp.authentication;

import gr.aueb.cf.schoolapp.model.User;
import gr.aueb.cf.schoolapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.management.relation.Role;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final MessageSource messageSource;

    @Autowired
    public CustomAuthenticationProvider(UserRepository userRepository, MessageSource messageSource) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init(){
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        if (!userRepository.isUserValid(username,password)){
            throw new BadCredentialsException(accessor.getMessage("badCredentials"));
        }

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        return new UsernamePasswordAuthenticationToken(username, password, authorities);

        return new UsernamePasswordAuthenticationToken(username, password, Collections.<GrantedAuthority>emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}


