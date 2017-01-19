package doctorclient.slinph.com.doctor_client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

import doctorclient.slinph.com.doctor_client.R;

public class WelcomeActivity extends BaseActivity {

    private RelativeLayout rl_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                        startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                        finish();
                    }
                };
                timer.schedule(task, 1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
