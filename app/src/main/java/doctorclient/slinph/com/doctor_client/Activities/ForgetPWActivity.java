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
import doctorclient.slinph.com.doctor_client.Utils.SharePreferencesUtils;
import doctorclient.slinph.com.doctor_client.Utils.ValidateUtils;
import doctorclient.slinph.com.doctor_client.Views.LoadingDialog;

public class ForgetPWActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_change_account;
    private EditText et_change_verCode;
    private Button bt_change_getCode;
    private EditText et_change_password;
    private EditText et_confirmChange_password;
    private CheckBox cb_change_protocol;
    private Button bt_confirm_change;
    private ForgetPWActivity mContext;
    private double time;

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
        return "忘记密码";
    }

    @Override
    protected boolean toolbarState() {
        return true;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_forget_pw;
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
        et_change_account = (EditText) findViewById(R.id.et_change_account);
        et_change_verCode = (EditText) findViewById(R.id.et_change_verCode);
        bt_change_getCode = (Button) findViewById(R.id.bt_change_getCode);
        et_change_password = (EditText) findViewById(R.id.et_change_password);
        et_confirmChange_password = (EditText) findViewById(R.id.et_confirmChange_password);
        cb_change_protocol = (CheckBox) findViewById(R.id.cb_change_protocol);
        bt_confirm_change = (Button) findViewById(R.id.bt_confirm_change);
    }

    @Override
    protected void initEvent() {
        if (bt_change_getCode != null){
            bt_change_getCode.setOnClickListener(this);
        }
        if (bt_confirm_change != null){
            bt_confirm_change.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_change_getCode:
                String account = et_change_account.getText().toString();
                if (!account.isEmpty()){
                    if (ValidateUtils.validateMobile(account)){
                        requestVerificationCode(account);
                    }else {
                        Toast.makeText(ForgetPWActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ForgetPWActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bt_confirm_change:
                checkChangePswInput();
                break;
        }
    }

    private void checkChangePswInput() {
        String phoneNumber = et_change_account.getText().toString();
        String verificationCode = et_change_verCode.getText().toString();
        String setPassword = et_change_password.getText().toString();
        String confirmPassword = et_confirmChange_password.getText().toString();
        if (phoneNumber.isEmpty()){
            Toast.makeText(mContext, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (verificationCode.isEmpty()){
            Toast.makeText(mContext, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (setPassword.isEmpty()){
            Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confirmPassword.isEmpty()){
            Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!setPassword.equals(confirmPassword)){
            Toast.makeText(mContext, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!cb_change_protocol.isChecked()){
            Toast.makeText(mContext, "请同意用户协议", Toast.LENGTH_SHORT).show();
            return;
        }
        requestChangePsw(phoneNumber,verificationCode,confirmPassword);
    }

    private void requestChangePsw(String tel, String code, String psw) {
        final JavaBeanRequest<RegisterResult> request = new JavaBeanRequest<>(Urls.changePswUrl,RequestMethod.POST, RegisterResult.class);
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
                    builder.setMessage("修改成功！")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharePreferencesUtils.putString(mContext, "LOGIN_ACCOUNT", "");
                                    SharePreferencesUtils.putString(mContext, "LOGIN_PSW", "");
                                    finish();
                                }
                            })
                            .setCancelable(false)
                            .show();
                }else {
                    Toast.makeText(mContext, registerResult.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int what, Response<RegisterResult> response) {
                Toast.makeText(mContext, "网络异常 "+response.responseCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {
                dialog.dismiss();
            }
        });
    }

    private void requestVerificationCode(String tel){
        JavaBeanRequest<RegisterCode> re = new JavaBeanRequest<>(Urls.codeUrl, RequestMethod.GET, RegisterCode.class);
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
                    Toast.makeText(mContext, "已发送", Toast.LENGTH_SHORT).show();
                    bt_change_getCode.setClickable(false);
                    if (System.currentTimeMillis() - time > 120000) {
                        new CountDownTimer(120000, 1000) {
                            StringBuilder stringBuilder = new StringBuilder();
                            public void onTick(long millisUntilFinished) {
                                stringBuilder.delete(0,stringBuilder.length());
                                stringBuilder.append("剩余:");
                                stringBuilder.append(millisUntilFinished / 1000);
                                stringBuilder.append("秒");
                                bt_change_getCode.setText(stringBuilder.toString());//"剩余:" + millisUntilFinished / 1000 + "秒"
                            }
                            public void onFinish() {
                                bt_change_getCode.setText(R.string.again_validate);
                                time = 0;
                                bt_change_getCode.setClickable(true);
                            }
                        }.start();
                    }
                    time = System.currentTimeMillis();
                }else {
                    Toast.makeText(mContext, registerCode.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(int what, Response<RegisterCode> response) {
                Toast.makeText(mContext, "网络异常 "+response.responseCode(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
