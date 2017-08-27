package jp.co.se.android.recipe.chapter02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Ch0212 extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 커스텀뷰를 설정
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(
                R.layout.ch0212_dialog_contents_item,
                (ViewGroup) findViewById(R.id.layout_root));

        // 알림 대화상자를 설정
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("대화상자 타이틀");
        builder.setView(layout);
        builder.setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OK 버튼 클릭을 처리
                EditText text = (EditText) layout.findViewById(R.id.edit_text);
                String string = text.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Cancel 버튼 클릭을 처리
            }
        });

        // 표시
        builder.create().show();
    }
}