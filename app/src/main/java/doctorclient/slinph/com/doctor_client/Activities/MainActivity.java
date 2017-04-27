package doctorclient.slinph.com.doctor_client.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import doctorclient.slinph.com.doctor_client.Fragments.ConversationFragment;
import doctorclient.slinph.com.doctor_client.Fragments.DiagnosisFragment;
import doctorclient.slinph.com.doctor_client.Fragments.MeFragment;
import doctorclient.slinph.com.doctor_client.Fragments.NewsFragment;
import doctorclient.slinph.com.doctor_client.R;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class MainActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener{

    private DiagnosisFragment diagnosisFragment;
    private NewsFragment newsFragment;
    private MeFragment meFragment;
    private RadioButton rb_diagnosis;
    private RadioButton rb_news;
    private RadioButton rb_me;
    private RadioGroup rg_tab;
    private RadioButton rb_communicate;
    private ConversationFragment communicateFragment;
    private MainActivity mContext;
    private ConversationListFragment conversationListFragment;
    private android.support.v4.app.FragmentManager mFragmentManager;
    private Fragment mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null){
            supportActionBar.hide();
        }*/
        //communicateFragment = new ConversationFragment();
        diagnosisFragment = new DiagnosisFragment();
        newsFragment = new NewsFragment();
        meFragment = new MeFragment();
        conversationListFragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        conversationListFragment.setUri(uri);

        mContext = this;

        mFragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!conversationListFragment.isAdded()){
            fragmentTransaction.add(R.id.fl_main,conversationListFragment);
            fragmentTransaction.commit();
            mContent = conversationListFragment;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void initView() {
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        rb_diagnosis = (RadioButton) findViewById(R.id.rb_diagnosis);
        rb_news = (RadioButton) findViewById(R.id.rb_news);
        rb_me = (RadioButton) findViewById(R.id.rb_me);
        rb_communicate = (RadioButton) findViewById(R.id.rb_communicate);
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
        if (rb_communicate != null){
            rb_communicate.setOnCheckedChangeListener(this);
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
            case R.id.rb_communicate:
                if (isChecked) {
                    switchFragment(mContent, conversationListFragment);
                    super.setTitle();
                }
                break;
        }
    }

    public void switchFragment(Fragment outset, Fragment target) {
        if (mContent != target) {
            mContent = target;
            android.support.v4.app.FragmentTransaction transaction = mFragmentManager.beginTransaction();
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
        String title = "";
        if (mContent == conversationListFragment){
            title = "咨询";
        }else if (mContent == diagnosisFragment){
            title = diagnosisFragment.getFragmentTitle();
        }else if(mContent == newsFragment){
            title = newsFragment.getFragmentTitle();
        }else{
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


    private long mPressedTime = 0;
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        if((mNowTime - mPressedTime) > 2000){
            Toast.makeText(this, R.string.double_press_exit, Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else{
            this.finish();
            System.exit(0);
        }
    }
}
