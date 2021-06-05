package com.example.borrowsystem;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
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
import Freelance.FreelanceAccepted;
import entity.ServiceInfo;

public class SignIn extends AppCompatActivity {

    //登录账号
    String count;
    Button bt_released;
    Button bt_release;
    Button bt_accepted;
    RadioGroup rg_service;
    private ListView mListView;
    DBHelper dbHelper;
    List<ServiceInfo> list;
    ServiceAdapter sAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Intent intent = getIntent();
        count = intent.getStringExtra("count");
        init();
        searchInfo();
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
//                                Toast.makeText(SignIn.this, s.getIsAccept(), Toast.LENGTH_SHORT).show();
                                ContentValues values = new ContentValues();
                                values.put("isAccept", "是");
                                values.put("freelancePhoneNum", count);
//                                Toast.makeText(SignIn.this, count, Toast.LENGTH_SHORT).show();
                                //更新serviceInfo表中freelance的联系方式和已被接单
                                db.update("serviceInfo", values,
                                        "name = ? and category = ? and content = ? and startTime = ? and endTime = ? and pay = ? and address = ? and phoneNum = ?",
                                        new String[]{s.getName(), s.getCategory(), s.getContent(), s.getStartTime(), s.getEndTime(), s.getPay(), s.getAddress(), s.getPhoneNum()});
                                list.remove(position);
                                sAdapter.notifyDataSetChanged();
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
        sAdapter = new ServiceAdapter();
        //将数据库中的信息存储在ArrayList中
        list = new ArrayList<>();
        dbHelper = new DBHelper(this);
        mListView = (ListView) findViewById(R.id.lv);
        bt_release = (Button) findViewById(R.id.bt_release);
        bt_released = (Button) findViewById(R.id.bt_released);
        bt_accepted = (Button) findViewById(R.id.bt_accepted);
        rg_service = (RadioGroup) findViewById(R.id.bg_service);
    }

    public void click(View view) {
        //自由职业者查询已接受的订单
        bt_accepted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, FreelanceAccepted.class);
                intent.putExtra("count", count);
                startActivity(intent);
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
                switch (checkedId) {
                    case R.id.rb_purchasing:

                        break;
                    case R.id.rb_drive:

                        break;
                    case R.id.rb_domestic:

                        break;
                }
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
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_view, parent, false);
                holder = new ViewHolder();
                holder.tv_name = (TextView) convertView.findViewById(R.id.item_name);
                holder.tv_category = (TextView) convertView.findViewById(R.id.item_category);
                holder.tv_pay = (TextView) convertView.findViewById(R.id.item_pay);
                holder.tv_content = (TextView) convertView.findViewById(R.id.item_content);
                holder.tv_startTime = (TextView) convertView.findViewById(R.id.item_startTime);
                holder.tv_endTime = (TextView) convertView.findViewById(R.id.item_endTime);
                holder.tv_address = (TextView) convertView.findViewById(R.id.item_address);
                holder.tv_phoneNum = (TextView) convertView.findViewById(R.id.item_phoneNum);
                holder.tv_isAccepted = (TextView) convertView.findViewById(R.id.item_isAccept);
                convertView.setTag(holder);
//            TextView tv_freelancePhoneNum = (TextView) view.findViewById(R.id.item_freePhone);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (list.get(position).getIsAccept().equals("否")) {
                holder.tv_name.setText("名称：" + list.get(position).getName());
                holder.tv_category.setText("类型：" + list.get(position).getCategory());
                holder.tv_pay.setText("薪酬：" + list.get(position).getPay());
                holder.tv_content.setText("内容：" + list.get(position).getContent());
                holder.tv_startTime.setText("开始时间：" + list.get(position).getStartTime());
                holder.tv_endTime.setText("结束时间：" + list.get(position).getEndTime());
                holder.tv_address.setText("地址：" + list.get(position).getAddress());
                holder.tv_phoneNum.setText("联系方式：" + list.get(position).getPhoneNum());
                holder.tv_isAccepted.setText("是否已被接单：" + list.get(position).getIsAccept());
            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView tv_name;
        TextView tv_category;
        TextView tv_pay;
        TextView tv_content;
        TextView tv_startTime;
        TextView tv_endTime;
        TextView tv_address;
        TextView tv_phoneNum;
        TextView tv_isAccepted;
    }
}
