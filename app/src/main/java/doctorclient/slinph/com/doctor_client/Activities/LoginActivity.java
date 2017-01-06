package doctorclient.slinph.com.doctor_client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import doctorclient.slinph.com.doctor_client.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private EditText et_account;
    private EditText et_password;
    private Button bt_login;
    private CheckBox cb_auto_login;
    private CheckBox cb_protocol;
    private Button bt_register;
    private Button bt_find_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }
    }

    @Override
    protected int setToolbarIcon() {
        return 0;
    }

    @Override
    protected String setToolbarTitle() {
        return "";
    }

    @Override
    protected boolean toolbarState() {
        return false;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected View.OnClickListener setNavigationAction() {
        return null;
    }


    @Override
    public void initView() {
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_login = (Button) findViewById(R.id.bt_login);
        cb_auto_login = (CheckBox) findViewById(R.id.cb_auto_login);
        cb_protocol = (CheckBox) findViewById(R.id.cb_protocol);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_find_pw = (Button) findViewById(R.id.bt_find_pw);
    }

    @Override
    public void initEvent() {
        if (bt_login != null){
            bt_login.setOnClickListener(this);
        }
        if (bt_register != null){
            bt_register.setOnClickListener(this);
        }
        if (bt_find_pw != null){
            bt_find_pw.setOnClickListener(this);
        }
        if (cb_auto_login != null){
            cb_auto_login.setOnCheckedChangeListener(this);
        }
        if (cb_protocol != null){
            cb_protocol.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.bt_register:
                Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_find_pw:
                //Toast.makeText(LoginActivity.this, "找回密码", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,ForgetPWActivity.class));
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.cb_auto_login:
                Toast.makeText(LoginActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                break;
            case R.id.cb_protocol:
                Toast.makeText(LoginActivity.this, ""+isChecked, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
