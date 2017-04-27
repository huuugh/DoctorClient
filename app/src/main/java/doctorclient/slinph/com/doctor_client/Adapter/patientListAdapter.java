package doctorclient.slinph.com.doctor_client.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import doctorclient.slinph.com.doctor_client.R;

/**
 * Created by Administrator on 2017/1/9.
 */
public class patientListAdapter extends BaseAdapter{

    private Context mContext;

    public patientListAdapter(Context con){
        mContext = con;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newsItemView = View.inflate(mContext, R.layout.patient_list_item, null);
        return newsItemView;
    }
}
