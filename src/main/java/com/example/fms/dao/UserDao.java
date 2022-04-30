package com.example.fms.dao;

import com.example.fms.model.User;

public interface UserDao {

    User findUserByemail(String email);

    User findUserByID(String id);

    //returns id of inserted user, -1 if failed
    int insertNewUser(String email, String password, String name, String address, String birthdate);
}
