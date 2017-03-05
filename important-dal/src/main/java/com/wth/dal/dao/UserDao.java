package com.wth.dal.dao;

import com.wth.object.User;

/**
 * Created by hanyuan on 3/5/17.
 */
public interface UserDao {

    public int getMatchCount(String userName, String password);

    public User findUserByUserName(final String userName);

    public void updateLoginInfo(User user);
}
