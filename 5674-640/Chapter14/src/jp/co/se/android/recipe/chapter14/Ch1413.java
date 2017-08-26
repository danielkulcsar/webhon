package jp.co.se.android.recipe.chapter14;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Ch1413 extends Activity implements OnClickListener {

    private static final String TAG = Ch1413.class.getSimpleName();
    private EditText mEditHours;
    private EditText mEditMinutes;
    private EditText mEditText;
    private Switch mSwitchSkipUi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1413_main);

        mEditHours = (EditText) findViewById(R.id.editHours);
        mEditMinutes = (EditText) findViewById(R.id.editMinutes);
        mEditText = (EditText) findViewById(R.id.editText);
        mSwitchSkipUi = (Switch) findViewById(R.id.switchSkipUi);
        Button btnSetAlarm = (Button) findViewById(R.id.btnSetAlarm);
        btnSetAlarm.setOnClickListener(this);

        // 디폴트를 설정
        Time time = new Time();
        time.setToNow();
        time.minute += 2; // 2 분 전을 지정
        mEditHours.setText(Integer.toString(time.hour));
        mEditMinutes.setText(Integer.toString(time.minute));
        mEditText.setText("Android 레시피 알람입니다.");
    }

    @Override
    public void onClick(View v) {
        String message = mEditText.getText().toString();
        String minutesStr = mEditMinutes.getText().toString();
        String hoursStr = mEditHours.getText().toString();
        boolean isSkip = mSwitchSkipUi.isChecked();

        try {
            setAlarm(Integer.parseInt(hoursStr), Integer.parseInt(minutesStr),
                    message, isSkip);
        } catch (NumberFormatException e) {
            // 수치가 아닌 값이 입력된 경우
            Toast.makeText(this, "수치를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // 알람 설정에 실패한 경우
            Toast.makeText(this, "설정에 실패했습니다.", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "설정 실패", e);
        }
    }

    /**
     * 알람을 설정
     */
    private void setAlarm(int hours, int minutes, String message, boolean isSkip)
            throws Exception {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        // 시간을 지정한다
        intent.putExtra(AlarmClock.EXTRA_HOUR, hours);
        // 분을 지정한다
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, message);
        // 메세지를 지정한다
        intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        // 설정 후, 알람 화면 표시를 스킵할지를 지정한다
        intent.putExtra(AlarmClock.EXTRA_SKIP_UI, isSkip);

        startActivity(intent);
    }
}
