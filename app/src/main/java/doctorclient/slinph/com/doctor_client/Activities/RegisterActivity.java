package doctorclient.slinph.com.doctor_client.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import doctorclient.slinph.com.doctor_client.Internet.JavaBeanRequest;
import doctorclient.slinph.com.doctor_client.Internet.RequestBean.RegisterCode;
import doctorclient.slinph.com.doctor_client.Internet.RequestBean.RegisterResult;
import doctorclient.slinph.com.doctor_client.Internet.Urls;
import doctorclient.slinph.com.doctor_client.R;
import doctorclient.slinph.com.doctor_client.Utils.ValidateUtils;
import doctorclient.slinph.com.doctor_client.Views.LoadingDialog;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private Button bt_register_verification;
    private EditText et_account_register;
    private EditText et_verification_register;
    private EditText et_password_register;
    private EditText et_confirm_password_register;
    private CheckBox cb_protocol_register;
    private Button bt_register;
    private long time;
    private RegisterActivity mContext;

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
        return "注册";
    }

    @Override
    protected boolean toolbarState() {
        return true;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected View.OnClickListener setNavigationAction() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
    }

    @Override
    protected void initView() {
        bt_register_verification = (Button) findViewById(R.id.bt_register_verification);
        et_account_register = (EditText) findViewById(R.id.et_account_register);
        et_verification_register = (EditText) findViewById(R.id.et_verification_register);
        et_password_register = (EditText) findViewById(R.id.et_password_register);
        et_confirm_password_register = (EditText) findViewById(R.id.et_confirm_password_register);
        cb_protocol_register = (CheckBox) findViewById(R.id.cb_protocol_register);
        bt_register = (Button) findViewById(R.id.bt_register);
    }

    @Override
    protected void initEvent() {
        if (bt_register_verification != null){
            bt_register_verification.setOnClickListener(this);}
        if (bt_register != null){
            bt_register.setOnClickListener(this);}
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_register_verification:
                String phone = et_account_register.getText().toString();
                if (phone.isEmpty()){
                    Toast.makeText(RegisterActivity.this, getResources().getString( R.string.phone_empty), Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean validateMobile = ValidateUtils.validateMobile(phone);
                if (validateMobile){
                    requestVerificationCode(phone);
                }else {
                    Toast.makeText(RegisterActivity.this, getResources().getString(R.string.phone_err), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bt_register:
                checkInput();
                break;
        }
    }

    private void requestRegister(String tel,String code,String psw){
        final JavaBeanRequest<RegisterResult> request = new JavaBeanRequest<>(Urls.registerUrl,RequestMethod.POST, RegisterResult.class);
        request.add("tel",tel);
        request.add("sms",code);
        request.add("passwd",psw);

        request(0, request, new OnResponseListener<RegisterResult>() {

            private AlertDialog dialog;

            @Override
            public void onStart(int what) {
                LoadingDialog loadingDialog = new LoadingDialog(mContext);
                dialog = loadingDialog.showLoadingDialog("");
            }

            @Override
            public void onSucceed(int what, Response<RegisterResult> response) {
                RegisterResult registerResult = response.get();
                if ("200".equals(registerResult.getCode())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setMessage("注册成功！")
                           .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   finish();
                               }
                           })
                           .setCancelable(false)
                           .show();
                }else {
                    Toast.makeText(RegisterActivity.this, registerResult.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int what, Response<RegisterResult> response) {
                Toast.makeText(RegisterActivity.this, "网络异常 "+response.responseCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {
               dialog.dismiss();
            }
        });
    }

    private void checkInput(){
        String phoneNumber = et_account_register.getText().toString();
        String verificationCode = et_verification_register.getText().toString();
        String setPassword = et_password_register.getText().toString();
        String confirmPassword = et_confirm_password_register.getText().toString();
        if (phoneNumber.isEmpty()){
            Toast.makeText(RegisterActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verificationCode.isEmpty()){
            Toast.makeText(RegisterActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (setPassword.isEmpty()){
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmPassword.isEmpty()){
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!setPassword.equals(confirmPassword)){
            Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!cb_protocol_register.isChecked()){
            Toast.makeText(RegisterActivity.this, "请同意用户协议", Toast.LENGTH_SHORT).show();
            return;
        }
        requestRegister(phoneNumber,verificationCode,confirmPassword);
    }

    private void requestVerificationCode(String tel){
        JavaBeanRequest<RegisterCode> re = new JavaBeanRequest<>(Urls.codeUrl,RequestMethod.GET, RegisterCode.class);
        re.add("tel",tel);
        request(0, re, new OnResponseListener<RegisterCode>() {

            @Override
            public void onStart(int what) {
            }

            @Override
            public void onSucceed(int what, Response<RegisterCode> response) {
                RegisterCode registerCode = response.get();
                String code = registerCode.getCode();
                if ("200".equals(code)){
                    Toast.makeText(RegisterActivity.this, "已发送", Toast.LENGTH_SHORT).show();
                    bt_register_verification.setClickable(false);
                    if (System.currentTimeMillis() - time > 120000) {
                        new CountDownTimer(120000, 1000) {
                            StringBuilder stringBuilder = new StringBuilder();
                            public void onTick(long millisUntilFinished) {
                                stringBuilder.delete(0,stringBuilder.length());
                                stringBuilder.append("剩余:");
                                stringBuilder.append(millisUntilFinished / 1000);
                                stringBuilder.append("秒");
                                bt_register_verification.setText(stringBuilder.toString());//"剩余:" + millisUntilFinished / 1000 + "秒"
                            }
                            public void onFinish() {
                                bt_register_verification.setText(R.string.again_validate);
                                time = 0;
                                bt_register_verification.setClickable(true);
                            }
                        }.start();
                    }
                    time = System.currentTimeMillis();
                }else {
                    Toast.makeText(RegisterActivity.this, registerCode.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int what, Response<RegisterCode> response) {
                Toast.makeText(RegisterActivity.this, "网络异常 "+response.responseCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
