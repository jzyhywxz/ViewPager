package com.zzw.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.zzw.transformer.TransformerType;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button normalBt;
    private Button scaleBt;
    private Button rotateBt;
    private Button tDGalleryBt;
    private Button fragPagerBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        normalBt=(Button) findViewById(R.id.normal_bt);
        normalBt.setOnClickListener(this);
        scaleBt=(Button) findViewById(R.id.scale_bt);
        scaleBt.setOnClickListener(this);
        rotateBt=(Button) findViewById(R.id.rotate_bt);
        rotateBt.setOnClickListener(this);
        tDGalleryBt=(Button) findViewById(R.id.td_gallery_bt);
        tDGalleryBt.setOnClickListener(this);
        fragPagerBt=(Button) findViewById(R.id.frag_pager_bt);
        fragPagerBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(this, PageActivity.class);
        switch (v.getId()) {
            case R.id.normal_bt:
                intent.putExtra(TransformerType.TYPE, TransformerType.NORMAL);
                break;
            case R.id.scale_bt:
                intent.putExtra(TransformerType.TYPE, TransformerType.SCALE);
                break;
            case R.id.rotate_bt:
                intent.putExtra(TransformerType.TYPE, TransformerType.ROTATE);
                break;
            case R.id.td_gallery_bt:
                intent=new Intent(this, GalleryActivity.class);
                break;
            case R.id.frag_pager_bt:
                intent=new Intent(this, FragPagerActivity.class);
                break;
            default:break;
        }
        startActivity(intent);
    }
}
