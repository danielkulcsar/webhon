package jp.co.se.android.recipe.chapter02;

import java.util.TimeZone;

import jp.co.se.android.recipe.chapter02.R;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.TimeFormatException;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class Ch0235DialogPreference extends DialogPreference {

    /**
     * Preference에서 이용하는 값.(프레퍼런스 XML에 android:defaultValue가 정의되지 않을 경우 이 값이 이용된다.
     */
    private String mPreferenceValue = "";

    private DatePicker mDatePicker;
    private TimePicker mTimePicker;

    public Ch0235DialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        // 대화 상자의 레이아웃으로 이용할 리소스를 지정
        setDialogLayoutResource(R.layout.ch0235_main);

        // OK 버튼의 라벨을 지정
        setPositiveButtonText(android.R.string.ok);
        // 취소 버튼의 라벨을 지정
        setNegativeButtonText(android.R.string.cancel);
    }

    /**
     * 값을 초기화함, android:defaultValue가 없는 경우 호출되지 않음
     */
    @Override
    protected void onSetInitialValue(boolean restorePersistedValue,
            Object defaultValue) {
        if (restorePersistedValue) {
            mPreferenceValue = getPersistedString(mPreferenceValue);
        } else {
            mPreferenceValue = (String) defaultValue;
            persistString(mPreferenceValue);
        }
    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);

        mDatePicker = (DatePicker) view.findViewById(R.id.datePicker);
        mTimePicker = (TimePicker) view.findViewById(R.id.timePicker);

        // 값 취득
        setTimeToView(mPreferenceValue);
    }

    /** 값을 화면에 반영 */
    private void setTimeToView(String preferenceValue) {
        // 지정한 문자열을 Time으로 변환
        Time time = new Time(TimeZone.getDefault().getID());
        try {
            time.parse(preferenceValue);
        } catch (TimeFormatException e) {
            // 값을 변환 실패한 경우잘못된 값이 들어 있는 경우 등) 현재 시각을 설정
            time.setToNow();
        }

        // 값을 화면에 설정
        mDatePicker.updateDate(time.year, time.month, time.monthDay);
        mTimePicker.setCurrentHour(time.hour);
        mTimePicker.setCurrentMinute(time.minute);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            // 시간을 문자열로 변환
            Time time = new Time(TimeZone.getDefault().getID());
            time.year = mDatePicker.getYear();
            time.month = mDatePicker.getMonth();
            time.monthDay = mDatePicker.getDayOfMonth();
            time.hour = mTimePicker.getCurrentHour();
            time.minute = mTimePicker.getCurrentMinute();
            String newValue = time.format2445();
            if (callChangeListener(newValue)) {
                mPreferenceValue = newValue;
                persistString(mPreferenceValue);
            }
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        if (isPersistent()) {
            return superState;
        }

        // 값을 저장
        final PreferenceSavedState myState = new PreferenceSavedState(
                superState);
        myState.value = mPreferenceValue;
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state == null
                || !state.getClass().equals(PreferenceSavedState.class)) {
            super.onRestoreInstanceState(state);
            return;
        }

        // 값을 저장한다
        PreferenceSavedState myState = (PreferenceSavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());

        // 원래 값을 초기화하고 화면을 구성
        setTimeToView(myState.value);
    }

    /**
     * 값의 저장을 하기 위한 클래스
     */
    private static class PreferenceSavedState extends BaseSavedState {
        String value;

        public PreferenceSavedState(Parcelable superState) {
            super(superState);
        }

        public PreferenceSavedState(Parcel source) {
            super(source);
            value = source.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeString(value);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<PreferenceSavedState> CREATOR = new Parcelable.Creator<PreferenceSavedState>() {

            public PreferenceSavedState createFromParcel(Parcel in) {
                return new PreferenceSavedState(in);
            }

            public PreferenceSavedState[] newArray(int size) {
                return new PreferenceSavedState[size];
            }
        };
    }
}
