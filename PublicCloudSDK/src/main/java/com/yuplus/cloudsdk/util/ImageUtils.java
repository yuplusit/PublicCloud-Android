package com.yuplus.cloudsdk.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.yuplus.cloudsdk.cst.FileCst;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @user longzhen
 * @date 2017/1/3
 * @desc 图片处理工具类
 */

public class ImageUtils {
    /**
     * 位图转为字节数组
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmapToByte(Bitmap bitmap) {
        if (bitmap == null) return null;

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOut);
        return byteOut.toByteArray();
    }

    /**
     * 字节数组转为位图
     *
     * @param data
     * @return
     */
    public static Bitmap byteToBitmap(byte[] data) {
        return (data == null || data.length == 0)
                ? null : BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     * Drawable转为位图
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * 位图转为Drawable
     *
     * @param bitmap
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(bitmap);
    }

    /**
     * Drawable转为字节数组
     *
     * @param drawable
     * @return
     */
    public static byte[] drawableToByte(Drawable drawable) {
        return bitmapToByte(drawableToBitmap(drawable));
    }

    /**
     * 字节数组转为Drawable
     *
     * @param data
     * @return
     */
    public static Drawable byteToDrawable(byte[] data) {
        return bitmapToDrawable(byteToBitmap(data));
    }

    /**
     * 放大/缩小位图
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
        if (bitmap == null || width <= 0 || height <= 0) return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    /**
     * 放大/缩小位图
     *
     * @param bitmap
     * @param scaleWidth
     * @param scaleHeight
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, float scaleWidth, float scaleHeight) {
        if (bitmap == null || scaleWidth <= 0 || scaleHeight <= 0) return null;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }


    /**
     * 旋转图片
     *
     * @param bitmap
     * @param angle
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int angle) {
        if (bitmap == null) return null;

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }


    /**
     * 获得圆角图片
     *
     * @param bitmap
     * @param roundPx
     * @return
     */
    public static Bitmap roundBitmap(Bitmap bitmap, float roundPx) {
        if (bitmap == null || roundPx <= 0) return null;

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, w, h);
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 获得带倒影的图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap reflectBitmap(Bitmap bitmap) {
        if (bitmap == null) return null;

        final int reflectionGap = 4;
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
                h / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }


    /**
     * 保存位图
     *
     * @param bitmap
     * @param path
     * @return
     */
    public static void saveBitmap(Bitmap bitmap, String path) {
        saveBitmap(bitmap, path, null);
    }


