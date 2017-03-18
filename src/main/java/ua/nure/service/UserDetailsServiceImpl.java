package ua.nure.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.nure.model.Role;
import ua.nure.model.User;
import ua.nure.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = Optional.ofNullable(userRepository.findByUsername(username));
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (optional.isPresent()) {
            for (Role role : optional.get().getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.name()));
            }
            System.err.println(optional.get() + " R: " + authorities);
            org.springframework.security.core.userdetails.User u =
            new org.springframework.security.core.userdetails.User(optional.get().getUsername(),
                    optional.get().getPassword(),
                    true,
                    true,
                    true,
                    true, authorities);
            return u;

        }
        return new org.springframework.security.core.userdetails.User("error", "error", authorities);
    }
}
