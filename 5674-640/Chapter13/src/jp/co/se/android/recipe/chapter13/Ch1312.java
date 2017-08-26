package jp.co.se.android.recipe.chapter13;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ch1312 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1312_main);

        findViewById(R.id.buttonShow).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showToast("Hello");
            }
        });
    }

    private void showToast(String msg) {
        // LayoutInflater을 구한다
        LayoutInflater inflater = getLayoutInflater();

        // Toast 용의 레이아웃을 구한다
        View layout = inflater.inflate(R.layout.ch1312_toast, null, false);

        // 레이아웃 내의 ImageView를 취득하여 임의의 이미지를 설정한다
        ImageView image = (ImageView) layout.findViewById(R.id.imageIcon);
        image.setImageResource(R.drawable.ic_launcher);

        // 레이아웃 내의 TextView를 취득하여 임의의 텍스트를 설정한다
        TextView text = (TextView) layout.findViewById(R.id.textMessage);
        text.setText(msg);
        // Toast를 표시
        Toast toast = new Toast(this);
        toast.setView(layout);
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.BOTTOM, 0, 0);
        toast.show();
    }

}
