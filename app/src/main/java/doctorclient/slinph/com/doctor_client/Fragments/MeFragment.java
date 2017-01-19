package doctorclient.slinph.com.doctor_client.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import doctorclient.slinph.com.doctor_client.Activities.PersonalCodeActivity;
import doctorclient.slinph.com.doctor_client.Activities.SettingActivity;
import doctorclient.slinph.com.doctor_client.Activities.VerificationActivity;
import doctorclient.slinph.com.doctor_client.Activities.WalletActivity;
import doctorclient.slinph.com.doctor_client.R;
import doctorclient.slinph.com.doctor_client.Views.CircleImageView;

/**
 * Created by hugh on 2017/1/6.
 *
 */
public class MeFragment extends BaseFragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String setFragmentTitle() {
        return "我的";
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected View addFragmentLayout() {
        View meView = LayoutInflater.from(mContext).inflate(R.layout.fragment_me_layout, null);
        TextView me_setting = (TextView) meView.findViewById(R.id.me_setting);
        TextView me_pocket = (TextView) meView.findViewById(R.id.me_pocket);
        TextView me_card = (TextView) meView.findViewById(R.id.me_card);
        CircleImageView civ_me_head = (CircleImageView) meView.findViewById(R.id.civ_me_head);
        if (civ_me_head != null){ civ_me_head.setOnClickListener(this); }
        if (me_setting != null){ me_setting.setOnClickListener(this); }
        if (me_pocket != null){ me_pocket.setOnClickListener(this); }
        if (me_card != null){ me_card.setOnClickListener(this); }
        return meView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.me_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.me_pocket:
                startActivity(new Intent(mContext, WalletActivity.class));
                break;
            case R.id.me_card:
                startActivity(new Intent(mContext, PersonalCodeActivity.class));
                break;
            case R.id.civ_me_head:
                startActivity(new Intent(mContext, VerificationActivity.class));
                break;
        }
    }
}
