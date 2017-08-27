package com.corea.DatabaseDemo;

import com.corea.DatabaseDemo.R;

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

public class InsertActivity extends Activity {
     
    //** Called when the activity is first created. *//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String alltables = "";														// ���̺��� ������ ���� ȭ�鿡 ǥ�õ� String	
        DBAdapter db = new DBAdapter(this);											// DB������� ��ü
        setContentView(R.layout.insert);											// ���� �������� Insert���̾ƿ� ��Ǯ��
        Button updateButton = (Button)findViewById(R.id.updateButton);					// ���Թ�ư ��ü ������ ���̾ƿ��� ���̵� ������
        AlertDialog.Builder ad = new AlertDialog.Builder(InsertActivity.this);  	// ���̺��� ǥ���� ���̾�α� ��ü ����
        TextView view =(TextView) findViewById(R.id.insertText);
       
        //-------------------------------------------DB ����-----------------------------------------------//
        db.open();	 																 // db ����
 
        //-------------------------------------------����--------------------------------------------------//
        long id;
        id = db.insertTitle("�� ��ȣ", "��ǻ��", 	"3");
//        id = db.execSQL("INSERT INTO students(name, dept, grade)" +
//        		"VALUES('�� �¹�', '��ǻ��', '3')");
        id = db.insertTitle("�� ��ȯ", "����", "4");
        id = db.insertTitle("�� ��ö", "ü��", "2");
        id = db.insertTitle("�� ����", "����", "1");
        id = db.insertTitle("�� ����", "����", "3");        
 
        //-----------------------------------------���� ���̾�α� ����(���̾�α� ��ư �߰�)-----------------------//
        ad.setMessage("�׸� 5�� ���� ����");
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int arg1) {
        		dialog.dismiss();
        	}
        });
        ad.show();																// ������ �Ϸ�Ǿ��ٴ� ���̾�α�

        //------------------------------------------ȭ�麸�̱�-----------------------------------------------//
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
        //deleteDatabase(DBAdapter.getDatabaseName());							// �����ͺ��̽� ���� ����
        
        //------------------------------------------��ư Ŭ���̺�Ʈ------------------------------------------//	
        updateButton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {
				startActivity(new Intent(InsertActivity.this, UpdateActivity.class));
			}     	
        });
        //--------------------------------------------------------------------------------------------------
    }
}
