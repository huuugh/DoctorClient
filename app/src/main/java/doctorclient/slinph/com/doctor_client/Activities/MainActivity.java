package doctorclient.slinph.com.doctor_client.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import doctorclient.slinph.com.doctor_client.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected int setToolbarIcon() {
        return R.drawable.arrow_left;
    }

    @Override
    protected String setToolbarTitle() {
        return "主页";
    }

    @Override
    protected boolean toolbarState() {
        return true;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected View.OnClickListener setNavigationAction() {
        return null;
    }
}
