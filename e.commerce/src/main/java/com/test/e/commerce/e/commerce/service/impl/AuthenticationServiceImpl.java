package com.test.e.commerce.e.commerce.service.impl;

import com.test.e.commerce.e.commerce.entity.UserEntity;
import com.test.e.commerce.e.commerce.exception.ResourceNotFound;
import com.test.e.commerce.e.commerce.repository.UserRepository;
import com.test.e.commerce.e.commerce.service.AuthenticationService;
import liquibase.pro.packaged.D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean authenticateUser(String apiKey) throws TimeoutException {

        UserEntity userEntity = userRepository.findByApiKey(apiKey);
        if(userEntity == null)
            throw new UsernameNotFoundException("User Not Found");

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(userEntity.getUpdatedAt());
        calendar.add(Calendar.MINUTE, 60);
        Date keyExpiryTime = calendar.getTime();

        if (currentDate.compareTo(keyExpiryTime) > 0)
            throw new TimeoutException("Session Time Out");

        userEntity.setCreatedAt(new Date());
        userEntity.setUpdatedAt(new Date());
        userRepository.save(userEntity);

        return true;
    }

    @Override
    public String login(String name){
        UserEntity userEntity = userRepository.findByName(name);
        if(userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setAdmin(false);
            userEntity.setName(name);
        }
        userEntity.setApiKey(UUID.randomUUID().toString());
        userRepository.save(userEntity);
        return userEntity.getApiKey().toString();
    }

}
