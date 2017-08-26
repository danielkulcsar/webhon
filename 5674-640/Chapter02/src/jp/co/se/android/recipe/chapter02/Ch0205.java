package jp.co.se.android.recipe.chapter02;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Ch0205 extends ListActivity {
    /***
     * 인덱스 데이터
     * 
     * @author yokmama
     * 
     */
    private class BindData {
        String title;
        String line1;
        String line2;

        public BindData(String string0, String string1, String string2) {
            this.title = string0;
            this.line1 = string1;
            this.line2 = string2;
        }
    }

    // 인덱스를 표시하기 위한 샘플 데이터
    private BindData[] INDEX_DATA = new BindData[] {
            new BindData("타이틀１", null, null),
            new BindData(null, "foo", "bar"),
            new BindData("타이틀2", null, null),
            new BindData(null, "hoge", "fuga"),
            new BindData(null, "null", "po"),
            new BindData("타이틀3", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"),
            new BindData("타이틀4", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"),
            new BindData("타이틀5", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"),
            new BindData("타이틀6", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"),
            new BindData("타이틀7", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"),
            new BindData("타이틀8", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"),
            new BindData("타이틀9", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"),
            new BindData("타이틀10", null, null),
            new BindData(null, "hoge", "hoge"),
            new BindData(null, "null", "po"), };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0205_main);

        // 인덱스 데이터를 추가
        List<BindData> list = new ArrayList<BindData>();
        for (int i = 0; i < INDEX_DATA.length; i++) {
            list.add(INDEX_DATA[i]);
        }

        // 인덱스 표시 어댑터 설정
        Ch0205Adapter adapter = new Ch0205Adapter(this, list);

        // 어댑터를 설정
        setListAdapter(adapter);
    }

    /***
     * 리스트 고속표시를 위해 View를 유지하기 위한 클래스
     * 
     * @author yokmama
     * 
     */
    private class ViewHolder {
        TextView title;
        TextView line1;
        TextView line2;
    }

    private class Ch0205Adapter extends ArrayAdapter<BindData> {
        private LayoutInflater mInflater;

        public Ch0205Adapter(Context context, List<BindData> objects) {
            super(context, 0, objects);
            this.mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public boolean isEnabled(int position) {
            // 선택 불가능하게 함
            return true;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            // 리스트 아이템 표시용 레이아웃을 읽기 생성
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.ch0205_list_item,
                        parent, false);
                // 리스트의 표시를 고속화하기 위해 View 저장용 클래스를 생성해 Tag로 설정
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.line1 = (TextView) convertView.findViewById(R.id.line1);
                holder.line2 = (TextView) convertView.findViewById(R.id.line2);
                convertView.setTag(holder);
            } else {
                // View에 저장된 인스턴스를 Tag로부터 구함
            	holder = (ViewHolder) convertView.getTag();
            }

            // Adapter에 설정된 리스트로부터 BindData를 구함
            BindData data = getItem(position);

            if (getItem(position).title != null) {
                // 인덱스용의 인덱스 데이터라면 인덱스 타이틀을 표시
                holder.title.setVisibility(View.VISIBLE);
                holder.title.setText(data.title);
                holder.line1.setVisibility(View.GONE);
                holder.line2.setVisibility(View.GONE);
                // line1,2
            } else {
                // 인덱스용의 데이터에 없는 경우는 텍스트만 표시
                holder.title.setVisibility(View.GONE);
                holder.line1.setVisibility(View.VISIBLE);
                holder.line1.setText(data.line1);
                holder.line2.setVisibility(View.VISIBLE);
                holder.line2.setText(data.line2);
            }
            return convertView;
        }
    }
}