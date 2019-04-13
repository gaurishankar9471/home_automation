package gaurishankar.com.hifisoftlab.homeautomation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private CardView mBT, mWifi;
    private ProgressDialog mProgress;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main_tb);
        mToolbar.setTitle("Home Automation");
        setSupportActionBar(mToolbar);


        mProgress = new ProgressDialog(MainActivity.this);
        mBT = findViewById(R.id.main_bluetooth_btn);
        mWifi = findViewById(R.id.main_wifi_btn);

        mBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgress.setMessage("Checking...");
                mProgress.show();
                Intent intent = new Intent(MainActivity.this, BluetoothMode.class);
                startActivity(intent);
            }
        });

        mWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WifiMode.class);
                startActivity(intent);
            }
        });
    }
}