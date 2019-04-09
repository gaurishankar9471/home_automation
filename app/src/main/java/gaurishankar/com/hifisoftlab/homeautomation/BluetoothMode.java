package gaurishankar.com.hifisoftlab.homeautomation;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static java.security.AccessController.getContext;

public class BluetoothMode extends AppCompatActivity {

    private LinearLayout mB1, mB2, mB3, mB4;

    private boolean status1, status2, status3, status4 = false;

    private boolean connected = false;

  //  private final String DEVICE_ADDRESS = "98:D3:35:70:ED:59";
    //MAC Address of Bluetooth Module
    private final UUID PORT_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");


    private OutputStream outputStream;
    private InputStream inputStream;
    private BluetoothDevice device;
    private BluetoothSocket socket;

    private Button mBTConnect;

    private ProgressDialog mProgress;

    private String command;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_mode);

        mProgress = new ProgressDialog(this);

        mBTConnect = findViewById(R.id.bt_connect);



        mBTConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!connected) {
                    showAvailableDeviceListDialog();
                } else {
                    try {
                        socket.close();
                        Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_LONG).show();
                        mBTConnect.setText("Connect");
//                mConnectionStatusTxt.setText("Not Connected");
//                mBTName.setText("Name");
//                mBTAddress.setText("Address");
                        connected = false;
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });


        mB1 = findViewById(R.id.b_bulb1);
        mB2 = findViewById(R.id.b_bulb2);
        mB3 = findViewById(R.id.b_bulb3);
        mB4 = findViewById(R.id.b_bulb4);

        mB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connected){
                    if (!status1) {

                        command ="A";

                        try {

                            outputStream.write(command.getBytes());

                            ImageView img = findViewById(R.id.b_img1);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status1 = true;


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {

                        command ="a";

                        try {

                            outputStream.write(command.getBytes());
                            ImageView img = findViewById(R.id.b_img1);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status1 = false;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        });

        mB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connected){

                    if (!status2) {

                        command = "B";

                        try {

                            outputStream.write(command.getBytes());
                            ImageView img = findViewById(R.id.b_img2);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status2 = true;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else {

                        command = "b";

                        try {

                            outputStream.write(command.getBytes());
                            ImageView img = findViewById(R.id.b_img2);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status2 = false;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        });

        mB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connected){
                    if (!status3) {

                        command = "C";

                        try {

                            outputStream.write(command.getBytes());
                            ImageView img = findViewById(R.id.b_img3);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status3 = true;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    } else {
                        command = "c";

                        try {

                            outputStream.write(command.getBytes());
                            ImageView img = findViewById(R.id.b_img3);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status3 = false;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }

            }
        });
        mB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (connected){

                    if (!status4) {
                        command = "D";

                        try {

                            outputStream.write(command.getBytes());
                            ImageView img = findViewById(R.id.b_img4);
                            img.setImageResource(R.drawable.bulb_icon_fill_layout);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status4 = true;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        command = "d";

                        try {

                            outputStream.write(command.getBytes());
                            ImageView img = findViewById(R.id.b_img4);
                            img.setImageResource(R.drawable.ic_lightbulb_outline_black_24dp);
                            Toast.makeText(getApplicationContext(), "Bulb 3", Toast.LENGTH_LONG).show();
                            status4 = false;

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        });

        String pre_add = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("BTADD", null);

        if (pre_add!=null){


            final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


            Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();

            if (bondedDevices.isEmpty()) //Checks for paired bluetooth devices
            {
                Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
            } else {
                for (BluetoothDevice iterator : bondedDevices) {
                    if (iterator.getAddress().equals(pre_add)) {
                        device = iterator;
                        connectToPreviousDevice();
                        break;
                    } else {
                        Toast.makeText(getApplicationContext(), "This Bluetooth Device is not paired. Please pair the device the device first", Toast.LENGTH_LONG).show();
                    }
                }
            }

        }

    }

    private void connectToPreviousDevice() {

        try {

            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates a socket to handle the outgoing connection
            socket.connect();
            connected = true;


        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }

        if (connected) {
            try {
                outputStream = socket.getOutputStream();
                Toast.makeText(getApplicationContext(), "Socket Connected", Toast.LENGTH_LONG).show();
                //        connected = true;
//                mConnectionStatusTxt.setText("Connected");
//                mBTAddress.setText(add);
//                mBTName.setText(name);
                mBTConnect.setText("Disconnect");
//                //gets the output stream of the socket
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_LONG).show();

            mBTConnect.setText("Connect");

        }


    }


    private void showAvailableDeviceListDialog() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.available_device_list_layout, null);

        final ListView mDeviceList = alertLayout.findViewById(R.id.available_device_list_view);

        Button submit = alertLayout.findViewById(R.id.comment_layout_submit_btn);
        Button cancel = alertLayout.findViewById(R.id.comment_layout_cancel_btn);

        final CheckBox checkBox = alertLayout.findViewById(R.id.available_device_checkbox);


        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        dialog.setCanceledOnTouchOutside(false);
        // dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        dialog.show();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Please Click on Available Address to get connected with Bluetooth Device", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_LONG).show();
            }
        });

        ArrayList<String> mobileArray = new ArrayList<>();

        final ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.my_list_view, mobileArray);
        mDeviceList.setAdapter(adapter);

        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();


        if (mBluetoothAdapter == null) //Checks if the device supports bluetooth
        {
            Toast.makeText(getApplicationContext(), "Device doesn't support bluetooth", Toast.LENGTH_SHORT).show();
        }

        if (!mBluetoothAdapter.isEnabled()) //Checks if bluetooth is enabled. If not, the program will ask permission from the user to enable it
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (mBluetoothAdapter.isEnabled()) {
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            for (BluetoothDevice bt : pairedDevices) {
                mobileArray.add(bt.getAddress() + "-" + bt.getName());
            }

            mDeviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Toast.makeText(getApplicationContext(), "Connecting...", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                    String[] total = mDeviceList.getItemAtPosition(i).toString().split("-");
                    String add = total[0];
                    String name = total[1];


                    Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();

                    if (bondedDevices.isEmpty()) //Checks for paired bluetooth devices
                    {
                        Toast.makeText(getApplicationContext(), "Please pair the device first", Toast.LENGTH_SHORT).show();
                    } else {
                        for (BluetoothDevice iterator : bondedDevices) {
                            if (iterator.getAddress().equals(add)) {
                                device = iterator;

                                BTconnect(add, name,checkBox);

                                break;
                            } else {
                                Toast.makeText(getApplicationContext(), "This Bluetooth Device is not paired. Please pair the device the device first", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                }
            });

        }


    }

    public void BTconnect(String add, String name, CheckBox checkBox) {


        mProgress.setMessage("Connecting...");
        mProgress.show();
        try {

            socket = device.createRfcommSocketToServiceRecord(PORT_UUID); //Creates a socket to handle the outgoing connection
            socket.connect();
            connected = true;


        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }

        if (connected) {
            try {
                outputStream = socket.getOutputStream();
                if (checkBox.isChecked()){
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("BTADD", device.getAddress()).apply();

                }
                Toast.makeText(getApplicationContext(), "Socket Connected", Toast.LENGTH_LONG).show();
                //        connected = true;
//                mConnectionStatusTxt.setText("Connected");
//                mBTAddress.setText(add);
//                mBTName.setText(name);
                mBTConnect.setText("Disconnect");
//                //gets the output stream of the socket
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_LONG).show();

              mBTConnect.setText("Connect");

        }

    }


}
