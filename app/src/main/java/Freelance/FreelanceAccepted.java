package Freelance;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.borrowsystem.R;
import com.example.borrowsystem.SignIn;

import java.util.List;

import entity.UserInfo;

public class FreelanceAccepted extends AppCompatActivity {

    ListView mListView;
    List<UserInfo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freelance_list_view);
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
}
