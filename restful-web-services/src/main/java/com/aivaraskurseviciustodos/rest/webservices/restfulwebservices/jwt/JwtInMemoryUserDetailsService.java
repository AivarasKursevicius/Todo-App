package com.aivaraskurseviciustodos.rest.webservices.restfulwebservices.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(
        new JwtUserDetails(
            1L,
            "aivkur",
            "$2a$10$3GlzV/sy.O8VKIxZf/aKYudUwxlAKSL/gIGl4iD/duwmP2MaPBZkO",
            "ROLE_USER_2"));
    inMemoryUserList.add(
            new JwtUserDetails(
                    2L,
                    "test",
                    "$2a$10$VnXEUGSnYSswxWe6dTf6k.Yp1PmOf7bC6XDtBm.aZuLTrq1jXD18m",
                    "ROLE_USER_2"));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst =
        inMemoryUserList.stream().filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }
}
