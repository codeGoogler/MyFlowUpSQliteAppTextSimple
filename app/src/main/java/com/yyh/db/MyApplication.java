package com.yyh.db;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;


/**
 * 类功能描述：</br>
 *  DbFlow用例测试项目
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        dbFlowInit();
    }
    private void dbFlowInit(){
        //FlowManager.init(new FlowConfig.Builder(this).build());

        try {
            // FlowManager.init(this);//这句也可以初始化
            FlowManager.init(new FlowConfig.Builder(getApplicationContext())
                    .openDatabasesOnInit(true) .build());
            FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
