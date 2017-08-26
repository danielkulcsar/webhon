package jp.co.se.android.recipe.chapter02;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class Ch0213 extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final CharSequence[] chkItems = { "item1", "item2", "item3" };
        final boolean[] chkSts = { true, false, false };
        AlertDialog.Builder checkDlg = new AlertDialog.Builder(this);
        checkDlg.setTitle("타이틀");
        checkDlg.setMultiChoiceItems(chkItems, chkSts,
                new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialog, int which,
                            boolean flag) {
                        // 항목 선택 시에 처리
                        // i는 선택한 항목의 인덱스
                        // flag는 체크 상태
                    }
                });
        checkDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // 표시
        checkDlg.create().show();
    }
}