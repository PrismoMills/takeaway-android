package com.example.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.order.MyApplication;
import com.example.order.R;
import com.example.order.dao.UserDao;
import com.example.order.data.DataServer;
import com.example.order.model.User;
import com.example.order.utils.Tips;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginUsernameEdit)
    EditText usernameEdit;

    @BindView(R.id.loginPasswordEdit)
    EditText passwordEdit;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Sign in");

        ButterKnife.bind(this);

        // 恢复账号
        String username = UserDao.getUsername();
        usernameEdit.setText(username);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        finish();
    }


    @OnClick(R.id.loginBtn)
    void login() {
        String username = usernameEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString();
        User user = new User(username, password);


        User loginUser = UserDao.findAccount(username);
        if (loginUser != null && loginUser.equals(user)) {

            Tips.show("Signed in");

            MyApplication.isLogin(true);
            MyApplication.setUser(loginUser);


            UserDao.saveUser(loginUser);
            UserDao.isLogin(true);


            UserDao.saveUsername(username);


            finish();
        } else {

            Tips.show("Incorrect username or password");
            passwordEdit.setText("");
        }
    }

    @OnClick(R.id.registerBtn)
    void register() {
        RegisterActivity.actionStart(this);
    }


    public User checkUserFromAccountList(User u) {
        for (User tmp : DataServer.getAccountList()) {
            if (tmp.equals(u)) {
                return tmp;
            }
        }

        return null;
    }
}