package com.zzw.activity;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzw.navigator.ScaleCircleNavigator;
import com.zzw.transformer.RotatePageTransformer;
import com.zzw.transformer.ScalePageTransformer;
import com.zzw.transformer.TransformerType;
import com.zzw.util.ViewAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PageActivity extends AppCompatActivity {
    private List<View> pages;
    private ViewPager pager;
    private MagicIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);

        pages=getPages();
        initPager();
        initIndicator();
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicator.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicator.onPageScrollStateChanged(state);
            }
        });
        ViewPagerHelper.bind(indicator, pager);
    }

    private void initPager() {
        pager=(ViewPager) findViewById(R.id.view_pager);
        PagerAdapter adapter=new ViewAdapter(pages);
        pager.setAdapter(adapter);

        switch (getIntent().getIntExtra(TransformerType.TYPE, TransformerType.NORMAL)) {
            case TransformerType.SCALE:
                pager.setPageTransformer(true, new ScalePageTransformer());
                break;
            case TransformerType.ROTATE:
                pager.setPageTransformer(true, new RotatePageTransformer());
                break;
            default:break;
        }
    }

    private void initIndicator() {
        indicator=(MagicIndicator) findViewById(R.id.bottom_indicator);
        ScaleCircleNavigator navigator=new ScaleCircleNavigator(this);
        navigator.setCircleCount(pages.size());
        navigator.setNormalCircleColor(Color.DKGRAY);
        navigator.setSelectedCircleColor(Color.CYAN);
        navigator.setCircleClickListener(new ScaleCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                pager.setCurrentItem(index);
            }
        });
        indicator.setNavigator(navigator);
    }

    private List<View> getPages() {
        List<View> pages=new ArrayList<>();
        Field[] fields=R.drawable.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.getName().startsWith("page")) {
                    ImageView view = new ImageView(this);
                    view.setImageResource(field.getInt(null));
                    view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    pages.add(view);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return pages;
    }
}
