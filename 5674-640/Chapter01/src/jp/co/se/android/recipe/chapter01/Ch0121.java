package jp.co.se.android.recipe.chapter01;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class Ch0121 extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ch0121_main);

		// 입력 자동완성용 항목을 생성
		List<String> colorList = new ArrayList<String>();
		colorList.add("좋아하는 색은 빨간색");
		colorList.add("좋아하는 색은 파란색");
		colorList.add("좋아하는 색은 녹색");

		// 입력 자동완성을 설정
		AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete);
		SuggestAdapter adapter = new SuggestAdapter(this, colorList);
		textView.setAdapter(adapter);
	}

	/**
	 * 입력 자동 완성 표시를 사용하는 어댑터
	 */
	private class SuggestAdapter extends ArrayAdapter<String> implements
			SpinnerAdapter {
		private LayoutInflater mInflator;
		private List<String> mItems;

		public SuggestAdapter(Context context, List<String> objects) {
			super(context, R.layout.ch0121_item_suggest, objects);
			mInflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mItems = objects;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			if (convertView == null) {
				convertView = mInflator.inflate(R.layout.ch0121_item_suggest,
						null, false);
				holder = new ViewHolder();
				holder.text = (TextView) convertView
						.findViewById(R.id.spinnerText);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// 제안 후보를 설정
			holder.text.setText(mItems.get(position));

			return convertView;
		}

		private class ViewHolder {
			TextView text;
		}
	}
}
