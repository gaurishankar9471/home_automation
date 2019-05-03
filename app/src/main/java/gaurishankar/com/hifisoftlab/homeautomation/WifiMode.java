package gaurishankar.com.hifisoftlab.homeautomation;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

public class WifiMode extends AppCompatActivity {


    private Boolean l1,l2,l3,turn;
    private static WifiManager wifiManager;
    private Context context;
    private WifiConfiguration conf;
    public static String networkSSID="Rishav";
    public static String networkPass="rajarishav";
    byte[] buf = new byte[1024];//used to sending information to esp is a form of byte

    private boolean wifi_on ;

    private Button wifiConnectBtn;

    private EditText mIPAdd;
    private Button mIPSbmBtn;
    private LinearLayout mB1, mB2, mB3, mB4;

    private boolean status1, status2, status3, status4 = false;

    private String ip_add;
    private TextView mIPTxt;

    private Toolbar mToolbar;

    private TextView mDisconnect;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_mode);

        mToolbar = findViewById(R.id.wifi_mode_tb);
        mToolbar.setTitle("Wifi Mode");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // this is for thread policy the AOS doesn't allow to transfer data using wifi module so we take the permission
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        mDisconnect = findViewById(R.id.wifi_connect_btn);
        mDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Please disable your hotspot",Toast.LENGTH_LONG).show();
            }
        });

        mIPTxt = findViewById(R.id.ip_add_text);
        mIPAdd = findViewById(R.id.wifi_ip_address);
        mIPSbmBtn = findViewById(R.id.wifi_ip_sbm_btn);
        mIPSbmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip_add = mIPAdd.getText().toString().trim();
                mIPTxt.setText(ip_add);
                mIPSbmBtn.setText("Connected");
                Toast.makeText(getApplicationContext(),"Connected",Toast.LENGTH_LONG).show();
            }
        });

        mB1 = findViewById(R.id.b_bulb1);
        mB2 = findViewById(R.id.b_bulb2);
        mB3 = findViewById(R.id.b_bulb3);
        mB4 = findViewById(R.id.b_bulb4);

        final OkHttpClient client = new OkHttpClient();


        mB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (AppManager.isApOn(WifiMode.this)){

                    if (!status1){

                        try {

                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay1On")
                                    .build();

                            status1=true;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 1 is On Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img1);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);

                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        try {
                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay1Off")
                                    .build();

                            status1=false;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 1 is Off Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img1);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);



                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enable your hotspot First!",Toast.LENGTH_LONG).show();

                }



            }
        });
        mB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppManager.isApOn(WifiMode.this)){

                    if (!status2){

                        try {

                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay2On")
                                    .build();

                            status2=true;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 2 is On Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img2);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);

                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        try {
                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay2Off")
                                    .build();

                            status2=false;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 2 is Off Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img2);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);



                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enable your hotspot First!",Toast.LENGTH_LONG).show();

                }

            }
        });
        mB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppManager.isApOn(WifiMode.this)){

                    if (!status3){

                        try {

                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay3On")
                                    .build();

                            status3=true;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 3 is On Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img3);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);

                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }

                    }
                    else {
                        try {
                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay3Off")
                                    .build();

                            status3=false;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 3 is Off Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img3);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);



                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enable your hotspot First!",Toast.LENGTH_LONG).show();

                }
            }
        });
        mB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppManager.isApOn(WifiMode.this)){

                    if (!status4){

                        try {

                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay4On")
                                    .build();

                            status4=true;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 4 is On Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img4);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);

                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        try {
                            Request request = new Request.Builder()
                                    .url("http://"+ip_add+"/Relay4Off")
                                    .build();

                            status4=false;
                            Response response = client.newCall(request).execute();
                            Toast.makeText(getApplicationContext(),"Relay 4 is Off Now!",Toast.LENGTH_LONG).show();
                            ImageView img = findViewById(R.id.b_img4);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(),"Please connect to Wifi First!",Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enable your hotspot First!",Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void enableHotspot() {

       // AppManager.isApOn(WifiMode.this); // check Ap state :boolean
        if (AppManager.isApOn(WifiMode.this)){
            Toast.makeText(getApplicationContext(),"Wifi is On",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Wifi is Off",Toast.LENGTH_LONG).show();
            AppManager.configApState(WifiMode.this);

        }
      //  AppManager.configApState(WifiMode.this);
    }



    public void sendHTTPRequest(String relay_id, ImageView image){

        OkHttpClient client = new OkHttpClient();


        try {
            Request request = new Request.Builder()
                    .url("http://192.168.43.223/"+relay_id)
                    .build();

            Response response = client.newCall(request).execute();
            Toast.makeText(getApplicationContext(),relay_id,Toast.LENGTH_LONG).show();
            image.setImageResource(R.drawable.bulb_icon_fill_layout);



        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
        }

    }


//    //used to send data to esp module
//    public class Client implements Runnable{
//        private final static String SERVER_ADDRESS = "192.168.43.223";//public ip of my server
//        private final static int SERVER_PORT = 8888;
//
//
//        public void run(){
//            InetAddress serverAddr;
//            DatagramPacket packet;
//            DatagramSocket socket;
//            try {
//                serverAddr = InetAddress.getByName(SERVER_ADDRESS);
//                socket = new DatagramSocket(); //DataGram socket is created
//                packet = new DatagramPacket(buf, buf.length, serverAddr, SERVER_PORT);//Data is loaded with information where to send on address and port number
//                socket.send(packet);
//                Toast.makeText(getApplicationContext(),"Data is sent",Toast.LENGTH_LONG).show();//Data is send in the form of packets
//                socket.close();//Needs to close the socket before other operation... its a good programming
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            } catch (SocketException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
