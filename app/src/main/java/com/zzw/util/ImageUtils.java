package com.zzw.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

/**
 * Created by zzw on 2017/3/22.
 */

public class ImageUtils {
    public static Bitmap getReverseBitmapById(Context context, int resId, float percent) {
        // get the source bitmap
        Bitmap srcBitmap=BitmapFactory.decodeResource(context.getResources(), resId);
        // get the tow third segment of the reverse bitmap
        Matrix matrix=new Matrix();
        matrix.setScale(1, -1);
        Bitmap rvsBitmap=Bitmap.createBitmap(srcBitmap, 0, (int) (srcBitmap.getHeight()*(1-percent)),
                srcBitmap.getWidth(), (int) (srcBitmap.getHeight()*percent), matrix, false);
        // combine the source bitmap and the reverse bitmap
        Bitmap comBitmap=Bitmap.createBitmap(srcBitmap.getWidth(),
                srcBitmap.getHeight()+rvsBitmap.getHeight()+20, srcBitmap.getConfig());
        Canvas gCanvas=new Canvas(comBitmap);
        gCanvas.drawBitmap(srcBitmap, 0, 0, null);
        gCanvas.drawBitmap(rvsBitmap, 0, srcBitmap.getHeight()+20, null);
        Paint paint=new Paint();
        LinearGradient shader=new LinearGradient(0, srcBitmap.getHeight()+20, 0, comBitmap.getHeight(),
                Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        gCanvas.drawRect(0, srcBitmap.getHeight()+20, srcBitmap.getWidth(), comBitmap.getHeight(), paint);
        return comBitmap;
    }
}
