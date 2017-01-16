package doctorclient.slinph.com.doctor_client.Activities;

import android.os.Bundle;
import android.view.View;

import doctorclient.slinph.com.doctor_client.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {

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
}
