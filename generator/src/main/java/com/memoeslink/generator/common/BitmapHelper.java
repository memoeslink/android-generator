package com.memoeslink.generator.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.nio.ByteBuffer;

public class BitmapHelper {

    public static byte[] getCharPixels(char c) {
        return getStrPixels(String.valueOf(c));
    }

    public static byte[] getStrPixels(String s) {
        Bitmap b = Bitmap.createBitmap(10, 10, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(b);
        canvas.drawText(s, 0, 10 / 2, new Paint());
        ByteBuffer buffer = ByteBuffer.allocate(b.getByteCount());
        b.copyPixelsToBuffer(buffer);
        return buffer.array();
    }
}
