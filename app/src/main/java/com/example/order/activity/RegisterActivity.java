package com.example.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.order.dao.UserDao;
import com.example.order.R;
import com.example.order.model.User;
import com.example.order.utils.Tips;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.loginUsernameEdit)
    EditText usernameEdit;

    @BindView(R.id.loginPasswordEdit)
    EditText passwordEdit;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setTitle("Create account");

        ButterKnife.bind(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //finish();
    }


    @OnClick(R.id.registerBtn)
    void register() {
        String username = usernameEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString();
        User user = new User(username, password);

        if (username.isEmpty() || password.trim().isEmpty()) {
            Tips.show("Enter a username and password");
            return;
        }
        if (!UserDao.registerAccount(user)) {
            Tips.show("Could not register. Check whether this username already exists.");
            return;
        }
        //LoginActivity.actionStart(this);
        Tips.show("Account created");
        finish();
    }

}