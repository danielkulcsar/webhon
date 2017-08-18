package com.example.popupdialog;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    EditText editActivity;
    ProgressDialog progressDlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editActivity = (EditText)findViewById(R.id.editActivity);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnAlertDialog : {
            new AlertDialog.Builder(this)
                .setTitle("Simple")
                .setMessage("Do you want to make game?")
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton("Yes", 
                	    new DialogInterface.OnClickListener() {	
                            public void onClick(DialogInterface dialog, int which) {
                                editActivity.setText("Yes");
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {	
                        public void onClick(DialogInterface dialog, int which) {
                            editActivity.setText("No");
                        }
                    })
                .show();
            break;
        }
        case R.id.btnCustomDialog : {
            final RelativeLayout layout = (RelativeLayout)View.inflate(this, 
            	    R.layout.popup_dialog, null);
            final EditText editPopup = (EditText)layout.findViewById(R.id.editPopup);
                    editPopup.setText(editActivity.getText().toString());

            new AlertDialog.Builder(this)
                .setTitle("Notice!")
                .setView(layout)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {	
                    public void onClick(DialogInterface dialog, int which) {
                        String strText = editPopup.getText().toString();
                        editActivity.setText(strText);
                    }
                })
                .show();
            break;
            }
        case R.id.btnProgressDialog : {
            progressDlg = new ProgressDialog(this);
            progressDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDlg.setMessage("File Downloading...");
            progressDlg.show();
            mTimer.sendEmptyMessageDelayed(0, 3000);
        }
        }
    }

    Handler mTimer = new Handler() {
        public void handleMessage(Message msg) {
            progressDlg.hide();
        }
    };

}
