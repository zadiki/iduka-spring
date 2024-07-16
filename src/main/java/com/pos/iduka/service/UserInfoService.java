package com.pos.iduka.service;

import com.pos.iduka.exception.DataDoesNotExistException;
import com.pos.iduka.model.UserInfo;
import com.pos.iduka.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService  implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userDetails= userInfoRepository.findByEmail(email);

        return userDetails.map(UserInfoDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("user not found"+email));

    }


    public UserInfo loadUserByEmail(String email) throws DataDoesNotExistException {
        Optional<UserInfo> userDetails= userInfoRepository.findByEmail(email);

        return userDetails
                .orElseThrow(()->new DataDoesNotExistException("user not found"+email));

    }



    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return userInfo;
    }
}
