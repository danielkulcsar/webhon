package com.corea.IntentImplicitDemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentImplicitDemo extends Activity implements OnClickListener{
    
    static final int[] BUTTONS = {
        R.id.action_dial,
        R.id.action_edit_contacts,
        R.id.action_view_geo,
        R.id.action_view_http,
    };
	
    // 
    private static final String TAG = "IntentActivity";
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 
        for(int buttonId: BUTTONS) {
            Button button = (Button)findViewById(buttonId);
            button.setOnClickListener(this);
        }
    }
    // 
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
        case R.id.action_dial:
        	sendDialIntent();
        	break;     
        case R.id.action_edit_contacts: 
        	sendEditContacts();
        	break;
        case R.id.action_view_geo: 
            sendViewGeo();
            break;
        case R.id.action_view_http: 
            sendViewHttp();
            break;
        default:
        	Log.e(TAG, "Unkowon Button:"+v.toString());
        	break;
        }
    }

    //����Ʈ4-2 ������ ��ȭ��ȣ�� �Ŵ� Activity ����
    private void sendDialIntent() {
    	Intent i = new Intent(Intent.ACTION_DIAL,
                              Uri.parse("tel:027305800")); //û�ʹ�
    	startActivity(i);	
    }

    // 
    private void sendEditContacts() {
        //
        //Uri uri = ContentUris.withAppendedId(
        //    Contacts.People.CONTENT_URI, 1);
    	Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
    	//Intent i = new Intent(Intent.ACTION_INSERT, Uri.parse("content://com.google.provider.NotePad"));
    	startActivity(i);
    	
    }

    // 
    private void sendViewGeo() {
        Intent i = new Intent(Intent.ACTION_VIEW,
				  Uri.parse("geo:37.553,126.991")); // ���￪
    //    		  Uri.parse("geo:37.58,126.98")); // �溹��
    //    					  Uri.parse("geo:37.56,126.89"));  // ��� �����Ű���
    //    					  Uri.parse("geo:37.569407,126.898401"));  // ��� �����
    //                        Uri.parse("geo:0,0"));
    	startActivity(i);	
    }

    // 
    private void sendViewHttp() {
        Intent i = new Intent(Intent.ACTION_VIEW,
                              Uri.parse("http://www.google.com/"));
    	startActivity(i);	
    }
}