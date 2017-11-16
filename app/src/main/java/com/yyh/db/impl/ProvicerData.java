package com.yyh.db.impl;

import java.util.ArrayList;
import java.util.List;


/**
* 类功能描述：</br>
* 模拟数据
* 博客地址：http://blog.csdn.net/androidstarjack
* 公众号：终端研发部
* @author yuyahao
* @version 1.0 </p> 修改时间：2017/10/12 </br> 修改备注：</br>
*/

public class ProvicerData  {
   public static List<Good> getMyListData(){
       List<Good> list = new ArrayList<>();
       for (int i = 0; i < 10; i++) {
          /* Good goodes = new Good();
           goodes.setgDes("加拿大进口Cetaphil/丝塔芙 洁面乳118ml 保湿洗面奶");
           goodes.setNum(20+i);
           goodes.setgName("昔年爱洗面奶");
           list.add(goodes);*/
       }
       return list;
   }
}
