package Client;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.borrowsystem.R;

import java.util.ArrayList;
import java.util.List;

import Database.DBHelper;
import entity.ServiceInfo;

public class ClientReleased extends AppCompatActivity {

    String count;
    List<ServiceInfo> list;
    DBHelper dbHelper;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_released);
        list = new ArrayList<>();
        dbHelper = new DBHelper(this);
        mListView = (ListView) findViewById(R.id.lv_client);
        ServiceAdapter sAdapter = new ServiceAdapter();
        mListView.setAdapter(sAdapter);
        Intent intent = getIntent();
        count = intent.getStringExtra("count");

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from serviceInfo where phoneNum = ? ", new String[]{count});
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
            serviceInfo.setFreelancePhoneNum(cursor.getString(cursor.getColumnIndex("freelancePhoneNum")));
            cursor.moveToNext();
            list.add(serviceInfo);
        }
        cursor.close();
        db.close();
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
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.client_list_view, parent, false);
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
                holder.tv_freelanceName = (TextView) convertView.findViewById(R.id.item_freelance);
                holder.tv_freelancePhoneNum = (TextView) convertView.findViewById(R.id.item_freePhone);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_name.setText("名称：" + list.get(position).getName());
            holder.tv_category.setText("类型：" + list.get(position).getCategory());
            holder.tv_pay.setText("薪酬：" + list.get(position).getPay());
            holder.tv_content.setText("内容：" + list.get(position).getContent());
            holder.tv_startTime.setText("开始时间：" + list.get(position).getStartTime());
            holder.tv_endTime.setText("结束时间：" + list.get(position).getEndTime());
            holder.tv_address.setText("地址：" + list.get(position).getAddress());
            holder.tv_phoneNum.setText("联系方式：" + list.get(position).getPhoneNum());
            holder.tv_isAccepted.setText("是否已被接单：" + list.get(position).getIsAccept());
            if (!list.get(position).getFreelancePhoneNum().equals("")) {
                holder.tv_freelancePhoneNum.setText("接单者联系方式：" + list.get(position).getFreelancePhoneNum());
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
        TextView tv_freelanceName;
        TextView tv_freelancePhoneNum;
    }
}
