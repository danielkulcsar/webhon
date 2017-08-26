package jp.co.se.android.recipe.chapter04;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Ch0408 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        String msg = String.format(
                "아래의 위젯을 확인해 주세요. \n* %1$s\n* %2$s\n* %3$s",
                Ch0408AppWidgetProvider.class.getName(),
                "res/xml/chapter04_widget_click.xml", "AndroidManifest.xml");
        textView.setText(msg);
        setContentView(textView);
    }

}
