package com.corea.DatabaseDemo;

import com.corea.DatabaseDemo.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class DeleteActivity extends Activity {
      	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String alltables = "";														// ���̺��� ������ ���� ȭ�鿡 ǥ�õ� String
        DBAdapter db = new DBAdapter(this);											// ��� ��ü ����
        AlertDialog.Builder ad = new AlertDialog.Builder(DeleteActivity.this);  	// ���̺��� ǥ���� ���̾�α� ��ü ����
        setContentView(R.layout.delete);											// delete���̾ƿ��� ��Ǯ���ش�.
        TextView view = (TextView)findViewById(R.id.deleteText);					// TextView��ü����, ���̵� �޾ƿ´�.
      
        //---------------------------------------���̾�α� ��ư����----------------------------------------//
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int arg1) {
        		dialog.dismiss();
        	}
        });
        
        //-------------------------------------------DB ����--------------------------------------------//
        db.open();	  // db ����
        
        //-------------------------------------------����-----------------------------------------------//
        if (db.deleteTitle(1)) {						
            ad.setMessage("�׸� 1�� ���� ����");			// 1����� ���� �Ϸ� ǥ��
            ad.show();	
        }
        else {
        	ad.setMessage("�׸� 1�� ���� ����");			// 1����� ���� ����  ǥ��
        	ad.show();
        }
         
        //----------------------------------------ȭ�麸�̱�---------------------------------------------//
        Cursor c = db.getAllTitles();										
        if(c.moveToFirst()) {
        	do{	
        		alltables = alltables.concat("��ȣ: " + c.getString(0) + "\n" +
                        "�̸�: " + c.getString(1) + "\n" +
                        "��: " + c.getString(2) + "\n" +
                        "�г�: " + c.getString(3) + "\n" +
                        "-------------------------" + "\n");
        	}while(c.moveToNext());
        }
        view.setText(alltables);
        
        //------------------------------------------DB�ݱ�,����--------------------------------------------//
        db.close();	
        deleteDatabase(DBAdapter.getDatabaseName());	// �����ͺ��̽� ���� ����
        //------------------------------------------------------------------------------------------------//
    }
}

