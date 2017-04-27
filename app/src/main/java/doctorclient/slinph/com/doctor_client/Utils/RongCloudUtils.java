package doctorclient.slinph.com.doctor_client.Utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import doctorclient.slinph.com.doctor_client.Application.MyApplication;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2017/4/19.
 *
 */
public class RongCloudUtils {

    public static void connect(Context context,String token) {
        if (context.getApplicationInfo().packageName.equals(MyApplication.getCurProcessName(context.getApplicationContext()))) {
            if (RongIM.getInstance() != null){
                RongIM.connect(token, new RongIMClient.ConnectCallback() {

                    @Override
                    public void onTokenIncorrect() {

                    }

                    @Override
                    public void onSuccess(String userid) {
                        Log.e("LoginActivity", "--onSuccess" + userid);
                    /*startActivity(new Intent(LoginActivity.this, io.rong.imkit.MainActivity.class));
                    finish();*/

                        Globalvariable.RONG_USERID = userid;
                        if (RongIM.getInstance() != null && Globalvariable.HEAD_IMAGE != null){
                            if (!Globalvariable.HEAD_IMAGE.isEmpty()){
                                RongIM.getInstance().setCurrentUserInfo(new UserInfo(userid,Globalvariable.REAL_NAME, Uri.parse(Globalvariable.HEAD_IMAGE)));
                            }
                        }
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {
                        Log.e("LoginActivity", "--onError" + errorCode);
                    }
                });
            }
        }
    }

}
