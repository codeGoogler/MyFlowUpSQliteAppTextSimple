package com.yyh.db.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;

import java.io.IOException;
import java.io.InputStream;


/**
 * 类功能描述：</br>
 * 图片处理工具
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class ImageUtil {
	private static final String TAG="imageutil";

	
	/**
	 * ID转化成BITMAP
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap drawableToBitmap(Context context, int resId){
		Drawable drawable = context.getResources().getDrawable(resId);
		return drawableToBitmap(drawable);
	}
	
	/**
	 * 把drawable转成BITMAP
	 * @param drawable
	 * @return
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(width, height, drawable
				.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, width, height);
		drawable.draw(canvas);
		return bitmap;
	}
	/**
	 * 读取图片的旋转的角度
	 *
	 * @param path
	 *            图片绝对路径
	 * @return 图片的旋转角度
	 */
	public static int getBitmapDegree(String path) {
	    int degree = 0;
	    try {
	        // 从指定路径下读取图片，并获取其EXIF信息
	        ExifInterface exifInterface = new ExifInterface(path);
	        // 获取图片的旋转信息
	        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
	                ExifInterface.ORIENTATION_NORMAL);
	        switch (orientation) {
	        case ExifInterface.ORIENTATION_ROTATE_90:
	            degree = 90;
	            break;
	        case ExifInterface.ORIENTATION_ROTATE_180:
	            degree = 180;
	            break;
	        case ExifInterface.ORIENTATION_ROTATE_270:
	            degree = 270;
	            break;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return degree;
	}
	/**
	 * 将图片按照某个角度进行旋转
	 *
	 * @param bm
	 *            需要旋转的图片
	 * @param degree
	 *            旋转角度
	 * @return 旋转后的图片
	 */
	public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
	    Bitmap returnBm = null;
	  
	    // 根据旋转角度，生成旋转矩阵
	    Matrix matrix = new Matrix();
	    matrix.postRotate(degree);
	    try {
	        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
	        returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
	    } catch (OutOfMemoryError e) {
//	  		TrackingHelper.trkExceptionInfo("rotateBitmapByDegree", e);
	    	LogUtils.e(TAG, e.getMessage());
	    }
	    if (returnBm == null) {
	        returnBm = bm;
	    }
	    if (bm != returnBm) {
	        bm.recycle();
	    }
	    return returnBm;
	}
	/**
	 * 读取RGB_565低质量的图片
	 * @param context
	 * @param resId
	 * @return
	 */
	public static Bitmap readBitMapByRGB_565(Context context, int resId) throws OutOfMemoryError {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
	/**
	 * 回收Bitmap
	 * @param bitmap
	 */
	public static void recycleBitmap(Bitmap bitmap)
	{
		if (null!=bitmap&&!bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap=null;
			System.gc();
		}
	}
	/**
	 * 回收Bitmaps
	 * @param bitmaps
	 */
	public static void recycleBitmap(Bitmap...bitmaps)
	{
		if (null!=bitmaps) {
			for (Bitmap bitmap : bitmaps) {
				if (null!=bitmap&&!bitmap.isRecycled()) {
					bitmap.recycle();
					bitmap=null;
				}
			}
			System.gc();
		}

	}
	public static void recycleDrawable(Drawable drawable)
	{
		if (null!=drawable)
		{
			drawable.setCallback(null);
			drawable=null;
			System.gc();
		}
	}
	public static void recycleDrawable(Drawable...drawables)
	{
		for (Drawable d :drawables)
		if (null!=d)
		{
			d.setCallback(null);
			d=null;

		}
		System.gc();
	}
}
/***
 * @deprecated 图片处理工具
 * @author:yyh
 * @date:20171005
 * 公众号：终端研发部
 * */
