package ca.keithweaver.openinstagram.View.Windows.LogIn;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import ca.keithweaver.openinstagram.R;

/**
 * Created by keithweaver on 16-07-19.
 */
public class LogInFragment extends Fragment {
    protected Activity mActivity;
    private static final String TAG = LogInFragment.class.getSimpleName();



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
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        view = declareUIObjects(view);



        return view;
    }


    @Override
    public void onResume(){
        super.onResume();


    }


    protected View declareUIObjects(View view){
//        mainSolutionsListView = (ListView) view.findViewById(R.id.mainSolutionsListView);
        return view;

    }
}