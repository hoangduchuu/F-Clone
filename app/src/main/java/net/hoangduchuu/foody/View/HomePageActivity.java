package net.hoangduchuu.foody.View;

import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.hoangduchuu.foody.Adapter.AdapterViewPagerHomePage;
import net.hoangduchuu.foody.R;

import static net.hoangduchuu.foody.R.id.viewPagerHomePage;

public class HomePageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    FirebaseAuth mAuth;
    ViewPager viewPagerHome;
    RadioButton rd_oDau, rd_anGi;
    RadioGroup radioGroupHome;

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

        radioGroupHome = (RadioGroup) findViewById(R.id.radioGroupHome);
        radioGroupHome.setOnCheckedChangeListener(this);


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

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
        switch (i) {
            case R.id.rd_odau:
                viewPagerHome.setCurrentItem(0,true);
                break;
            case R.id.rd_angi:
                viewPagerHome.setCurrentItem(1,true);
                break;
        }
    }
}