    /**
     * 保存位图
     *
     * @param bitmap
     * @param path
     * @return
     */
    public static void saveBitmap(Bitmap bitmap, String path, Bitmap.CompressFormat format) {
        if (bitmap == null || StringUtils.isEmpty(path)) return;
        FileOutputStream fileOut = null;

        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            if (format == null) {
                if (path.endsWith(FileCst.SUFFIX_PNG)) {
                    format = Bitmap.CompressFormat.PNG;
                } else if (path.endsWith(FileCst.SUFFIX_JPG) || path.endsWith(FileCst.SUFFIX_JPEG)) {
                    format = Bitmap.CompressFormat.JPEG;
                } else {
                    format = Bitmap.CompressFormat.PNG;
                }
            }
            bitmap.compress(format, 100, byteOut);
            byte[] buffer = byteOut.toByteArray();

            fileOut = new FileOutputStream(path);
            fileOut.write(buffer, 0, buffer.length);
            fileOut.flush();
        } catch (Exception ex) {
        } finally {
            try {
                if (fileOut != null) fileOut.close();
            } catch (Exception ex) {
            }
        }
    }


    /**
     * 把一个View的对象转换成位图
     *
     * @param view
     * @return
     */
    public static Bitmap viewToBitmap(View view) {
        if (view == null) return null;

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();

        return bitmap;
    }

    /**
     * 设置Drawable的灰度
     * <pre>图标置灰   setDrawable(drawable, 0)</pre>
     *
     * @param drawable
     * @param sat      0-1
     * @return
     */
    public static Drawable setSatDrawable(Drawable drawable, float sat) {
        drawable.mutate();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(sat);
        ColorMatrixColorFilter cf = new ColorMatrixColorFilter(cm);
        drawable.setColorFilter(cf);
        return drawable;
    }

    /**
     * 将位图分割成(xp * yp)块
     *
     * @param bitmap
     * @param xp
     * @param yp
     * @return
     */
    public static Bitmap[] splitBitmap(Bitmap bitmap, int xp, int yp) {
        if (bitmap == null || xp <= 0 || yp <= 0) return null;

        Bitmap[] pieces = new Bitmap[xp * yp];
        int pieceWidth = bitmap.getWidth() / xp;
        int pieceHeight = bitmap.getHeight() / yp;

        for (int i = 0; i < xp; i++) {
            for (int j = 0; j < yp; j++) {
                int xValue = j * pieceWidth;
                int yValue = i * pieceHeight;
                Bitmap piece = Bitmap.createBitmap(bitmap, xValue, yValue, pieceWidth, pieceHeight);
                pieces[i * xp + j] = piece;
            }
        }

        return pieces;
    }

    /**
     * 生成水印图片
     *
     * @param src
     * @param watermark
     * @return
     */
    public Bitmap watermarkBitmap(Bitmap src, Bitmap watermark) {
        if (src == null) return null;
        if (watermark == null) return null;

        int w = src.getWidth();
        int h = src.getHeight();
        int ww = watermark.getWidth();
        int wh = watermark.getHeight();

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(bitmap);
        cv.drawBitmap(src, 0, 0, null);
        cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, null);
        cv.save(Canvas.ALL_SAVE_FLAG);
        cv.restore();
        return bitmap;
    }

    /**
     * 获取指定图片的宽度
     *
     * @param context
     * @param resId
     * @return
     */
    public static int getWidth(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        return options.outWidth;
    }

    /**
     * 获取指定图片的高度
     *
     * @param context
     * @param resId
     * @return
     */
    public static int getHeight(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        return options.outHeight;
    }

    /**
     * 获取指定图片的类型
     *
     * @param context
     * @param resId
     * @return
     */
    public static String getType(Context context, int resId) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        return options.outMimeType;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 设置inJustDecodeBounds=true，只获取图片信息
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        //计算inSampleSize
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }

        options.inSampleSize = inSampleSize;

        // 解码图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


    /**
     * get input stream from network by imageurl, you need to close inputStream yourself
     *
     * @param imageUrl
     * @param readTimeOutMillis read time out, if less than 0, not set, in mills
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static InputStream getInputStreamFromUrl(String imageUrl, int readTimeOutMillis) {
        InputStream stream = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (readTimeOutMillis > 0) {
                con.setReadTimeout(readTimeOutMillis);
            }
            stream = con.getInputStream();
        } catch (MalformedURLException e) {
            closeInputStream(stream);
            throw new RuntimeException("MalformedURLException occurred. ", e);
        } catch (IOException e) {
            closeInputStream(stream);
            throw new RuntimeException("IOException occurred. ", e);
        }
        return stream;
    }

    /**
     * get drawable by imageUrl
     *
     * @param imageUrl
     * @param readTimeOutMillis read time out, if less than 0, not set, in mills
     * @return
     */
    public static Drawable getDrawableFromUrl(String imageUrl, int readTimeOutMillis) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOutMillis);
        Drawable d = Drawable.createFromStream(stream, "src");
        closeInputStream(stream);
        return d;
    }

    /**
     * get Bitmap by imageUrl
     *
     * @param imageUrl
     * @return
     */
    public static Bitmap getBitmapFromUrl(String imageUrl, int readTimeOut) {
        InputStream stream = getInputStreamFromUrl(imageUrl, readTimeOut);
        Bitmap b = BitmapFactory.decodeStream(stream);
        closeInputStream(stream);
        return b;
    }

    /**
     * close inputStream
     *
     * @param s
     */
    private static void closeInputStream(InputStream s) {
        if (s == null) {
            return;
        }

        try {
            s.close();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        }
    }
}
