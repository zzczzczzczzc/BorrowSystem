package com.example.borrowsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //创建数据库和表
        helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("count", "1");
        values.put("password", "2");
        db.insert("user", null, values);
        db.close();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt_signUp = (Button) findViewById(R.id.bt_signUp);
        bt_signUp.setOnClickListener(this);
        Button bt_signIn = (Button) findViewById(R.id.bt_signIn);
        bt_signIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //注册
            case R.id.bt_signUp:
                Intent intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;
            //登录
            case R.id.bt_signIn:
                SQLiteDatabase db = helper.getWritableDatabase();
                EditText et_count = (EditText) findViewById(R.id.et_count);
                EditText et_password = (EditText) findViewById(R.id.et_password);
                String count = et_count.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (count.equals("") || password.equals("")) {
                    Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
//                Cursor cursor = db.query("user", new String[]{"password"},
//                        "where count = ?", new String[]{count}, null, null, null);
//                Cursor cursor = db.query("user", null,
//                        null, null, null, null, null);
                Cursor cursor = db.rawQuery("select * from user where count = ?", new String[]{"1"});
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    String ct = cursor.getString(0);
                    String pw = cursor.getString(1);
                    Toast.makeText(this, ct + pw, Toast.LENGTH_SHORT).show();
                    cursor.moveToNext();
                }
                cursor.close();
                break;
        }
    }
}