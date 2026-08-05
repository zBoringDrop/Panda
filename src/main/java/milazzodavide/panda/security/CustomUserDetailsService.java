package milazzodavide.panda.security;

import lombok.RequiredArgsConstructor;
import milazzodavide.panda.entity.UserEntity;
import milazzodavide.panda.exception.ExceptionMessage;
import milazzodavide.panda.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND_FROM_EMAIL + "(email: " + email + ")"));

        return userEntity;
    }
}