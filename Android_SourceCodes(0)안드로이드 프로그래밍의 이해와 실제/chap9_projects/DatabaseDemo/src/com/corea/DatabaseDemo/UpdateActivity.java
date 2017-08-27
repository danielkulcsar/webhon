package com.corea.DatabaseDemo;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UpdateActivity extends Activity {
	
    //** Called when the activity is first created. *//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String alltables = "";														// ���̺��� ������ ���� ȭ�鿡 ǥ�õ� String
        setContentView(R.layout.update);											// ������Ʈ ���̾ƿ��� ��Ǯ����.
        Button deleteButton = (Button)findViewById(R.id.deleteButton);				// ��ư ��ü ������, ���̵� �ޱ�
        DBAdapter db = new DBAdapter(this);											// ��� ��ü ����
        AlertDialog.Builder ad = new AlertDialog.Builder(UpdateActivity.this);    	// ���̺��� ǥ���� ���̾�α� ��ü ����
        TextView view = (TextView)findViewById(R.id.updateText);					// TextView ���� �޾ƿ´�.
        
        //---------------------------------------���̾�α� ��ư����----------------------------------------//
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int arg1) {
        		dialog.dismiss();
        	}
        });
        
        //-------------------------------------------DB ����---------------------------------------------//
        db.open();	 																 // db ����
 
        //-------------------------------------------������Ʈ---------------------------------------------//
        if (db.updateTitle(2, "�� �ֿ�", "���", "1")) {
        	ad.setMessage("�׸� 2�� ���� ����");									// 1����� ���� �Ϸ� ǥ��
        	ad.show();
        }
        else {
           ad.setMessage("�׸� 2�� ���� ����");								// 1����� ���� ����  ǥ��
           ad.show();
        }
       //----------------------------------------ȭ�麸�̱�-----------------------------------------------//
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
               
        //-----------------------------------------DB �ݱ�, ����-------------------------------------------//
        db.close();
        //deleteDatabase(DBAdapter.getDatabaseName());	// �����ͺ��̽� ���� ����

        //------------------------------------------��ư Ŭ���̺�Ʈ------------------------------------------//	
        deleteButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				startActivity(new Intent(UpdateActivity.this, DeleteActivity.class));
			}     	
        });
        //------------------------------------------------------------------------------------------------//	
    }
}
