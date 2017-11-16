package com.yyh.db.util;

import android.util.Log;

/**
 * 类功能描述：</br>
 * 公用日志
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class LogUtils {
	
	public static boolean isOpen = true;
	/**
	 * 通讯日志
	 */
	public static boolean isOpenCom = true;
	
	/**
	 * 仅接口调用
	 * @param tag
	 * @param msg
	 */
	public static void cd(String tag, String msg){
		if (isOpenCom) {
			Log.d(isNull(tag), isNull(msg));
		}
	}
	
	public static void d(String tag, String msg){
		if (isOpen) {
			Log.d(isNull(tag), isNull(msg));
		}
	}
	
	public static void i(String tag, String msg){
		if (isOpen) {
			Log.i(isNull(tag), isNull(msg));
		}
	}
	
	public static void e(String tag, String msg) {
		if (isOpen) {
			Log.e(isNull(tag), isNull(msg));
		}
	}
	private static String isNull(String s)
	{
		if(s==null)
		{
			return "null";
		}else
		{
			return s;
		}
	}

}

