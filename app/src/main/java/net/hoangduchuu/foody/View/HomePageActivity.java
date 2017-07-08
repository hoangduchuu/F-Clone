package net.hoangduchuu.foody.View;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.hoangduchuu.foody.Adapter.AdapterViewPagerHomePage;
import net.hoangduchuu.foody.R;

import static net.hoangduchuu.foody.R.id.viewPagerHomePage;

public class HomePageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    FirebaseAuth mAuth;
    ViewPager viewPagerHome;
    RadioButton rd_oDau, rd_anGi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance();
        viewPagerHome = (ViewPager) findViewById(viewPagerHomePage);
        AdapterViewPagerHomePage adapterViewPagerHomePage = new AdapterViewPagerHomePage(getSupportFragmentManager());
        viewPagerHome.setAdapter(adapterViewPagerHomePage);
        viewPagerHome.addOnPageChangeListener(this);

        rd_anGi = (RadioButton) findViewById(R.id.rd_angi);
        rd_oDau = (RadioButton) findViewById(R.id.rd_odau);



    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rd_oDau.setChecked(true);
                break;
            case 1:
                rd_anGi.setChecked(true);
                break;
        }
        Log.d("kiemtra", position + "");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
