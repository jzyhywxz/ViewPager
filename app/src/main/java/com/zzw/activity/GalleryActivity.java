package com.zzw.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zzw.transformer.GalleryPageTransformer;
import com.zzw.util.ImageUtils;
import com.zzw.util.ViewAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private List<View> pages;
    private FrameLayout layout;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        pages=getPages();
        pager=(ViewPager) findViewById(R.id.gallery);
        PagerAdapter adapter=new ViewAdapter(pages);
        pager.setAdapter(adapter);
        pager.setPageMargin(20);
        pager.setOffscreenPageLimit(3);
        pager.setPageTransformer(true, new GalleryPageTransformer());

        layout=(FrameLayout) findViewById(R.id.frame_layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return pager.dispatchTouchEvent(event);
            }
        });
    }

    private List<View> getPages() {
        List<View> pages=new ArrayList<>();
        Field[] fields=R.drawable.class.getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.getName().startsWith("page")) {
                    ImageView view = new ImageView(this);
                    view.setImageBitmap(ImageUtils.getReverseBitmapById(this, field.getInt(null), 0.5f));
                    pages.add(view);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return pages;
    }

}
