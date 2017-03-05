package com.wth.dal.dao;

import com.wth.object.LoginLog;

/**
 * Created by hanyuan on 3/5/17.
 */
public interface LoginLogDao {

    //保存登陆日志SQL

    public void insertLoginLog(LoginLog loginLog);
}
