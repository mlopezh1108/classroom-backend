package net.developz.classroom.backend.iam.auth.infrastructure.security.user;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.developz.classroom.backend.iam.access.application.port.PersonPermissionRepositoryPort;
import net.developz.classroom.backend.iam.user.application.port.PersonRepositoryPort;
import net.developz.classroom.backend.iam.access.application.port.PersonRoleRepositoryPort;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

        private final PersonRepositoryPort personRepository;
        private final PersonRoleRepositoryPort personRoleRepository;
        private final PersonPermissionRepositoryPort personPermissionRepository;

        @Override
        @Transactional(readOnly = true)
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return personRepository.findByEmail(email)
                                .map(person -> {
                                        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

                                        // 1. Load Role-based permissions
                                        personRoleRepository.findByPersonIdAndActiveTrue(person.getId())
                                                        .forEach(personRole -> {
                                                                // Add the role itself (e.g., ROLE_ADMIN)
                                                                authorities.add(new SimpleGrantedAuthority("ROLE_"
                                                                                + personRole.getRole().getRoleName()));

                                                                // Add all permissions associated with that role (e.g.,
                                                                // SUBJECT_VIEW)
                                                                personRole.getRole().getPermissions()
                                                                                .forEach(permission -> authorities
                                                                                                .add(new SimpleGrantedAuthority(
                                                                                                                permission.getPermissionName())));
                                                        });

                                        // 2. Load Person-specific direct permissions
                                        personPermissionRepository.findByPersonIdAndActiveTrue(person.getId())
                                                        .forEach(personPermission -> authorities.add(
                                                                        new SimpleGrantedAuthority(personPermission
                                                                                        .getPermission()
                                                                                        .getPermissionName())));

                                        return new User(
                                                        person.getEmail(),
                                                        person.getPassword(),
                                                        authorities);
                                })
                                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
        }

}



