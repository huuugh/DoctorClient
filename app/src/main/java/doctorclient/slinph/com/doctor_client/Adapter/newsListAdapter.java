package doctorclient.slinph.com.doctor_client.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import doctorclient.slinph.com.doctor_client.R;

/**
 * Created by Administrator on 2017/1/7.
 *
 */
public class newsListAdapter extends BaseAdapter {

    private Context mContext;

    public newsListAdapter(Context con){
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
        View newsItemView = View.inflate(mContext, R.layout.news_item, null);
        SimpleDraweeView sdv_news_pic = (SimpleDraweeView) newsItemView.findViewById(R.id.sdv_news_pic);
        Uri uri = Uri.parse("http://img.taopic.com/uploads/allimg/140506/240419-1405060Z30831.jpg");
        sdv_news_pic.setImageURI(uri);
        TextView tv_news_title = (TextView) newsItemView.findViewById(R.id.tv_news_title);
        return newsItemView;
    }
}
