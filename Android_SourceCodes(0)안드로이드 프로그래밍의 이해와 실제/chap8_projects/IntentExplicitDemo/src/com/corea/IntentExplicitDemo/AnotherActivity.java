// LaunchedActivity.java
package com.corea.IntentExplicitDemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//StartActivityForResult �޼ҵ忡 ���� ȣ��Ǵ� Activity
public class AnotherActivity extends Activity {

	//���� �ÿ� ����Ʈ�� ���޵� ������ Ű
    public static final String TEXT_INPUT = "TextInput";
    //���� �ÿ� ����Ʈ�� ���޵� ������ Ű
    public static final String TEXT_RESULT = "TextResult";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anotherview);
		
        //����Ʈ�κ��� Parameter�� �о� �帲
        String input = getIntent().getStringExtra(TEXT_INPUT);
        if (input != null) {
            EditText edit = (EditText)findViewById(R.id.result_text);
            edit.setText(input);
        }

        Button finish = (Button)findViewById(R.id.finish_button);
        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	//��ư�� ������ �����ϴ�
                terminate();
            }});
    }

    //�ؽ�Ʈ �ڽ��� ������ ����Ʈ�� �����ϰ� Activity�� ����
    private void terminate() {
        EditText edit = (EditText)findViewById(R.id.result_text);
        String result = edit.getText().toString();
        if (result.length() != 0) {
            //�ؽ�Ʈ�ڽ��� ���ڰ� �ԷµǾ� ������ ����Ʈ�� ����
            Intent i = new Intent();
            i.putExtra(TEXT_RESULT, result);
            setResult(RESULT_OK, i);
        } else {
            //�ؽ�Ʈ�ڽ��� ���ڰ� �ԷµǾ� ���� ������ ����� ���(cancel)
            setResult(RESULT_CANCELED);
        }
        // Activity�� �����ϴ�
        finish();
    }
}
