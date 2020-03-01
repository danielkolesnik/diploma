package com.university.contractors.service;

import com.university.contractors.model.jpa.entity.User;
import com.university.contractors.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class ContractorsUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public ContractorsUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getUserRole().getValue()));

    }
}
