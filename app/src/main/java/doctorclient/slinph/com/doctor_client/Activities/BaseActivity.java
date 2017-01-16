package doctorclient.slinph.com.doctor_client.Activities;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import doctorclient.slinph.com.doctor_client.R;

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar tb_base;
    private TextView tv_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //very important
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//set screen orientation
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//set no title
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_base);
        tb_base = (Toolbar) findViewById(R.id.tb_base);
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
        FrameLayout fl_base = (FrameLayout) findViewById(R.id.fl_base);
        View contentLayout = LayoutInflater.from(this).inflate(addLayoutId(), null);
        if (fl_base != null){
            fl_base.addView(contentLayout);
        }
        if (toolbarState()){
            tb_base.setVisibility(View.VISIBLE);
        }else {
            tb_base.setVisibility(View.GONE);
        }

        int icon = setToolbarIcon();
        if (icon != 0){
            tb_base.setNavigationIcon(icon);
        }else {
            tb_base.setNavigationIcon(R.drawable.arrow_left);
        }

        if (setNavigationAction() != null){
            tb_base.setNavigationOnClickListener(setNavigationAction());
        }

        initView();
        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setTitle();
    }

    public void setTitle(){
        String title = setToolbarTitle();
        if (!title.isEmpty()&&tv_toolbar != null){
            tv_toolbar.setText(title);
        }
    }

    public Toolbar getTb_base() {
        return tb_base;
    }

    /**
     * @return status_bar's height
     */
    private int getStatusBarHeight(){
        try
        {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return  getResources().getDimensionPixelSize(x);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract int setToolbarIcon();

    protected abstract String setToolbarTitle();

    protected abstract boolean toolbarState();

    protected abstract int addLayoutId();

    protected abstract View.OnClickListener setNavigationAction();
}
