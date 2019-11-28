package com.metacodersbd.watch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.metacodersbd.watch.adapter.viewPagerAdapter;
import com.metacodersbd.watch.fragments.fragment_dashboard;
import com.metacodersbd.watch.fragments.searchFragment;
import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // initaling the one signal
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        final BubbleNavigationLinearView bubbleNavigationLinearView = findViewById(R.id.bottom_navigation_view_linear) ;


        // my view pager

        viewPagerAdapter adapter = new viewPagerAdapter( getSupportFragmentManager() ) ;


        adapter.addFragment( new fragment_dashboard(), "Homepage"); // loc == 0
        adapter.addFragment(new searchFragment() , "Search");//loc ==1




        final ViewPager viewPager = findViewById(R.id.fragmentContainer) ;
        viewPager.setAdapter(adapter) ;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                bubbleNavigationLinearView.setCurrentActiveItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        bubbleNavigationLinearView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {

                viewPager.setCurrentItem(position , true);

            }
        });




    }

}
