package jp.co.se.android.recipe.chapter02;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class Ch0236Preference extends Preference implements
        OnRatingBarChangeListener {

    private float mCurrentRating;
    private float mOldRating;

    public Ch0236Preference(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 커스텀 위젯 레이아웃 설정
        setWidgetLayoutResource(R.layout.ch0236_preference);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        // 처음 Preference가 호출될 때 설정할 초기치를 리턴
        return a.getFloat(index, 0);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue,
            Object defaultValue) {
        // Preferencene 초기화 설정
        if (restorePersistedValue) {
            // restorePersistedValue가 true이면 SharedPreference로부터 값을 구함
            mCurrentRating = getPersistedFloat(mCurrentRating);
        } else {
            // restorePersistedValue가 false이면 Preference에 기본값을 설정
            mCurrentRating = (Float) defaultValue;
            persistFloat(mCurrentRating);
        }
        mOldRating = mCurrentRating;
    }

    @Override
    protected void onBindView(View view) {
        // Preference와 커스터마이즈 된 View를 Bind
        final RatingBar rating = (RatingBar) view
                .findViewById(R.id.ratingPreference);
        if (rating != null) {
            rating.setRating(mCurrentRating);
            rating.setOnRatingBarChangeListener(this);
        }
        super.onBindView(view);
    }

    @Override
    public void onRatingChanged(RatingBar rating, float value, boolean arg2) {
        // 사용자의 설정 변경이 있을 때 호출
        mCurrentRating = (callChangeListener(value)) ? value : mOldRating;
        persistFloat(mCurrentRating);
        mOldRating = mCurrentRating;
    }

}
