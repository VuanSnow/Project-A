package com.myproject.projecta.web;

import com.myproject.projecta.domain.User;
import com.myproject.projecta.domain.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailServiceImpl implements UserDetailsService {
    
    final UserRepository repo;
    
    @Autowired
    public UserDetailServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        UserDetails ud = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole()));
        return ud;
    }
}
