package ca.keithweaver.openinstagram.View.Windows.Main;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.keithweaver.openinstagram.R;

/**
 * Created by keithweaver on 16-07-19.
 */
public class FeedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    protected Activity mActivity;
    private static final String TAG = FeedFragment.class.getSimpleName();

    SwipeRefreshLayout swipeLayout;

    @Override
    public void onAttach(Activity act)
    {
        super.onAttach(act);
        mActivity = act;

    }

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState)
    {
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        view = declareUIObjects(view);

        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorSchemeColors(android.R.color.holo_purple, android.R.color.holo_red_light);//bug here, not same as demo cause it was depreciated



        return view;
    }


    @Override
    public void onResume(){
        super.onResume();


    }


    protected View declareUIObjects(View view){
//        mainSolutionsListView = (ListView) view.findViewById(R.id.mainSolutionsListView);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);

        return view;

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
            }
        }, 5000);
    }
}
