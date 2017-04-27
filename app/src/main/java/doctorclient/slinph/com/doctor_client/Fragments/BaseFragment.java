package doctorclient.slinph.com.doctor_client.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created by hugh on 2017/1/6.
 *
 */
public abstract class BaseFragment extends Fragment {

    protected Activity mContext;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        initViewsEvent();
        mRequestQueue = NoHttp.newRequestQueue();
    }

    public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
        mRequestQueue.add(what, request, listener);
    }

    @Override
    public void onDestroy() {
        mRequestQueue.cancelAll();
        mRequestQueue.stop();
    }

    protected void initViewsEvent(){

    }

    public String getFragmentTitle(){
        return setFragmentTitle();
    }

    protected abstract String setFragmentTitle();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View diagnosisView;

        View view = addFragmentLayout();
        if (view != null){
            diagnosisView = view;
        }else {
            diagnosisView = super.onCreateView(inflater, container, savedInstanceState);
        }
        return diagnosisView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract View addFragmentLayout();
}
