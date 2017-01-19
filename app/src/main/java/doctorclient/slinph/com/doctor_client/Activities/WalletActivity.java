package doctorclient.slinph.com.doctor_client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import doctorclient.slinph.com.doctor_client.R;

public class WalletActivity extends BaseActivity implements View.OnClickListener{

    private TextView wallet_withdraw;
    private TextView wallet_billing;
    private TextView wallet_bankcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        wallet_withdraw = (TextView) findViewById(R.id.wallet_withdraw);
        wallet_billing = (TextView) findViewById(R.id.wallet_billing);
        wallet_bankcard = (TextView) findViewById(R.id.wallet_bankcard);
    }

    @Override
    protected void initEvent() {
        if (wallet_withdraw != null) {
            wallet_withdraw.setOnClickListener(this); }
        if (wallet_billing != null){
            wallet_billing.setOnClickListener(this); }
        if (wallet_bankcard != null){
            wallet_bankcard.setOnClickListener(this); }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.wallet_withdraw:
                View dialogView = View.inflate(this, R.layout.withdraw_dialog, null);
                ImageButton ib_dialog_cancel = (ImageButton) dialogView.findViewById(R.id.ib_dialog_cancel);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setView(dialogView);
                AlertDialog dialog = builder.show();
                final AlertDialog finalDialog = dialog;
                ib_dialog_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finalDialog.dismiss();
                    }
                });
                break;
            case R.id.wallet_billing:
                startActivity(new Intent(this,WithdrawalsRecordActivity.class));
                break;
            case R.id.wallet_bankcard:
                startActivity(new Intent(this,BindBankCardActivity.class));
                break;
        }
    }

    @Override
    protected int setToolbarIcon() {
        return 0;
    }

    @Override
    protected String setToolbarTitle() {
        return "我的钱包";
    }

    @Override
    protected boolean toolbarState() {
        return true;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_wallet;
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
