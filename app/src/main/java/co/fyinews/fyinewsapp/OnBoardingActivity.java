package co.fyinews.fyinewsapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

public class OnBoardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boading);

        getSupportActionBar().hide();


        PaperOnboardingPage scr1 = new PaperOnboardingPage("Welcome",
                "To FYI news, one of a kind news application.",
                Color.parseColor("#f48fb1"), R.drawable.onboarding_welcome, R.drawable.onboarding_pager_circle_icon);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("News",
                "FYI news will provide you canadian news from over 50 sources, which is presented in seven different categories.",
                Color.parseColor("#65B0B4"), R.drawable.onboarding_fyi_no_text_icon, R.drawable.onboarding_pager_circle_icon);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("",
                "Powered by 'newsAPI.org'",
                Color.parseColor("#9B90BC"), R.drawable.onboarding_news_api_logo, R.drawable.onboarding_pager_circle_icon);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);

        PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.frag_output_on_board, onBoardingFragment);
        fragmentTransaction.commit();

        final Intent i = new Intent(this, MainActivity.class);
        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {

                startActivity(i);
            }
        });
    }
}
