package com.example.administrator.huarongdao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class gamestart extends AppCompatActivity {

    private static  final  String TAG="gamestart";
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
        name = findViewById(R.id.player_name);
        password = findViewById(R.id.password);
    }

    public void next(View view) {
        Intent intent = new Intent(gamestart.this, MainActivity.class);
        startActivity(intent);
    }

    public void roll(View view) {
        Intent intent = new Intent(gamestart.this, roll_test.class);
        startActivity(intent);
    }

    public void fun(View v){
        switch (v.getId()){
            case R.id.register://点击的是注册按钮的话
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String n = name.getText().toString().trim();//用trim函数去掉用户输入的名字中的空白
                        String psw = password.getText().toString().trim();
                        Log.i(TAG,"***start***");
                        UserDao ud = new UserDao();
                        boolean result =ud.register(n,psw);
                        if (!result){
                            Looper.prepare();
                            Toast toast = Toast.makeText(gamestart.this,"注册成功！",Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                        Log.i(TAG,"fun"+result);
                    }
                }).start();
                break;
            case R.id.login://点击的是登录按钮的话
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String n = name.getText().toString().trim();
                        String psw = password.getText().toString().trim();
                        if (n.equals("")||psw.equals("")){
                            Looper.prepare();
                            Toast toast = Toast.makeText(gamestart.this,"输入不能为空！",Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                        UserDao ud = new UserDao();
                        Boolean result = ud.login(n,psw);
                        if (!result){
                            Looper.prepare();
                            Toast toast=Toast.makeText(gamestart.this,"用户名不存在或密码错误！",Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }else{
                            Looper.prepare();
                            Toast toast=Toast.makeText(gamestart.this,"登录成功",Toast.LENGTH_SHORT);
                            toast.show();


                            Intent intent = new Intent(gamestart.this, mycenter.class);
                            intent.putExtra("name",n);
                            startActivity(intent);
                            //一下代码为跳转界面
                            // Intent intent=new Intent(MainActivity.this,info.class);
                            //intent.putExtra("name",n);
                            // startActivity(intent);
                            Looper.loop();

                        }

                        //以上为jdbc登录
                    }
                }).start();

        }

    }


}
