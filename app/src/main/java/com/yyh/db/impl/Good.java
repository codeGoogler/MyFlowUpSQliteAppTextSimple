package com.yyh.db.impl;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.yyh.db.db.AppDatabase;

import java.io.Serializable;



@Table(database = AppDatabase.class)
public class Good extends BaseModel implements Serializable {
    //DBFlow会根据你的类名自动生成一个表明
    //这个类对应的表名为：Good_Table
    @Column
    public String gName;
    @Column
    public String gDes;
    @Column
    public int num;

    @PrimaryKey(autoincrement = true)//ID自增
    public long id;

    @Column
    public String extraCoumen;//增加的字段<数据库升级甩>

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgDes() {
        return gDes;
    }

    public void setgDes(String gDes) {
        this.gDes = gDes;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEextraCoumen() {
        return extraCoumen;
    }

    public void setExtraCoumen(String content) {
        this.extraCoumen = content;
    }
}
