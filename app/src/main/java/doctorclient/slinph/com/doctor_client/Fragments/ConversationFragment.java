package doctorclient.slinph.com.doctor_client.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import doctorclient.slinph.com.doctor_client.R;
import doctorclient.slinph.com.doctor_client.Utils.Globalvariable;
import io.rong.imkit.RongIM;

/**
 * Created by hugh on 2017/1/6.
 *
 */
public class ConversationFragment extends BaseFragment implements View.OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String setFragmentTitle() {
        return "咨询";
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected View addFragmentLayout() {
        View diagnosisView = LayoutInflater.from(mContext).inflate(R.layout.fragment_communicate_layout, null);
        /*ListView lv_patient = (ListView) diagnosisView.findViewById(R.id.lv_patient);
        lv_patient.setAdapter(new patientListAdapter(mContext));
        lv_patient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(mContext, PatientDetailActivity.class));
            }
        });*/
        Button bt_conversation = (Button) diagnosisView.findViewById(R.id.bt_conversation);
        bt_conversation.setOnClickListener(this);
        return diagnosisView;
    }

    @Override
    public void onClick(View v) {
        if (RongIM.getInstance() != null){
            RongIM.getInstance().startPrivateChat(mContext, Globalvariable.RONG_USERID,Globalvariable.REAL_NAME);
        }
    }
}
