package com.corea.ContextMenuDemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;
import android.widget.Toast;

public class ContextMenuDemo extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        TextView tv = (TextView) findViewById(R.id.text);        
        tv.setOnCreateContextMenuListener(this);
    }
 
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, 
    ContextMenuInfo menuInfo) 
    {
    	super.onCreateContextMenu(menu, view, menuInfo);
    	Log.e("ContextMenuDemo", "before menu.add()");
    	
        menu.setQwertyMode(true);
        menu.add(0, 0, 0, "�׸� 0");
        menu.add(0, 1, 1, "�׸� 1");
        menu.add(0, 2, 2, "�׸� 2");
        menu.add(0, 3, 3, "�׸� 3");
        menu.add(0, 4, 4, "�׸� 4");
        menu.add(0, 5, 5, "�׸� 5");
        menu.add(0, 6, 6, "�׸� 6");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
    	if (item.getItemId() >= 0 && item.getItemId() <= 6) {
    		Toast.makeText(this, "�׸� " + item.getItemId() + "����", 
                    Toast.LENGTH_LONG).show();
    		return true;
    	}
    	else
    		return false;
    }
}
