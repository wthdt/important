package com.wth.dal.dao.impl;

import com.wth.dal.dao.LoginLogDao;
import com.wth.object.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hanyuan on 3/5/17.
 */
@Repository
public class LoginLogDaoImpl implements LoginLogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String INSERT_LOGIN_LOG_SQL= "INSERT INTO t_login_log(user_id,ip,login_datetime) VALUES(?,?,?)";

    @Override
    public void insertLoginLog(LoginLog loginLog) {
        Object[] args = { loginLog.getUserId(), loginLog.getIp(),
                loginLog.getLoginDate() };
        jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, args);
    }
}
