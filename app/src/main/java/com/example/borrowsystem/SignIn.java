package com.example.borrowsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Client.ClientReleased;
import Client.ReleaseOrder;
import Database.DBHelper;
import entity.ServiceInfo;

public class SignIn extends AppCompatActivity {

    //登录账号
    String count;
    Button bt_released;
    Button bt_release;
    Button bt_accepted;
    Button bt_accept;
    RadioGroup rg_service;
    private ListView mListView;
    DBHelper dbHelper;
    List<ServiceInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Intent intent = getIntent();
        count = intent.getStringExtra("count");
        init();
        searchInfo();
        final ServiceAdapter sAdapter = new ServiceAdapter();
        mListView.setAdapter(sAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //弹出一个对话框，选择是否接单
                AlertDialog dialog = new AlertDialog.Builder(SignIn.this).setTitle("接单").setMessage("是否接受该订单？").
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                ServiceInfo s = (ServiceInfo) sAdapter.getItem(position);
                                //将订单信息插入自由职业者的已接受订单表中
                                Cursor cursor = db.rawQuery("select * from user where count = ?", new String[]{count});
                                cursor.moveToFirst();
                                //更新客户中已发布订单中的自由职业者的联系方式和姓名

                                //更改主界面ListView中的isAccept为“是”


                            }
                        }).setNegativeButton("取消", null).create();
                dialog.show();
            }
        });
//        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void searchInfo() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("serviceInfo", null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ServiceInfo serviceInfo = new ServiceInfo();

            serviceInfo.setName(cursor.getString(cursor.getColumnIndex("name")));
            serviceInfo.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            serviceInfo.setContent(cursor.getString(cursor.getColumnIndex("content")));
            serviceInfo.setStartTime(cursor.getString(cursor.getColumnIndex("startTime")));
            serviceInfo.setEndTime(cursor.getString(cursor.getColumnIndex("endTime")));
            serviceInfo.setPay(cursor.getString(cursor.getColumnIndex("pay")));
            serviceInfo.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            serviceInfo.setPhoneNum(cursor.getString(cursor.getColumnIndex("phoneNum")));
            serviceInfo.setIsAccept(cursor.getString(cursor.getColumnIndex("isAccept")));

            cursor.moveToNext();
            list.add(serviceInfo);
        }
        cursor.close();
        db.close();
    }

    public void init() {
        //将数据库中的信息存储在ArrayList中
        list = new ArrayList<>();
        dbHelper = new DBHelper(this);
        mListView = (ListView) findViewById(R.id.lv);
        bt_release = (Button) findViewById(R.id.bt_release);
        bt_released = (Button) findViewById(R.id.bt_released);
        bt_accept = (Button) findViewById(R.id.bt_accept);
        bt_accepted = (Button) findViewById(R.id.bt_accepted);
        rg_service = (RadioGroup) findViewById(R.id.bg_service);
    }

    public void click(View view) {
        //自由职业者查询已接受的订单，跳转到另一界面
        bt_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //自由职业者接受订单，生成数据插入数据库
        bt_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //客户已发布订单
        bt_released.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, ClientReleased.class);
                intent.putExtra("count", count);
                startActivity(intent);
            }
        });
        //客户发布订单
        bt_release.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SignIn.this, ReleaseOrder.class);
                intent1.putExtra("count", count);
                startActivity(intent1);
            }
        });
        //选择订单类型
        rg_service.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

    }

    class ServiceAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
            View view = View.inflate(SignIn.this, R.layout.list_view, null);
            TextView tv_name = (TextView) view.findViewById(R.id.item_name);
            TextView tv_category = (TextView) view.findViewById(R.id.item_category);
            TextView tv_pay = (TextView) view.findViewById(R.id.item_pay);
            TextView tv_content = (TextView) view.findViewById(R.id.item_content);
            TextView tv_startTime = (TextView) view.findViewById(R.id.item_startTime);
            TextView tv_endTime = (TextView) view.findViewById(R.id.item_endTime);
            TextView tv_address = (TextView) view.findViewById(R.id.item_address);
            TextView tv_phoneNum = (TextView) view.findViewById(R.id.item_phoneNum);
            TextView tv_isAccepted = (TextView) view.findViewById(R.id.item_isAccept);
            if (list.get(position).getIsAccept().equals("否")) {
                tv_name.setText("名称：" + list.get(position).getName());
                tv_category.setText("类型：" + list.get(position).getCategory());
                tv_pay.setText("薪酬：" + list.get(position).getPay());
                tv_content.setText("内容：" + list.get(position).getContent());
                tv_startTime.setText("开始时间：" + list.get(position).getStartTime());
                tv_endTime.setText("结束时间：" + list.get(position).getEndTime());
                tv_address.setText("地址：" + list.get(position).getAddress());
                tv_phoneNum.setText("联系方式：" + list.get(position).getPhoneNum());
                tv_isAccepted.setText("是否已被接单：" + list.get(position).getIsAccept());
            }
            return view;
        }
    }

//    class ViewHolder {
//
//    }
}
