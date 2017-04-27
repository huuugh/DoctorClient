package doctorclient.slinph.com.doctor_client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Timer;
import java.util.TimerTask;

import doctorclient.slinph.com.doctor_client.Internet.JavaBeanRequest;
import doctorclient.slinph.com.doctor_client.Internet.RequestBean.LoginResult;
import doctorclient.slinph.com.doctor_client.Internet.Urls;
import doctorclient.slinph.com.doctor_client.R;
import doctorclient.slinph.com.doctor_client.Utils.RongCloudUtils;
import doctorclient.slinph.com.doctor_client.Utils.SharePreferencesUtils;
import doctorclient.slinph.com.doctor_client.Views.LoadingDialog;

public class WelcomeActivity extends BaseActivity {

    private RelativeLayout rl_welcome;
    private WelcomeActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        AnimationSet animationSet = new AnimationSet(false);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0.3F, 1.0F, 0.3F, 1.0F,Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5F);
        scaleAnimation.setDuration(3000);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5F, 1.0F);
        alphaAnimation.setDuration(3000);
        RotateAnimation rotateAnimation = new RotateAnimation(0L, 360L, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
        rotateAnimation.setDuration(3000);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        rl_welcome.startAnimation(animationSet);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        autoLogin();
                    }
                };
                timer.schedule(task, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void autoLogin() {
        boolean isAutoLogin = SharePreferencesUtils.getBoolean(mContext, "IS_AUTO_LOGIN",false);
        if (isAutoLogin){
            String savedAccount = SharePreferencesUtils.getString(mContext, "LOGIN_ACCOUNT","");
            String savedPsw = SharePreferencesUtils.getString(mContext, "LOGIN_PSW", "");
            if (!savedAccount.isEmpty() && !savedPsw.isEmpty()){
                final JavaBeanRequest<LoginResult> request = new JavaBeanRequest<>(Urls.loginUrl, RequestMethod.POST, LoginResult.class);
                request.add("tel",savedAccount);
                request.add("passwd",savedPsw);

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
                            //LoginResult.User data = loginResult.getData();
                            startActivity(new Intent(mContext,MainActivity.class));
                            finish();
                            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                            RongCloudUtils.connect(mContext,"ClMOdS+6aMyDJ2rmrvP0qu3oB5M9l1ON2szCfCKLZM1wA4Iwjmjy98Esl5lFsdu4MacR2mvTv4Pd/PxUwgDgSQ==");
                        }else {
                            startActivity(new Intent(mContext,LoginActivity.class));
                            finish();
                            Toast.makeText(mContext, loginResult.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(int what, Response<LoginResult> response) {
                        startActivity(new Intent(mContext,LoginActivity.class));
                        finish();
                        Toast.makeText(mContext, "网络异常 "+response.responseCode(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish(int what) {
                        dialog.dismiss();
                    }
                });
            }
        }else {
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }

    @Override
    protected void initView() {
        rl_welcome = (RelativeLayout) findViewById(R.id.rl_welcome);
    }

    @Override
    protected void initEvent() {

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
        return R.layout.activity_welcome;
    }

    @Override
    protected View.OnClickListener setNavigationAction() {
        return null;
    }
}
