package com.example.bluetoothex;

import java.io.*;
import java.util.*;

import android.app.*;
import android.bluetooth.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity 
        implements AdapterView.OnItemClickListener {
    static final int ACTION_ENABLE_BT = 101;
    TextView mTextMsg;
    EditText mEditData;
    BluetoothAdapter mBA;
    ListView mListDevice;
    ArrayList<String> mArDevice;
    static final String  BLUE_NAME = "BluetoothEx";
    static final UUID BLUE_UUID = UUID.fromString( 
            "fa87c0d0-afac-11de-8a39-0800200c9a66");
    ClientThread mCThread = null;
    ServerThread mSThread = null;
    SocketThread mSocketThread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMsg = (TextView)findViewById(R.id.textMessage);
        mEditData = (EditText)findViewById(R.id.editData);
        initListView();

        boolean isBlue = canUseBluetooth();
        if( isBlue )
            getParedDevice();
    }

    public boolean canUseBluetooth() {
        mBA = BluetoothAdapter.getDefaultAdapter();
        if( mBA == null ) {
            mTextMsg.setText("Device not found");
            return false;
        }

        mTextMsg.setText("Device is exist");
        if( mBA.isEnabled() ) {
            mTextMsg.append("\nDevice can use");
           return true;
       }

        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, ACTION_ENABLE_BT);
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == ACTION_ENABLE_BT ) {
            if( resultCode == RESULT_OK ) {
                mTextMsg.append("\nDevice can use");
                getParedDevice();
            }
            else {
                mTextMsg.append("\nDevice can not use");
            }
        }
    }

    public void getParedDevice() {
        if( mSThread != null ) return;
        mSThread = new ServerThread();
        mSThread.start();

        Set<BluetoothDevice> devices = mBA.getBondedDevices();
        for( BluetoothDevice device : devices ) {
            addDeviceToList(device.getName(), device.getAddress());
        }

        startFindDevice();
        setDiscoverable();
    }

    public void startFindDevice() {
        stopFindDevice();
        mBA.startDiscovery();
        registerReceiver(mBlueRecv, new 
                IntentFilter( BluetoothDevice.ACTION_FOUND ));
    }

    public void stopFindDevice() {
        if( mBA.isDiscovering() ) {
            mBA.cancelDiscovery();
            unregisterReceiver(mBlueRecv);
        }
    }

    BroadcastReceiver mBlueRecv = new BroadcastReceiver() {
        public void  onReceive(Context context, Intent intent) {
            if( intent.getAction() == BluetoothDevice.ACTION_FOUND ) {
                BluetoothDevice device = 
                        intent.getParcelableExtra( BluetoothDevice.EXTRA_DEVICE );
                if( device.getBondState() != BluetoothDevice.BOND_BONDED )
                    addDeviceToList(device.getName(), device.getAddress());
            }
        }
    };

    public void addDeviceToList(String name, String address) {
        String deviceInfo = name + " - " + address;
        Log.d("tag1", "Device Find: " + deviceInfo);
        mArDevice.add(deviceInfo);
        ArrayAdapter adapter = (ArrayAdapter)mListDevice.getAdapter();
        adapter.notifyDataSetChanged();
    }

    public void initListView() {
        mArDevice = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mArDevice);
        mListDevice = (ListView)findViewById(R.id.listDevice);
        mListDevice.setAdapter(adapter);
        mListDevice.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView parent, View view, int position, long id) {
        String strItem = mArDevice.get(position);

        int pos = strItem.indexOf(" - ");
        if( pos <= 0 ) return;
        String address = strItem.substring(pos + 3);
        mTextMsg.setText("Sel Device: " + address);

        stopFindDevice();
        mSThread.cancel();
        mSThread = null;

        if( mCThread != null ) return;
        BluetoothDevice device = mBA.getRemoteDevice(address);
        mCThread = new ClientThread(device);
        mCThread.start();
    }

    public void setDiscoverable() {
        if( mBA.getScanMode() == 
                BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE )
            return;
        Intent intent = new 
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
        startActivity(intent);
    }

    public void showMessage(String strMsg) {
        Message msg = Message.obtain(mHandler, 0, strMsg);
        mHandler.sendMessage(msg);
        Log.d("tag1", strMsg);
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                String strMsg = (String)msg.obj;
                mTextMsg.setText(strMsg);
            }
        }
    };

    private class ClientThread extends Thread {
        private BluetoothSocket mmCSocket;

        public ClientThread(BluetoothDevice  device) {
            try {
                mmCSocket = 
                        device.createInsecureRfcommSocketToServiceRecord(BLUE_UUID);
            } catch(IOException e) {
                showMessage("Create Client Socket error");
                return;
            }
        }

        public void run() {
            try {
                mmCSocket.connect();
            } catch(IOException e) {
                showMessage("Connect to server error");
                try {
                    mmCSocket.close();
                } catch (IOException e2) {
                    showMessage("Client Socket close error");
                }
                return;
            }

            onConnected(mmCSocket);
        }

        public void cancel() {
            try {
                mmCSocket.close();
            } catch (IOException e) {
                showMessage("Client Socket close error");
            }
        }
    }

    private class ServerThread extends Thread {
        private BluetoothServerSocket mmSSocket;

        public ServerThread() {
            try {
                mmSSocket = 
                        mBA.listenUsingInsecureRfcommWithServiceRecord(  
                        BLUE_NAME, BLUE_UUID);
            } catch(IOException e) {
                showMessage("Get Server Socket Error");
            }
        }

        public void run() {
            BluetoothSocket cSocket = null;

            try {
                cSocket = mmSSocket.accept();
            } catch(IOException e) {
                showMessage("Socket Accept Error");
                return;
            }

            onConnected(cSocket);
        }

        public void cancel() {
            try {
                mmSSocket.close();
            } catch (IOException e) {
                showMessage("Server Socket close error");
            }
        }
    }

    public void onConnected(BluetoothSocket socket) {
        showMessage("Socket connected");

        if( mSocketThread != null )
            mSocketThread = null;
        mSocketThread = new SocketThread(socket);
        mSocketThread.start();
    }

    private class SocketThread extends Thread {
        private final BluetoothSocket mmSocket;
        private InputStream mmInStream;
        private OutputStream mmOutStream;

        public SocketThread(BluetoothSocket socket) {
            mmSocket = socket;

            try {
                mmInStream = socket.getInputStream();
                mmOutStream = socket.getOutputStream();
            } catch (IOException e) {
                showMessage("Get Stream error");
            }
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;

            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String strBuf = new String(buffer, 0, bytes);
                    showMessage("Receive: " + strBuf);
                    SystemClock.sleep(1);
                } catch (IOException e) {
                    showMessage("Socket disconneted");
                    break;
                }
            }
        }

        public void write(String strBuf) {
            try {
                byte[] buffer = strBuf.getBytes();
                mmOutStream.write(buffer);
                showMessage("Send: " + strBuf);
            } catch (IOException e) {
                showMessage("Socket write error");
            }
        }
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnSend : {
            if( mSocketThread == null ) return;
            String strBuf = mEditData.getText().toString();
            if( strBuf.length() < 1 ) return;
            mEditData.setText("");
            mSocketThread.write(strBuf);
            break;
        }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        stopFindDevice();

        if( mCThread != null ) {
            mCThread.cancel();
            mCThread = null;
        }
        if( mSThread != null ) {
            mSThread.cancel();
            mSThread = null;
        }
        if( mSocketThread != null )
            mSocketThread = null;
    }

}
