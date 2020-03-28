package com.example.demo.model.mapper;

import com.example.demo.entity.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.model.message.request.CreateUserReq;
import com.example.demo.model.message.request.UpdateUserReq;

import java.util.Date;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto temp = new UserDto();
        temp.setId(user.getId());
        temp.setAvatar(user.getAvatar());
        temp.setUserName(user.getUserName());
        temp.setEmail(user.getEmail());
        temp.setAddress(user.getAddress());
        temp.setCreateDate(user.getCreateDate());
        temp.setUpdateDate(user.getUpdateDate());
        temp.setRole(user.getRole());
        return temp;
    }

    public static User toUser(CreateUserReq createUserReq) {
        User user = new User();
        user.setUserName(createUserReq.getName());
        user.setEmail(createUserReq.getEmail());
        user.setPassword(createUserReq.getPassword());
        user.setRole("USER");
        user.setAvatar("/images/default-avt.png");
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        return user;
    }

    public static User toUser(UpdateUserReq updateUserReq, int userID, Date createdDate) {
        User user = new User();
        user.setId(userID);
        user.setUserName(updateUserReq.getName());
        user.setAvatar(updateUserReq.getAvatar());
        user.setCreateDate(createdDate);
        user.setUpdateDate(new Date());
        user.setRole(updateUserReq.getRole());
        return user;
    }
}
