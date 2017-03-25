package com.zzw.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zzw on 2017/3/22.
 */

public class ScalePageTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE=0.75f;

    @Override
    public void transformPage(View page, float position) {
        //Log.d("TAG", "<"+page.hashCode()+", "+position+">");
        // out of left screen
        if(position<-1.0f) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
        // slide left
        else if(position<=0.0f) {
            page.setAlpha(1.0f);
            page.setTranslationX(0.0f);
            page.setScaleX(1.0f);
            page.setScaleY(1.0f);
        }
        // slide right
        else if(position<=1.0f) {
            page.setAlpha(1.0f-position);
            page.setTranslationX(-page.getWidth()*position);
            float scale=MIN_SCALE+(1.0f-MIN_SCALE)*(1.0f-position);
            page.setScaleX(scale);
            page.setScaleY(scale);
        }
        // out of right screen
        else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        }
    }
}
