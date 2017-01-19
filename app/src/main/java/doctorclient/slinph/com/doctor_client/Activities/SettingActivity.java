package doctorclient.slinph.com.doctor_client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import doctorclient.slinph.com.doctor_client.R;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private TextView setting_advice;
    private TextView setting_app_share;
    private TextView setting_about;
    private TextView setting_change_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setting_advice = (TextView) findViewById(R.id.setting_advice);
        setting_app_share = (TextView) findViewById(R.id.setting_app_share);
        setting_about = (TextView) findViewById(R.id.setting_about);
        setting_change_pw = (TextView) findViewById(R.id.setting_change_pw);
    }

    @Override
    protected void initEvent() {
        setting_advice.setOnClickListener(this);
        setting_app_share.setOnClickListener(this);
        setting_about.setOnClickListener(this);
        setting_change_pw.setOnClickListener(this);
    }

    @Override
    protected int setToolbarIcon() {
        return 0;
    }

    @Override
    protected String setToolbarTitle() {
        return "设置";
    }

    @Override
    protected boolean toolbarState() {
        return true;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_setting;
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_advice:
                startActivity(new Intent(this,AdviceActivity.class));
                break;
            case R.id.setting_app_share:
                startActivity(new Intent(this,AppShareActivity.class));
                break;
            case R.id.setting_about:
                break;
            case R.id.setting_change_pw:
                startActivity(new Intent(this,ForgetPWActivity.class));
                break;
        }
    }
}
