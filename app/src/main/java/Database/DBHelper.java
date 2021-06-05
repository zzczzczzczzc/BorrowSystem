package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String name = "borrowSystem.db";
    private static final int version = 1;

    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //注册表
        db.execSQL("Create table user" +
                "(name String not null, " +
                "count String primary key, " +
                "password String not null, " +
                "category String not null, " +
                "purchasing boolean, " +
                "domestic boolean, " +
                "drive boolean)");
        //服务表
        db.execSQL("Create table serviceInfo" +
//                "(clientName String not null," +
                "(name String not null," +
                "category String not null," +
                "content String not null," +
                "startTime String not null," +
                "endTime String not null," +
                "pay String not null," +
                "address String not null," +
                "phoneNum String not null," +
                "freelanceName String, " +
                "freelancePhoneNum String," +
                "isAccept String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
