package com.zzw.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by zzw on 2017/3/22.
 */

public class GalleryPageTransformer implements ViewPager.PageTransformer {
    private static final float MAX_ROTATION=20.0f;
    private static final float MIN_SCALE=0.75f;
    private static final float MAX_TRANSLATE=20.0f;

    @Override
    public void transformPage(View page, float position) {
        if(position<-1) {
            page.setTranslationX(MAX_TRANSLATE);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setRotationY(-MAX_ROTATION);
        }
        else if(position<=0) {
            page.setTranslationX(-MAX_TRANSLATE*position);
            float scale=MIN_SCALE+(1-MIN_SCALE)*(1.0f+position);
            page.setScaleX(scale);
            page.setScaleY(scale);
            page.setRotationY(MAX_ROTATION*position);
        }
        else if(position<=1) {
            page.setTranslationX(-MAX_TRANSLATE*position);
            float scale=MIN_SCALE+(1-MIN_SCALE)*(1.0f-position);
            page.setScaleX(scale);
            page.setScaleY(scale);
            page.setRotationY(MAX_ROTATION*position);
        }
        else {
            page.setTranslationX(-MAX_TRANSLATE);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setRotationY(MAX_ROTATION);
        }
    }
}
