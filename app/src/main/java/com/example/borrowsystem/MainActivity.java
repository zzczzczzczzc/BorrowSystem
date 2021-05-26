package com.example.borrowsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            case R.id.bt_signUp:
                Intent intent = new Intent(this, SignUp.class);
                startActivity(intent);
                break;
            case R.id.bt_signIn:
                Intent intent1 = new Intent(this, SignIn.class);
                startActivity(intent1);
        }

    }
}