package com.memoeslink.generator.common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import org.memoeslink.CharHelper;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class PrintableCharUtils {

    private PrintableCharUtils() {
    }

    public static boolean isPrintable(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return (!Character.isISOControl(c)) &&
                block != null &&
                block != Character.UnicodeBlock.SPECIALS;
    }

    public static boolean isAvailableInFont(char c) {
        if (c == CharHelper.NULL_CHAR || Character.isWhitespace(c))
            return false;
        char missingCharacter = '\u0978';
        byte[] b1 = getCharPixels(c);
        byte[] b2 = getCharPixels(missingCharacter);
        return !Arrays.equals(b1, b2);
    }

    public static boolean isDisplayable(char c) {
        return isPrintable(c) && isAvailableInFont(c);
    }

    public static char getFirstDisplayable(char... chars) {
        for (char c : chars) {
            if (isDisplayable(c))
                return c;
        }
        return '\0';
    }

    private static byte[] getCharPixels(char c) {
        return getStrPixels(String.valueOf(c));
    }

    private static byte[] getStrPixels(String s) {
        Bitmap b = Bitmap.createBitmap(10, 10, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(b);
        canvas.drawText(s, 0, 10 / 2, new Paint());
        ByteBuffer buffer = ByteBuffer.allocate(b.getByteCount());
        b.copyPixelsToBuffer(buffer);
        return buffer.array();
    }
}
