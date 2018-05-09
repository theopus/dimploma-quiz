package com.ira.quizplatform.security;

import com.ira.quizplatform.repository.UserRepo;
import com.ira.quizplatform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersSecurityProfile implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public UsersSecurityProfile(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        System.out.println(user);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_STUDENT");
        if (username.equals("admin")) {
            auth = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_TEACHER");
        }
        String password = user.getPassword();
        return org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(username)
                .password(password)
                .authorities(auth)
                .build();
    }
}
