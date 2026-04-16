package net.developz.classroom.backend.iam.infrastructure.security.user;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.iam.infrastructure.persistence.repository.PersonRepository;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return personRepository.findByEmail(email)
                .map(person -> new User(
                        person.getEmail(),
                        person.getPassword(),
                        List.of(new SimpleGrantedAuthority("ROLE_" + person.getDefaultRole()))))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }

}
