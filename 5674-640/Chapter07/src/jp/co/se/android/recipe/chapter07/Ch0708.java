package jp.co.se.android.recipe.chapter07;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/***
 * 
 * 음원은 http://maoudamashii.jokersounds.com/music_rule.html 에서 받음
 * 
 * @author yokmama
 * 
 */
public class Ch0708 extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0708_main);

        findViewById(R.id.buttonPlay).setOnClickListener(this);
        findViewById(R.id.buttonStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPlay) {
            Intent service = new Intent(this, Ch0708Service.class);
            service.setAction("play");
            startService(service);
        } else if (v.getId() == R.id.buttonStop) {
            Intent service = new Intent(this, Ch0708Service.class);
            service.setAction("stop");
            startService(service);
        }
    }

}
