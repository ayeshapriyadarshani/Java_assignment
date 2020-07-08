/*
 * package com.wiley.covid19.security;
 * 
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.wiley.covid19.model.AppUserDetails; import
 * com.wiley.covid19.model.User; import
 * com.wiley.covid19.repository.UserRepository;
 * 
 * @Service public class UserDetailServiceImpl implements UserDetailsService {
 * 
 * @Autowired UserRepository userRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String userName) throws
 * UsernameNotFoundException { Optional<User> user =
 * userRepository.findByUserName(userName);
 * 
 * user.orElseThrow(() -> new UsernameNotFoundException("Not found: " +
 * userName));
 * 
 * return user.map(AppUserDetails::new).get(); }
 * 
 * }
 */