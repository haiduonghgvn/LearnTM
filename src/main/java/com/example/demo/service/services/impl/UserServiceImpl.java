package com.example.demo.service.services.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.DuplicateRecordException;
import com.example.demo.exception.InternalServerException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.message.request.CreateUserReq;
import com.example.demo.model.message.request.UpdateUserReq;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.services.CRUDService;
import com.example.demo.service.services.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements CRUDService<UserDto> {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<UserDto> listAll() {
        List<UserDto> userDTOs = new ArrayList<>();
        for (User user : userRepo.findAll()) {
                userDTOs.add(UserMapper.toUserDto(user));
        }
        return userDTOs;
    }

    @Override
    public ServiceResult delete(int id) {
        ServiceResult result = new ServiceResult();
        User productEntity = userRepo.findById(id).orElse(null);
        if (productEntity == null) {
            result.setStatus(ServiceResult.Status.FAILED);
            result.setMessage("Customer Not Found");
        } else {
            userRepo.delete(productEntity);
            result.setMessage("success");
        }
        return result;
    }

    @Override
    public UserDto create(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto update(UserDto userDto, int id) {
        return null;
    }

    public UserDto create(CreateUserReq createUserReq) {
        User user = userRepo.findByUserName(createUserReq.getEmail());
        if(user != null) {
            throw new DuplicateRecordException("Email is already in use!");
        }

        String passwordEncode = passwordEncoder.encode("123456");
        createUserReq.setPassword(passwordEncode);
        user = UserMapper.toUser(createUserReq);
        userRepo.save(user);

        return UserMapper.toUserDto(user);
    }

    public UserDto update(UpdateUserReq updateUserReq, int id) {
        Optional<User> user = userRepo.findById(id);
        if(!user.isPresent()) {
            throw new NotFoundException("This User does not exist!");
        }
        user.get().setUserName(updateUserReq.getName());
        user.get().setRole(updateUserReq.getRole());
        user.get().setAvatar(updateUserReq.getAvatar());
        user.get().setUpdateDate(new Date());
        try {
            userRepo.save(user.get());
        } catch (Exception e) {
            throw new InternalServerException(e.toString());
        }

        return UserMapper.toUserDto(user.get());
    }

}
