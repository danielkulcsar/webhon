package com.example.dbreportcard;

import java.util.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    DbHelper mDbHelper;
    SQLiteDatabase mDb;
    EditText mEditName;
    EditText mEditKorean;
    EditText mEditMath;
    ListView mListMember;
    Cursor mCursor;
    ArrayList<String> mArMember = new ArrayList<String>();
    ArrayAdapter<String> mAdapter;
    int mSelIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mEditName = (EditText)findViewById(R.id.editName);
        mEditKorean = (EditText)findViewById(R.id.editKorean);
        mEditMath = (EditText)findViewById(R.id.editMath);

        mDbHelper = new DbHelper(this);
        mDb = mDbHelper.getWritableDatabase();
        initListView();
        readAllRecords();
    }

    class DbHelper extends SQLiteOpenHelper {
        public DbHelper(Context context) {
            super(context, "ReportCard", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table Student (" +
                    "_id integer PRIMARY KEY autoincrement, " +
                    "name TEXT, korean integer, math integer);");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists Student");
            onCreate(db);
        }
    }

    public void onBtnAdd() {
        String strName = mEditName.getText().toString();
        String strKorean = mEditKorean.getText().toString();
        String strMath = mEditMath.getText().toString();

        String strQuery = "insert into Student(name, korean, math) values (' " 
                + strName + " ', " + strKorean + ", " + strMath + ");";
        mDb.execSQL(strQuery);

        readAllRecords();
        mCursor.moveToLast();
        mSelIndex = mCursor.getInt(0);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.buttonAdd :
            onBtnAdd();
            break;
        case R.id.buttonDel :
            onBtnDel();
            break;
        case R.id.buttonUpdate :
            onBtnUpdate();
            break;
        }
    }

    public void readAllRecords() {
        mArMember.clear();

        String strQuery = "select _id, name, korean, math from Student";
        mCursor = mDb.rawQuery(strQuery, null);

        for(int i=0; i < mCursor.getCount(); i++) {
            mCursor.moveToNext();
            int nId = mCursor.getInt(0);
            String strName = mCursor.getString(1);
            int nKorean = mCursor.getInt(2);
            int nMath = mCursor.getInt(3);
            String strRecord = nId + ": " + strName + " / " + nKorean + " / " + nMath;
            Log.d("tag", "Rec-" + strRecord);
            mArMember.add(strRecord);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void initListView() {
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mArMember);
        mListMember = (ListView)findViewById(R.id.listMember);
        mListMember.setAdapter(mAdapter);
        mListMember.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListMember.setDivider(new ColorDrawable(Color.GRAY));
        mListMember.setDividerHeight(2);
        mListMember.setOnItemClickListener(mItemListener);
    }

    public void viewRecord(int nIndex) {
        mCursor.moveToPosition(nIndex);
        int nId = mCursor.getInt(0);
        String strName = mCursor.getString(1);
        int nKorean = mCursor.getInt(2);
        int nMath = mCursor.getInt(3);

        mEditName.setText(strName);
        mEditKorean.setText(Integer.toString(nKorean));
        mEditMath.setText(Integer.toString(nMath));
        mSelIndex = nId;
    }

    AdapterView.OnItemClickListener mItemListener = new 
            AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            viewRecord(position);
        }
    };

    public void onBtnDel() {
        mDb.execSQL("delete from Student where _id = " + mSelIndex);
        readAllRecords();
    }

    public void onBtnUpdate() {
        String strName = mEditName.getText().toString();
        String strKorean = mEditKorean.getText().toString();
        String strMath = mEditMath.getText().toString();

        String strQuery = "update Student set name = ' " + strName + 
                " ', korean = " + strKorean + ", math = " + strMath + 
                " where _id = " + mSelIndex;
        mDb.execSQL(strQuery);
        readAllRecords();
    }

}
