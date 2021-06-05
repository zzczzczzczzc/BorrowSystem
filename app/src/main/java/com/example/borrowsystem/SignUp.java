package com.example.borrowsystem;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Database.DBHelper;
import entity.UserInfo;

public class SignUp extends AppCompatActivity {

    EditText et_name;
    EditText et_count;
    EditText et_password;
    RadioGroup rg_category;
    CheckBox cb_purchasing;
    CheckBox cb_drive;
    CheckBox cb_domestic;
    Button bt_complete;
    DBHelper helper;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        helper = new DBHelper(this);
        userInfo = new UserInfo();
        init();
        signUp();
    }

    public void init() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_count = (EditText) findViewById(R.id.et_count);
        et_password = (EditText) findViewById(R.id.et_password);
        rg_category = (RadioGroup) findViewById(R.id.rg_category);
        bt_complete = (Button) findViewById(R.id.bt_complete);
        cb_domestic = (CheckBox) findViewById(R.id.cb_domestic);
        cb_drive = (CheckBox) findViewById(R.id.cb_drive);
        cb_purchasing = (CheckBox) findViewById(R.id.cb_purchasing);
    }

    public void signUp() {
        //选择注册为自由职业者还是客户
        rg_category.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //自由职业者
                if (checkedId == R.id.rb_freelance) {
                    userInfo.setCategory("freelance");
                } else {
                    userInfo.setCategory("client");
                }
            }
        });

        //将输入的数据添加进数据库
        bt_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInfo.setName(et_name.getText().toString());
                userInfo.setCount(et_count.getText().toString());
                userInfo.setPassword(et_password.getText().toString());
//                if (userInfo.getPurchasing()) {
//                    Toast.makeText(SignUp.this, userInfo.getName() + " " + userInfo.getCount() + " " + userInfo.getPassword() + " " + userInfo.getCategory(), Toast.LENGTH_SHORT).show();
//                }
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("name", userInfo.getName());
                values.put("count", userInfo.getCount());
                values.put("password", userInfo.getPassword());
                values.put("category", userInfo.getCategory());
                values.put("purchasing", userInfo.getPurchasing());
                values.put("drive", userInfo.getDrive());
                values.put("domestic", userInfo.getDomestic());
                Cursor cursor = db.rawQuery("select count from user where count = ?", new String[]{userInfo.getCount()});
                if (cursor.getCount() != 0) {
                    Toast.makeText(SignUp.this, "该账号已存在", Toast.LENGTH_SHORT).show();
                } else if (userInfo.getCount().equals("") || userInfo.getName().equals("") ||
                        userInfo.getPassword().equals("") || userInfo.getCategory().equals("")) {
                    Toast.makeText(SignUp.this, "请填写全部信息", Toast.LENGTH_SHORT).show();
                } else {
                    db.insert("user", null, values);
                    db.close();
                    Toast.makeText(SignUp.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    //选择所需要或可提供的服务（可多选）
    public void doClick(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.cb_purchasing:
                if (checked) {
//                    Toast.makeText(SignUp.this, "成功", Toast.LENGTH_SHORT).show();
                    userInfo.setPurchasing(true);
                } else {
//                    Toast.makeText(SignUp.this, "失败", Toast.LENGTH_SHORT).show();
                    userInfo.setPurchasing(false);
                }
                break;
            case R.id.cb_domestic:
                if (checked) {
                    userInfo.setDomestic(true);
                } else {
                    userInfo.setDomestic(false);
                }
                break;
            case R.id.cb_drive:
                if (checked) {
                    userInfo.setDrive(true);
                } else {
                    userInfo.setDrive(false);
                }
                break;
        }
    }
}
