package Client;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.borrowsystem.R;

import Database.DBHelper;

public class ReleaseOrder extends AppCompatActivity {

    String count;
    Button bt_release;
    DBHelper dbHelper;
    ContentValues values;
    RadioGroup rg_category;
    EditText et_name;
    EditText et_content;
    EditText et_startTime;
    EditText et_endTime;
    EditText et_pay;
    EditText et_address;
    EditText et_phoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_order);
        Intent intent = getIntent();
        count = intent.getStringExtra("clientName");
        init();
        rg_category.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_purchasing:
                        values.put("category", "购物");
                        break;
                    case R.id.rb_drive:
                        values.put("category", "出行");
                        break;
                    case R.id.rb_domestic:
                        values.put("category", "家政");
                        break;
                }
            }
        });
        bt_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                values.put("clientName", count);
                values.put("name", et_name.getText().toString());
                values.put("content", et_content.getText().toString());
                values.put("startTime", et_startTime.getText().toString());
                values.put("endTime", et_endTime.getText().toString());
                values.put("pay", et_pay.getText().toString());
                values.put("address", et_address.getText().toString());
                values.put("phoneNum", et_phoneNum.getText().toString());
                values.put("isAccept", "否");
                values.put("freelanceName", "");
                values.put("freelancePhoneNum", "");
                db.insert("serviceInfo", null, values);
                db.close();
                Toast.makeText(ReleaseOrder.this, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void init() {
        values = new ContentValues();
        dbHelper = new DBHelper(this);
        bt_release = (Button) findViewById(R.id.bt_release);
        et_name = (EditText) findViewById(R.id.et_name);
        rg_category = (RadioGroup) findViewById(R.id.rg_category);
        et_content = (EditText) findViewById(R.id.et_content);
        et_startTime = (EditText) findViewById(R.id.et_startTime);
        et_endTime = (EditText) findViewById(R.id.et_endTime);
        et_pay = (EditText) findViewById(R.id.et_pay);
        et_address = (EditText) findViewById(R.id.et_address);
        et_phoneNum = (EditText) findViewById(R.id.et_phoneNum);
    }
}
