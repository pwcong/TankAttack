package me.pwcong.tankattack.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Pwcong on 2016/11/30.
 */

public class BitmapUtils {

    private BitmapUtils(){

    }

    public static Bitmap zoom(Bitmap bitmap,float sx,float sy){

        Matrix matrix = new Matrix();
        matrix.postScale(sx,sy);

        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

    }

}
