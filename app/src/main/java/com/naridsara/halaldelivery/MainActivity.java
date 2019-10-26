package com.naridsara.halaldelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.naridsara.halaldelivery.utility.MyConnect;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (MyConnect.conn() == null) {
            Toast.makeText(getBaseContext(), "Not ok", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getBaseContext(), "ok", Toast.LENGTH_LONG).show();
        }
    }
}
