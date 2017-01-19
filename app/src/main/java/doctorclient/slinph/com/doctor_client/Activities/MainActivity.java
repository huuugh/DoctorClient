package doctorclient.slinph.com.doctor_client.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import doctorclient.slinph.com.doctor_client.Fragments.DiagnosisFragment;
import doctorclient.slinph.com.doctor_client.Fragments.MeFragment;
import doctorclient.slinph.com.doctor_client.Fragments.NewsFragment;
import doctorclient.slinph.com.doctor_client.R;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener{

    private DiagnosisFragment diagnosisFragment;
    private NewsFragment newsFragment;
    private MeFragment meFragment;
    private RadioButton rb_diagnosis;
    private RadioButton rb_news;
    private RadioButton rb_me;
    private FragmentManager mFragmentManager;
    private Fragment mContent;
    private RadioGroup rg_tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }
        diagnosisFragment = new DiagnosisFragment();
        newsFragment = new NewsFragment();
        meFragment = new MeFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!diagnosisFragment.isAdded()){
            fragmentTransaction.add(R.id.fl_main,diagnosisFragment);
            fragmentTransaction.commit();
            mContent = diagnosisFragment;
        }
    }

    @Override
    protected void initView() {
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        rb_diagnosis = (RadioButton) findViewById(R.id.rb_diagnosis);
        rb_news = (RadioButton) findViewById(R.id.rb_news);
        rb_me = (RadioButton) findViewById(R.id.rb_me);
    }

    @Override
    protected void initEvent() {
        if (rb_diagnosis != null){
            rb_diagnosis.setOnCheckedChangeListener(this);
        }
        if (rb_news != null){
            rb_news.setOnCheckedChangeListener(this);
        }
        if (rb_me != null){
            rb_me.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.rb_diagnosis:
                if (isChecked){
                    switchFragment(mContent,diagnosisFragment);
                    super.setTitle();
                }
                break;
            case R.id.rb_news:
                if (isChecked) {
                    switchFragment(mContent, newsFragment);
                    super.setTitle();
                }
                break;
            case R.id.rb_me:
                if (isChecked) {
                    switchFragment(mContent, meFragment);
                    super.setTitle();
                }
                break;
        }
    }

    public void switchFragment(Fragment outset, Fragment target) {
        if (mContent != target) {
            mContent = target;
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            if (!target.isAdded()) {// 先判断是否被add过
                transaction.hide(outset).add(R.id.fl_main, target).commit();// 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(outset).show(target).commit();// 隐藏当前的fragment，显示下一个
            }
        }
    }

    @Override
    protected int setToolbarIcon() {
        return R.drawable.arrow_left_transparent;
    }

    @Override
    protected String setToolbarTitle() {
        String title;
        if (mContent == diagnosisFragment){
            title = diagnosisFragment.getFragmentTitle();
        }else if(mContent == newsFragment){
            title = newsFragment.getFragmentTitle();
        }else {
            title = meFragment.getFragmentTitle();
        }
        return title;
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
