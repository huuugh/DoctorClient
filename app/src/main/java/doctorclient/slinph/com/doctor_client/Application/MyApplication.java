package doctorclient.slinph.com.doctor_client.Application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

import io.rong.imkit.RongIM;

/**
 * Created by hugh on 2017/1/7.
 *
 */
public class MyApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //初始化Fresco
        Fresco.initialize(this);
        //初始化NoHttp
        NoHttp.initialize(this,new NoHttp.Config()
                .setConnectTimeout(15*1000)
                .setReadTimeout(15*1000)
                .setNetworkExecutor(new OkHttpNetworkExecutor()));
        //初始化RongCloud
        RongIM.init(this);
    }


    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;
            }
        }
        return null;
    }
}
