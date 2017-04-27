package doctorclient.slinph.com.doctor_client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import doctorclient.slinph.com.doctor_client.Internet.JavaBeanRequest;
import doctorclient.slinph.com.doctor_client.Internet.RequestBean.LoginResult;
import doctorclient.slinph.com.doctor_client.Internet.Urls;
import doctorclient.slinph.com.doctor_client.R;
import doctorclient.slinph.com.doctor_client.Utils.Globalvariable;
import doctorclient.slinph.com.doctor_client.Utils.RongCloudUtils;
import doctorclient.slinph.com.doctor_client.Utils.SharePreferencesUtils;
import doctorclient.slinph.com.doctor_client.Utils.ValidateUtils;
import doctorclient.slinph.com.doctor_client.Views.LoadingDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener,CompoundButton.OnCheckedChangeListener{

    private EditText et_account;
    private EditText et_password;
    private Button bt_login;
    private CheckBox cb_auto_login;
    private CheckBox cb_protocol;
    private Button bt_register;
    private Button bt_find_pw;
    private LoginActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }*/
        mContext = this;
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
                checkInput();
                break;
            case R.id.bt_register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
            case R.id.bt_find_pw:
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

    private void checkInput(){
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        if (account.isEmpty()){
            Toast.makeText(LoginActivity.this, "请输入账号", Toast.LENGTH_SHORT).show();
            return;
        }else {
            boolean validateMobile = ValidateUtils.validateMobile(account);
            if (!validateMobile){
                Toast.makeText(LoginActivity.this, "请输入正确的账号", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (password.isEmpty()){
            Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!cb_protocol.isChecked()){
            Toast.makeText(LoginActivity.this, "同意用户协议才能继续", Toast.LENGTH_SHORT).show();
            return;
        }
        requestLogin(account,password);
    }

    private void requestLogin(final String tel, final String psw) {
            final JavaBeanRequest<LoginResult> request = new JavaBeanRequest<>(Urls.loginUrl, RequestMethod.POST, LoginResult.class);
            request.add("tel",tel);
            request.add("passwd",psw);

            request(0, request, new OnResponseListener<LoginResult>() {

                private AlertDialog dialog;

                @Override
                public void onStart(int what) {
                    LoadingDialog loadingDialog = new LoadingDialog(mContext);
                    dialog = loadingDialog.showLoadingDialog("");
                }

                @Override
                public void onSucceed(int what, Response<LoginResult> response) {
                    LoginResult loginResult = response.get();
                    if ("200".equals(loginResult.getCode())){
                        LoginResult.User data = loginResult.getData();
                        String img_url = data.getImg_url();
                        String real_name = data.getReal_name();
                        Globalvariable.HEAD_IMAGE = img_url;
                        Globalvariable.REAL_NAME = real_name;

                        startActivity(new Intent(mContext,MainActivity.class));
                        finish();
                        saveLoginInfo(tel,psw);
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        RongCloudUtils.connect(mContext,"ClMOdS+6aMyDJ2rmrvP0qu3oB5M9l1ON2szCfCKLZM1wA4Iwjmjy98Esl5lFsdu4MacR2mvTv4Pd/PxUwgDgSQ==");
                    }else {
                        Toast.makeText(mContext, loginResult.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(int what, Response<LoginResult> response) {
                    Toast.makeText(mContext, "网络异常 "+response.responseCode(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFinish(int what) {
                    dialog.dismiss();
                }
            });
    }

    private void saveLoginInfo(String account,String psw) {
        boolean isAutoLogin = cb_auto_login.isChecked();
        if (isAutoLogin){
            SharePreferencesUtils.putString(mContext, "LOGIN_ACCOUNT", account);
            SharePreferencesUtils.putString(mContext, "LOGIN_PSW", psw);
        }
        SharePreferencesUtils.putBoolean(mContext,"IS_AUTO_LOGIN",isAutoLogin);
    }
}
