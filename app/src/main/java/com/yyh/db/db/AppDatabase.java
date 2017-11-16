package com.yyh.db.db;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;
import com.yyh.db.impl.Good;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {
    //数据库名称
    public static final String NAME = "NF_AppDatabase";
    //数据库版本号
    public static final int VERSION = 2;

    /**
     * 数据库的修改：
     * 1、PatientSession 表结构的变化
     * 2、增加表字段，考虑到版本兼容性，老版本不建议删除字段
     */
    @Migration(version = VERSION, database = AppDatabase.class)
    public static class Migration2UserData extends AlterTableMigration<Good> {

        public Migration2UserData(Class<Good> table) {
            super(table);
        }

        @Override
        public void onPreMigrate() {
            addColumn(SQLiteType.TEXT, "extraCoumen");
        }
    }
}
