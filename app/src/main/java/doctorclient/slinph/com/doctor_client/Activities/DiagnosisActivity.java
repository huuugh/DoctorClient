package doctorclient.slinph.com.doctor_client.Activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

import doctorclient.slinph.com.doctor_client.R;
import doctorclient.slinph.com.doctor_client.Utils.UIUtils;
import doctorclient.slinph.com.doctor_client.Views.flowlayout.FlowLayout;
import doctorclient.slinph.com.doctor_client.Views.flowlayout.TagAdapter;
import doctorclient.slinph.com.doctor_client.Views.flowlayout.TagFlowLayout;

public class DiagnosisActivity extends BaseActivity {

    private TagFlowLayout fl_all_stage;
    private TagFlowLayout fl_current_stage;
    private LayoutInflater mInflater;
    private Spinner spinner_loss_type;
    private Spinner spinner_loss_degree;
    private Spinner spinner_loss_disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        fl_all_stage = (TagFlowLayout) findViewById(R.id.fl_all_stage);
        fl_current_stage = (TagFlowLayout) findViewById(R.id.fl_current_stage);
        spinner_loss_type = (Spinner) findViewById(R.id.spinner_loss_type);
        spinner_loss_degree = (Spinner) findViewById(R.id.spinner_loss_degree);
        spinner_loss_disease = (Spinner) findViewById(R.id.spinner_loss_disease);
        mInflater = LayoutInflater.from(DiagnosisActivity.this);
        fillTagAdater(fl_all_stage,new String[]{"消炎","控油","止脱","生发","粗发"});
        fillTagAdater(fl_current_stage,new String[]{"消炎","控油","止脱","生发","粗发"});
    }

    @Override
    protected void initEvent() {
    }

    /**
     * 填充标签数据
     *
     * @param view
     */
    private void fillTagAdater(final TagFlowLayout view, final String[] vals) {
        fillTagListener(view, vals);
        view.setAdapter(new TagAdapter<String>(vals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                //默认为多选样式
                TextView tv = (TextView) mInflater.inflate(R.layout.flowlayout_tagview_item, view, false);
                tv.setText(s);
                //单选时样式改变
                if (view.getMaxSelectCount() == 1) {
                    //距顶端距离(内边距)
                    tv.setBackground(getResources().getDrawable(R.drawable.tagview_radio_bg));
                    tv.setTextColor(getResources().getColorStateList(R.color.tagview_edit_color));
                    //距顶端距离(内边距)
                    tv.setPadding(UIUtils.dp2px(5), UIUtils.dp2px(5), UIUtils.dp2px(5), UIUtils.dp2px(5));
                    tv.setTextSize(16);
                    //设置控件长宽
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, UIUtils.dp2px(30));
                    //外边距
                    layoutParams.setMargins(UIUtils.dp2px(5), UIUtils.dp2px(5), UIUtils.dp2px(5), UIUtils.dp2px(5));
                    tv.setLayoutParams(layoutParams);
                }
                return tv;
            }
        });
    }

    private void fillTagListener(final TagFlowLayout view, final String[] vals) {
        view.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                StringBuilder sb = new StringBuilder();
                for (Integer i : selectPosSet) {
                    sb.append(vals[i]).append(",");//用于多选,可以获取多选信息
                }
                String str = sb.toString();
                if (!TextUtils.isEmpty(str)) {
                    str = str.substring(0, str.length() - 1);//去掉最后一个逗号
                }

                switch (view.getId()){
                    case R.id.fl_all_stage:
                        Toast.makeText(DiagnosisActivity.this, str, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.fl_current_stage:
                        Toast.makeText(DiagnosisActivity.this, str, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    @Override
    protected int setToolbarIcon() {
        return 0;
    }

    @Override
    protected String setToolbarTitle() {
        return "诊断";
    }

    @Override
    protected boolean toolbarState() {
        return true;
    }

    @Override
    protected int addLayoutId() {
        return R.layout.activity_diagnosis;
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
