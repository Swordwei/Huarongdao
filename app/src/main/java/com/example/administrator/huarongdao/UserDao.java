package com.example.administrator.huarongdao;

import android.util.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static android.content.ContentValues.TAG;

public class UserDao {

    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    //第一个参数为数据库名称，第二个参数为数据库账号 第三个参数为数据库密码
    Connection conn = jdbcUtil.getConnection("swordwei","swordwei","980416wjq");
    //注册

    public UserDao(){
        Log.i(TAG,"***I am here***");
    }


    public  boolean register(String name, String password){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return false;
        }else {
            Log.i(TAG,"***start register***");
            //进行数据库操作
            String sql = "insert into user(name,password) values(?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,name);
                pre.setString(2,password);
                return pre.execute();
            } catch (SQLException e) {
                Log.i(TAG,"***connect Wrong***");
                return false;
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //登录
    public boolean login(String name, String password){
        if (conn==null){
            Log.i(TAG,"register:conn is null");
            return false;
        }else {
            String sql = "select * from user where name=? and password=?";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setString(1,name);
                pres.setString(2,password);
                ResultSet res = pres.executeQuery();
                boolean t = res.next();
                return t;
            } catch (SQLException e) {

                return false;
            }

        }
    }
}