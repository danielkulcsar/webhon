package exam.install;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class AndExam_Install extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Toast.makeText(this,"Touch Event Received",
					Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}
}