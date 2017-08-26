package jp.co.se.android.recipe.chapter02;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Ch0223 extends Activity implements TabListener {
    private static final int MAX_PAGES = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        // Actionbar의 내비게이션 모드를 탭에 설정
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // 샘플로써 MAX_PAGES문의 탭을 추가
        for (int i = 0; i < MAX_PAGES; i++) {
            String tabName = String.format("Tab(%1$s)", i);
            // 탭명을 바탕으로 탭을 작성해 추가
            actionBar.addTab(actionBar.newTab().setText(tabName)
                    .setTabListener(this));
        }
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // 탭이 선택될 때 각 탭을 표시하는 View를 가진 프래그먼트를 생성해 화면을 교체
        ft.replace(android.R.id.content,
                PageFragment.newInstance(tab.getPosition()));
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }

    /**
     * 각 탭마다의 화면으로써 샘플에서는 중앙에 문자열을 표시할 뿐인 심플한 프래그먼트
     */
    public static class PageFragment extends Fragment {
        public static final String TAB_NAME = "Page";
        public static final String EXTRA_PAGE_NUM = "extra.pageNum";

        public static final PageFragment newInstance(int pageNum) {
            PageFragment f = new PageFragment();
            Bundle args = new Bundle();
            args.putInt(EXTRA_PAGE_NUM, pageNum);
            f.setArguments(args);

            return f;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            int pageNum = getArguments().getInt(EXTRA_PAGE_NUM);

            TextView textView = new TextView(getActivity());
            textView.setText(String.format("Page (%1$s)", pageNum));

            return textView;
        }
    }
}
