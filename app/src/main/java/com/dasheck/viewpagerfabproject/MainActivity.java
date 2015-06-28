package com.dasheck.viewpagerfabproject;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private ViewPager pager;
    private ScreenSlidePagerAdapter adapter;
    private Button button;

    private float lastOffset;
    private int currentTab;


    private TextView infoText;

    private boolean buttonEnabled(int destination) {
        switch (destination) {
            case 0:
                return false;

            case 3:
            case 5:
                return false;

            default:
                return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoText = (TextView) findViewById(R.id.infoText);

        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        button = (Button) findViewById(R.id.button);
        hideButton();

        lastOffset = 0.0f;
        currentTab = 0;

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int currentItem, float offset, int pixelOffset) {
                if(offset > 0.0f) {
                    float scaleFactor = 0.0f;
                    int nextTab = 0;
                    boolean left = true;

                    if(currentItem != currentTab) {
                        nextTab = currentItem;
                    } else {
                        nextTab = currentItem + 1;
                        left = false;
                    }

                    if(buttonEnabled(currentTab) && buttonEnabled(nextTab)) {
                        scaleFactor = scaleFactor(offset);
                    } else if(buttonEnabled(currentTab) && !buttonEnabled(nextTab)) {
                        if(left) {
                            scaleFactor = scaleFactorFadeIn(offset);
                        } else {
                            scaleFactor = scaleFactorFadeOut(offset);
                        }
                    } else if(!buttonEnabled(currentTab) && buttonEnabled(nextTab)) {
                        if(left) {
                            scaleFactor = scaleFactorFadeOut(offset);
                        } else {
                            scaleFactor = scaleFactorFadeIn(offset);
                        }
                    } else if(!buttonEnabled(currentTab) && buttonEnabled(nextTab)) {
                        scaleFactor = 0.0f;
                    }

                    lastOffset = offset;

                    button.setScaleX(scaleFactor);
                    button.setScaleY(scaleFactor);

                    infoText.setText("CT: " + currentTab + ", NT: " + nextTab + ", OFF: " + offset + "THIS: " + buttonEnabled(currentTab) + ", NEXT: " + buttonEnabled(nextTab));
                }
            }

            @Override
            public void onPageSelected(int i) {
                currentTab = i;

                if(buttonEnabled(currentTab)) {
                    showButton();
                } else {
                    hideButton();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void showButton() {
        button.setScaleX(1.0f);
        button.setScaleY(1.0f);
    }

    private void hideButton() {
        button.setScaleX(0.0f);
        button.setScaleY(0.0f);
    }

    private float scaleFactor(float x) {
        return (float) (0.5f * Math.cos(20 * x / Math.PI) + 0.5f);
    }

    private float scaleFactorFadeIn(float x) {
        if(x <= 0.5f) {
            return 0.0f;
        }

        return scaleFactor(x);
    }

    private float scaleFactorFadeOut(float x) {
        if(x >= 0.5f) {
            return 0.0f;
        }

        return scaleFactor(x);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
