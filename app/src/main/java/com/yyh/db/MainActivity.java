package com.yyh.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.ModelAdapter;
import com.yyh.db.adapter.Mydapter;
import com.yyh.db.impl.Good;
import com.yyh.db.impl.Good_Table;
import com.yyh.db.impl.ProvicerData;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.id.list;

/**
 * 类功能描述：</br>
 * DbFlow用例测试项目
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
public class MainActivity extends AppCompatActivity {
    private ListView lv_tontent;
    private int i = 20;
    private List<Good> list;
    Mydapter mydapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_tontent = (ListView) findViewById(R.id.lv_tontent);
        list = ProvicerData.getMyListData();
        mydapter = new Mydapter(this, list);
        lv_tontent.setAdapter(mydapter);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_add,R.id.btn_del,R.id.btn_update,R.id.btn_query,R.id.btn_update2})
    public void onClick(View v){
        ModelAdapter<Good> manager = FlowManager.getModelAdapter(Good.class);
        switch (v.getId()){
            case R.id.btn_add://增加
                i = 20+i;
                Good goodes = new Good();
                goodes.setgDes("貌似官方有两张图，一个新形态蓝色，一个红色。别忘了最后吉莲暴气红色其实悟空应该还是打不过，新形态还可以再变身哈哈哈");
                goodes.setNum(i);
                goodes.setgName("自在如意功第"+i+"层");
                goodes.setExtraCoumen("数据库升级");//用于
                manager.insert(goodes);
                list = new Select().from(Good.class).queryList();
                mydapter.updateAdapterData(list);
                lv_tontent.setSelection(ListView.FOCUS_DOWN);
                break;
            case R.id.btn_del://删除
                //Good queryList2 = new Select().from(Good.class).where(Good_Table.gName.eq("昔年爱洗面奶")).querySingle();
                list = new Select().from(Good.class).queryList();
                if(list != null && list.size() > 0){
                    manager.delete(list.get(0));
                }
                mydapter.updateAdapterData(list);
                break;
            case R.id.btn_update://修改
                list = new Select().from(Good.class).queryList();
                if(list != null && list.size() > 0){
                    for (int j = 0; j < list.size(); j++) {
                       Good updataModel = list.get(j);
                        updataModel.setgName("卡卡罗特");
                        updataModel.update();
                        list.set(j,updataModel);
                    }
                    mydapter.updateAdapterData(list);
                }

                break;
            case R.id.btn_query://查询
                list = new Select().from(Good.class).queryList();
                mydapter.updateAdapterData(list);
                break;
            case R.id.btn_update2://查询
               // SQLite.update(Good.class).set(Good_Table.gName.eq("卡卡罗特")).where(Good_Table.num.eq(1)).execute();
                list = new Select().from(Good.class).queryList();
                if(list != null && list.size() > 0){
                    SQLite.update(Good.class).set(Good_Table.gName.eq("卡卡罗特")).where(Good_Table.num.eq(list.get(0).getNum())).execute();
                }
                list = new Select().from(Good.class).queryList();
                mydapter.updateAdapterData(list);
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this);
    }

}
