package doctorclient.slinph.com.doctor_client.Views;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import doctorclient.slinph.com.doctor_client.R;

/**
 * Created by Hugh on 2017/4/14.
 *
 */
public class LoadingDialog {

    private static AlertDialog.Builder builder;
    private static Context mContext;

    public LoadingDialog(Context context){
        builder = new AlertDialog.Builder(context);
        this.mContext = context;
    }

    public AlertDialog showLoadingDialog(String tip){
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.loadingdialog, null);
        TextView tv_loading_tip = (TextView) dialogView.findViewById(R.id.tv_loading_tip);
        tv_loading_tip.setText(tip);
        builder.setView(dialogView);
        AlertDialog dialog = builder.show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_round_dialog,null));
        }else {
            dialog.getWindow().setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.bg_round_dialog));
        }
        return dialog;
    }
}
