package doctorclient.slinph.com.doctor_client.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hugh on 2017/1/6.
 *
 */
public abstract class BaseFragment extends Fragment{

    protected Activity mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        initViewsEvent();
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
        /*int layoutId = addFragmentLayout();
        View diagnosisView;
        if (layoutId != 0){
            diagnosisView = LayoutInflater.from(mContext).inflate(layoutId, null);
        }else {
            diagnosisView = super.onCreateView(inflater, container, savedInstanceState);
        }*/

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
