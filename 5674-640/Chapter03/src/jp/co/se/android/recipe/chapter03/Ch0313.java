package jp.co.se.android.recipe.chapter03;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Ch0313 extends Activity implements OnItemClickListener {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch0313_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mTextView = (TextView) findViewById(R.id.text);

        setupNavigationDrawer();

        // 목록 데이터를 설정
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.add("Menu1");
        adapter.add("Menu2");
        adapter.add("Menu3");
        mDrawerList.setAdapter(adapter);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    private void setupNavigationDrawer() {
        // NavigationDrawer의 그림자로 설정할 Drawable을 지정
        mDrawerLayout.setDrawerShadow(R.drawable.ch0313_drawer_shadow,
                GravityCompat.START);
        mDrawerList.setOnItemClickListener(this);

        // ActionBar 아이콘의 왼쪽에 DrawerToggle을 표시
        getActionBar().setDisplayHomeAsUpEnabled(true);
        // ActionBar 홈버튼을 사용 가능하게 함
        getActionBar().setHomeButtonEnabled(true);

        // 드로워를 개폐 시에 이벤트를 받을 수 있게 함
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.ch0313_drawer_open,
                R.string.ch0313_drawer_close) {
            @Override
            public void onDrawerClosed(View view) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // DrawerToggle측의 옵션 메뉴 선택을 처리할 수 있도록
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // DrawerToggle측의 옵션 메뉴를 제어할 수 있도록
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // DrawerToggle측에서 위/아래 변경을 제어할 수 있도록
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View parent,
            int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {
        ListAdapter adapter = mDrawerList.getAdapter();
        String item = (String) adapter.getItem(position);
        mTextView.setText("선택한 항목: " + item);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

}
