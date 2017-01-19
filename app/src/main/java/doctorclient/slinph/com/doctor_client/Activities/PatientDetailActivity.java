package doctorclient.slinph.com.doctor_client.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import doctorclient.slinph.com.doctor_client.R;

public class PatientDetailActivity extends BaseActivity implements View.OnClickListener {

    private Button bt_diagnosis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        bt_diagnosis = (Button) findViewById(R.id.bt_diagnosis);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_diagnosis:
                startActivity(new Intent(this,DiagnosisActivity.class));
                break;
        }
    }

    @Override
    protected void initEvent() {
        bt_diagnosis.setOnClickListener(this);
    }

    @Override
    protected int setToolbarIcon() {
        return 0;
    }

    @Override
    protected String setToolbarTitle() {
        return "患者详情";
    }

    @Override
    protected boolean toolbarState() {
        return true;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_patient_detail;
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
