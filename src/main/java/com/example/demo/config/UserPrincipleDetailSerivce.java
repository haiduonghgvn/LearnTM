package com.example.demo.config;

import com.example.demo.model.dto.UserDto;
import com.example.demo.service.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipleDetailSerivce  implements UserDetailsService {

    @Autowired
    UserServiceImpl customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto user = customerService.findUserByName(username);
        UserPrincipleDetail userPrincipleDetail= new UserPrincipleDetail(user);
        return userPrincipleDetail;
    }
}
