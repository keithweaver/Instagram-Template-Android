package ca.keithweaver.openinstagram.View.Windows.Main;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import ca.keithweaver.openinstagram.R;
import ca.keithweaver.openinstagram.View.Windows.LogIn.LogInFragment;
import ca.keithweaver.openinstagram.View.Windows.LogIn.SignUpFragment;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * Created by keithweaver on 16-07-19.
 */
public class MainActivity extends AppCompatActivity implements MaterialTabListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ViewPager pager;
    private ViewPagerAdapter pagerAdapter;
    MaterialTabHost tabHost;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        res = this.getResources();

        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        pager = (ViewPager) this.findViewById(R.id.searchToolbar);

        // init view pager
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setIcon(getIcons(i))
                            .setTabListener(this)
            );
        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int num) {
            Log.v(TAG, String.valueOf(num));
            switch (num) {
                case 0:
                    return new FeedFragment();
                case 1:
                    return new ExploreFragment();
                case 2:
                    return new PostFragment();
                case 3:
                    return new FriendsFragment();
                case 4:
                    return new ProfileFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 5;
        }

    }
    /*
    * It doesn't matter the color of the icons, but they must have solid colors
    */
    private Drawable getIcons(int position) {
        switch (position) {
            case 0:
                return res.getDrawable(R.drawable.home);
            case 1:
                return res.getDrawable(R.drawable.magnify);
            case 2:
                return res.getDrawable(R.drawable.upload);
            case 3:
                return res.getDrawable(R.drawable.fullheart);
            case 4:
                return res.getDrawable(R.drawable.profile);
        }
        return null;
    }
}