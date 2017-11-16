package com.yyh.db.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 类功能描述：</br>
 * 吐司Toast
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class GetToast {
	private static Toast toast = null;
	public static Toast useString(Context context, String string) {
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,string, Toast.LENGTH_SHORT);
		}else{
			toast.setText(string);
		}
		toast.show();
		return toast;
	}

	/**
	 *
	 * @param context
	 * @param id StringVuales下的StringId
	 * @return
	 */
	public static Toast useid(Context context, int id) {
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,id, Toast.LENGTH_SHORT);
		}else{
			toast.setText(id);
		}
		toast.show();
		return toast;
	}
	public static Toast useInt(Context context, int id) {
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,""+id, Toast.LENGTH_SHORT);
		}else{
			toast.setText(""+id);
		}
		toast.show();
		return toast;
	}
	public static Toast setToastPosition(Context context, String string , int grivity, int xOffset, int yOffset){
		if (context == null)
			return null;
		if(toast == null){
			toast = Toast.makeText(context,string, Toast.LENGTH_SHORT);
		}else{
			toast.setText(string);
		}
		toast.setGravity(grivity,xOffset, yOffset);
		return toast;
	}


}
