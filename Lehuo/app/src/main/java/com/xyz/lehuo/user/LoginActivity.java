package com.xyz.lehuo.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xyz.lehuo.R;
import com.xyz.lehuo.global.BaseActivity;
import com.xyz.lehuo.global.Constant;
import com.xyz.lehuo.util.HttpUtil;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyz on 15/12/24.
 */
public class LoginActivity extends BaseActivity {

    ImageView back;
    TextView register;
    EditText username;
    EditText password;
    Button login;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        register = (TextView) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.user_pwd);
        login = (Button) findViewById(R.id.login);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "信息填写不完整", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", name));
                params.add(new BasicNameValuePair("pwd", pwd));
                new HttpUtil().create(HttpUtil.POST, Constant.SERVER_ADDRESS+Constant.LOGIN_API, params, new HttpUtil.HttpCallBallListener() {
                    @Override
                    public void onStart() {
                        progressDialog = ProgressDialog.show(LoginActivity.this, "提示", "登录中，请稍等");
                    }

                    @Override
                    public void onFinish() {
                        progressDialog.cancel();
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(LoginActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(String result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            if (jsonObject.getInt("code") == 1) {

                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}
