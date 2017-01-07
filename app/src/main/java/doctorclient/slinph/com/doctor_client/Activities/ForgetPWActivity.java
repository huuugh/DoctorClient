package doctorclient.slinph.com.doctor_client.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import doctorclient.slinph.com.doctor_client.R;

public class ForgetPWActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }
        setTitle();
    }

    @Override
    protected int setToolbarIcon() {
        return R.drawable.arrow_left;
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

    }

    @Override
    protected void initEvent() {

    }
}
