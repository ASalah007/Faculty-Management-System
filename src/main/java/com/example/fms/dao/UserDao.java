package com.example.fms.dao;

import com.example.fms.model.User;

public interface UserDao {
    User findUserByemail(String email);
    User findUserByID(String id);
    boolean insertNewUser(String email, String password, String name, String id, String address, String birthdate);
}
