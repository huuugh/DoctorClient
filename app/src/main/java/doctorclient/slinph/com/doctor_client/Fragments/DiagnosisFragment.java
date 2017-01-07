package doctorclient.slinph.com.doctor_client.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import doctorclient.slinph.com.doctor_client.R;

/**
 * Created by hugh on 2017/1/6.
 *
 */
public class DiagnosisFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected String setFragmentTitle() {
        return "医生诊疗";
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected View addFragmentLayout() {
        View diagnosisView = LayoutInflater.from(mContext).inflate(R.layout.fragment_diagnosis_layout, null);
        return diagnosisView;
    }

}