package doctorclient.slinph.com.doctor_client.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import doctorclient.slinph.com.doctor_client.Adapter.newsListAdapter;
import doctorclient.slinph.com.doctor_client.R;

/**
 * Created by hugh on 2017/1/6.
 *
 */
public class NewsFragment extends BaseFragment {

    private TextView tv_state;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViewsEvent() {
        super.initViewsEvent();
    }

    @Override
    protected String setFragmentTitle() {
        return "资讯";
    }

    @Override
    protected View addFragmentLayout() {
        View newsView = LayoutInflater.from(mContext).inflate(R.layout.fragment_news_layout, null);
        //tv_state = (TextView)newsView.findViewById(R.id.tv_state);
        swipeRefreshLayout = (SwipeRefreshLayout)newsView.findViewById(R.id.srl_news);
        //设置刷新时动画的颜色，可以设置4个
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //tv_state.setText("正在刷新");
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        //tv_state.setText("刷新完成");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 6000);
            }
        });

        ListView lv_news_list = (ListView) newsView.findViewById(R.id.lv_news_list);
        if (lv_news_list != null){
            lv_news_list.setAdapter(new newsListAdapter(mContext));
        }
        return newsView;
    }

}
