package com.example.toastex;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnToast :
            Toast.makeText(getApplication(),  "Basic Toast", Toast.LENGTH_SHORT).show();
            break;
        case R.id.btnToastGravity : {
            Toast toast = Toast.makeText(getApplication(),  "Toast-Gravity", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.RIGHT, 50, 50);
            toast.show();
        }
            break;
        case R.id.btnToastLayout : {
            LinearLayout layout = (LinearLayout)View.inflate(this,
                    R.layout.layout_toast, null);
            Toast toast = new Toast(this);
            toast.setView(layout);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }
            break;
        }
    }

}
