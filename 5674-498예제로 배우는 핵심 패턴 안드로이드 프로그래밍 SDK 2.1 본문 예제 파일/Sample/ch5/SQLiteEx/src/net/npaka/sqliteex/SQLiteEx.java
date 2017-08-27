package net.npaka.sqliteex;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

//데이터베이스의 읽고 쓰기
public class SQLiteEx extends Activity
implements View.OnClickListener {
	private final static String DB_NAME ="test.db";//DB명
	private final static String DB_TABLE="test"; //테이블명
	private final static int DB_VERSION=1; //버전
	
	private EditText editText;//텍스트 박스
	private Button btnWrite;//쓰기 버튼
	private Button btnRead; //읽기 버튼
	private SQLiteDatabase db; //데이터베이스 객체
	
	//초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(255,255,255));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		//텍스트 박스의 생성
		editText=new EditText(this);
		editText.setText("",EditText.BufferType.NORMAL);
		setLLParams(editText,240,50);
		layout.addView(editText);
		
		//쓰기 버튼의 생성
		btnWrite=new Button(this);
		btnWrite.setText("DB 쓰기");
		btnWrite.setOnClickListener(this);
		setLLParams(btnWrite);
		layout.addView(btnWrite);
		
		//읽기 버튼의 생성
		btnRead=new Button(this);
		btnRead.setText("DB 읽기");
		btnRead.setOnClickListener(this);
		setLLParams(btnRead);
		layout.addView(btnRead);
		
		//데이터베이스 객체 구하기 (5）
		DBHelper dbHelper=new DBHelper(this);
		db=dbHelper.getWritableDatabase();
	}
	
	//버튼 클릭 이벤트 처리
	public void onClick(View v) {
		if (v==btnWrite) {
			try {
				String str=editText.getText().toString();
				writeDB(str);
			} catch (Exception e) {
				showDialog(this,"에러","쓰기 실패");
			}
		} else if (v==btnRead) {
			try {
				String str=readDB();
				editText.setText(str);
			} catch (Exception e) {
				showDialog(this,"에러","읽기 실패");
			}
		}
	}
	
	//데이터베이스의 쓰기 (6）
	private void writeDB(String info) throws Exception {
		ContentValues values=new ContentValues();
		values.put("id","0");
		values.put("info",info);
		int colNum=db.update(DB_TABLE,values,null,null);
		if (colNum==0) db.insert(DB_TABLE,"",values);
	}
	
	//데이터베이스의 읽기 (7）
	private String readDB() throws Exception {
		Cursor c=db.query(DB_TABLE,new String[]{"id","info"},
			"id='0'",null,null,null,null);
		if (c.getCount()==0) throw new Exception();
		c.moveToFirst();
		String str=c.getString(1);
		c.close();
		return str;
	}
	
	//데이터베이스 헬퍼 정의 (1）
	private static class DBHelper extends SQLiteOpenHelper {
		//데이터베이스 헬퍼 생성자 (2）
		public DBHelper(Context context) {
			super(context,DB_NAME,null,DB_VERSION);
		}
		
		//데이터베이스의 생성 (3）
		@Override
			public void onCreate(SQLiteDatabase db) {
			db.execSQL(
				"create table if not exists "+
				DB_TABLE+"(id text primary key,info text)");
		}
		
		//데이터베이스의 업그레이드 (4）
		@Override
			public void onUpgrade(SQLiteDatabase db,
			int oldVersion,int newVersion) {
			db.execSQL(
				"drop talbe if exists "+DB_TABLE);
			onCreate(db);
		}
	}
	
	//대화상자의 표시
	private static void showDialog(final Activity activity,String title,String text){
		AlertDialog.Builder ad=new AlertDialog.Builder(activity);
		ad.setTitle(title);
		ad.setMessage(text);
		ad.setPositiveButton("OK",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int whichButton) {
				activity.setResult(Activity.RESULT_OK);
			}
		});
		ad.create();
		ad.show();
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	
	//리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view,int w,int h) {
		view.setLayoutParams(new LinearLayout.LayoutParams(w,h));
	}
}
