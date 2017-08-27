package com.corea.CallLogDemo;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class CallLogDemo extends ListActivity{

    private static String[] PROJECTION = new String[] {
      Phone._ID, Phone.DISPLAY_NAME, Phone.NUMBER
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Phone.CONTENT_URI = vnd.android.cursor.dir/phone_v2
        Cursor c = getContentResolver().query(Phone.CONTENT_URI,					// Phone.CONTENT_URI�̶� �׼����� ���� ������ URI�� ã�Ƽ� Ŀ�� ��ü�� ��´�. ���⼭�� ��ȭ��ȣ��URI
        		  PROJECTION, null, null, null);									// _ID, DISPLAY_NAME, NUMBER�� �׸��� Ŀ���� ��´�.
        startManagingCursor(c);														// Ŀ���� ��Ƽ��Ƽ�� ����������Ŭ�� ����ȭ �����ش�.
        
        ListAdapter adapter = new SimpleCursorAdapter(this,							// List�信 �ø� ����� ����, �� ����ʹ� Ŀ�� �׸��� ���� �� �ְ� �����. ù �Ķ���ʹ� context
                android.R.layout.simple_list_item_2, c, 							// �ȵ���̵忡�� �����ϴ� 2��¥�� layout�� �������̰�, c�� Ŀ����ü
                new String[] {Phone.DISPLAY_NAME, Phone.NUMBER}, 					// DISPLAY_NAME, NUMBER �׸� ����Ϳ� ���� ���̴�.		
                new int[] {android.R.id.text1,android.R.id.text2}); 				// ���� view ����.
        setListAdapter(adapter);													// ����͸� inflate��Ų��.
    }
}