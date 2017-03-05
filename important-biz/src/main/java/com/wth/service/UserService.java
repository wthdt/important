package com.wth.service;

import com.wth.object.User;

/**
 * Created by hanyuan on 3/5/17.
 */
public interface UserService {

    public boolean hasMatchUser(String userName, String password);

    public void loginSuccess(User user);

    public User findUserByUserName(String userName);

}
