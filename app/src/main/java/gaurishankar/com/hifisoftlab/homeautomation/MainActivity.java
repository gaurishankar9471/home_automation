package gaurishankar.com.hifisoftlab.homeautomation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private CardView mBT, mWifi;
    private ProgressDialog mProgress;

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.main_tb);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.home_overflow_about_us){
            Intent intent = new Intent(MainActivity.this,AboutUsActivity.class);
            startActivity(intent);
        }

        if (id == R.id.home_overflow_more_app) {
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +"com.gauri.btcontrol" ));
            startActivity(rateIntent);
        }

        if (id == R.id.home_overflow_rate_this_app){
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
            startActivity(rateIntent);
        }
        if (id == R.id.home_overflow_share_this_app){
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "Bluetooth Control");
                i.putExtra(Intent.EXTRA_TEXT,"Home Automation is an excellent app to make your living easy, simple and smart. This app not only saves your time but also energy and effort. Home Automation facilitates you with the best features which are listed down accordingly \n For more details Download Our App \n Link :https://play.google.com/store/apps/details?id="+getApplicationContext().getPackageName() );
                startActivity(Intent.createChooser(i, "Select One"));
            } catch(Exception e) {
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }

        }
        if (id == R.id.home_overflow_exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}