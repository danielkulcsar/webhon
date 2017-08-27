package net.npaka.oauthtwitterclient;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;

// 트위터 클라이언트
public class OAuthTwitterClient extends Activity {
	//정수
	private final static String
		CONSUMER_KEY ="컨슈머 키를 입력함", // 등록된 자신의 컨슈머 키
		CONSUMER_SECRET="컨슈머 시크릿을 입력함"; // 등록된 자신의 컨슈머 시크릿
	private final String CALLBACKURL="myapp://mainactivity";
	private static final int
		MENU_SETUP =0,
		MENU_UPDATE=1;
	
	// 인증
	private CommonsHttpOAuthConsumer consumer;
	private OAuthProvider provider;
	
	// 정보
	private ListView listView;
	private static HashMap<String,Drawable> icons=
		new HashMap<String,Drawable>();
	private ArrayList<Status> timeline=
		new ArrayList<Status>();
	
	// 어플리케이션의 초기화
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		// 레이아웃의 생성
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		// 리스트 뷰의 생성 (1)
		listView=new ListView(this);
		setLLParams(listView);
		layout.addView(listView);
		
		// 인증
		doOauth(false);
	}
	
	// 옵션 메뉴의 생성
	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item0=menu.add(0,MENU_SETUP,0,"설정");
		item0.setIcon(android.R.drawable.ic_menu_add);
		MenuItem item1=menu.add(0,MENU_UPDATE,0,"갱신");
		item1.setIcon(android.R.drawable.ic_menu_call);
		return true;
	}
	
	// 메뉴 아이템 선택 이벤트의 처리
	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		try {
			switch (item.getItemId()) {
				// 설정
			case MENU_SETUP:
				doOauth(true);
				return true;
				// 갱신
			case MENU_UPDATE:
				updateTimeline();
				return true;
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	// 타임 라인의 갱신
	private void updateTimeline() {
		//ArrayList<Status>
		timeline=new ArrayList<Status>();
		Status status =null;
		String tagName=null;
		InputStream in=null;
		try {
			// 접속
			HttpGet httpGet=new HttpGet(
				"http://twitter.com/statuses/friends_timeline.xml");
			consumer.sign(httpGet);
			DefaultHttpClient http=new DefaultHttpClient();
			HttpResponse execute=http.execute(httpGet);
			
			// XML의 분석 처리 (4)
			in=execute.getEntity().getContent();
			final XmlPullParser parser=Xml.newPullParser();
			parser.setInput(new InputStreamReader(in));
			while (true) {
				int type=parser.next();
				// 문서 시작
				if (type==XmlPullParser.START_DOCUMENT) {
				}
				// 태그 시작
				else if (type==XmlPullParser.START_TAG) {
					tagName=parser.getName();
					if (tagName.equals("status")) {
						status=new Status();
						timeline.add(status);
					}
				}
				// 텍스트
				else if (type==XmlPullParser.TEXT) {
					if (parser.getText().trim().length()==0) {
					} else if (tagName.equals("screen_name")) {
						status.name=parser.getText();
					} else if (tagName.equals("text")) {
						status.text=parser.getText();
					} else if (tagName.equals("profile_image_url")) {
						status.iconURL=parser.getText();
					}
				}
				// 태그 종료
				else if (type==XmlPullParser.END_TAG) {
				}
				// 문서 종료
				else if (type==XmlPullParser.END_DOCUMENT) {
					break;
				}
			}
			
			// 닫기
			in.close();
			
			// 갱신
			for (int i=0;i<timeline.size();i++) {
				timeline.get(i).icon=readIcon(timeline.get(i).iconURL);
			}
			listView.setAdapter(new TwitterAdapter(this));
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
		}
	}
	
	// 아이콘 읽기
	private Drawable readIcon(String url) throws Exception {
		Drawable drawable=icons.get(url);
		if (drawable!=null) return drawable;
		byte[] data=http2data(url);
		Bitmap bmp=BitmapFactory.decodeByteArray(data,0,data.length);
		drawable=new BitmapDrawable(bmp);
		icons.put(url,drawable);
		return drawable;
	}
	
	// HTTP 통신
	private byte[] http2data(String path) throws Exception {
		int size;
		byte[] w=new byte[1024];
		HttpURLConnection c=null;
		InputStream in=null;
		ByteArrayOutputStream out=null;
		try {
			// HTTP 접속 개방
			URL url=new URL(path);
			c=(HttpURLConnection) url.openConnection();
			c.setRequestMethod("GET");
			c.connect();
			in=c.getInputStream();
			
			// 바이트 배열의 읽기
			out=new ByteArrayOutputStream();
			while (true) {
				size=in.read(w);
				if (size<=0) break;
				out.write(w,0,size);
			}
			out.close();
			
			// HTTP 접속의 닫기
			in.close();
			c.disconnect();
			return out.toByteArray();
		} catch (Exception e) {
			try {
				if (c!=null) c.disconnect();
				if (in!=null) in.close();
				if (out!=null) out.close();
			} catch (Exception e2) {
			}
			throw e;
		}
	}
	
	// 리니어 레이아웃의 파라미터 지정
	private static void setLLParams(View view) {
		view.setLayoutParams(new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT));
	}
	
	// 트위터 어댑터의 생성 (2)
	public class TwitterAdapter extends BaseAdapter {
		private Context context;
		//constructor　 　
		public TwitterAdapter(Context c) {
			context=c;
		}
		
		// 항목 수 구하기
		public int getCount() {
			return timeline.size();
		}
		
		// 항목 구하기
		public Object getItem(int position) {
			return timeline.get(position);
		}
		
		// 항목 ID 구하기
		public long getItemId(int position) {
			return position;
		}
		
		// 뷰 구하기
		public View getView(int position,
			View convertView,ViewGroup parent) {
			TextView textView=new TextView(context);
			textView.setTextSize(12.0f);
			textView.setCompoundDrawablesWithIntrinsicBounds(
				timeline.get(position).icon,null,null,null);
			textView.setText("["+timeline.get(position).name+"]\n"+
				timeline.get(position).text);
			return textView;
		}
	}
	
	// OAuth 인증에 의한 통신 (3)
	private void doOauth(boolean setup) {
		try {
			consumer=new CommonsHttpOAuthConsumer(
				CONSUMER_KEY,CONSUMER_SECRET);
			provider=new DefaultOAuthProvider(
				"http://twitter.com/oauth/request_token",
				"http://twitter.com/oauth/access_token",
				"http://twitter.com/oauth/authorize");
			
			// 토큰의 읽기
			SharedPreferences pref=getSharedPreferences(
				"token",MODE_PRIVATE);
			String token =pref.getString("token","");
			String tokenSecret=pref.getString("tokenSecret","");
			
			// 인증이 끝난 상태
			if (!setup && token.length()>0 && tokenSecret.length()>0) {
				consumer.setTokenWithSecret(token,tokenSecret);
				
				// 트위터 조작
				updateTimeline();
			}
			// 인증 처리를 위해 브라우저 기동
			else {
				String url=provider.retrieveRequestToken(
					consumer,CALLBACKURL);
				this.startActivity(
					new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		} catch (Exception e) {
			Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
		}
	}
	
	// 인증 완료 시 불린다.
	@Override
		protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Uri uri=intent.getData();
		if (uri!=null && uri.toString().startsWith(CALLBACKURL)) {
			String verifier=uri.getQueryParameter(
				oauth.signpost.OAuth.OAUTH_VERIFIER);
			try {
				provider.retrieveAccessToken(consumer,verifier);
				
				// 토큰의 기입
				SharedPreferences pref=getSharedPreferences(
					"token",MODE_PRIVATE);
				SharedPreferences.Editor editor=pref.edit();
				editor.putString("token",consumer.getToken());
				editor.putString("tokenSecret",consumer.getTokenSecret());
				editor.commit();
				
				// 트위터 조작
				updateTimeline();
			} catch(Exception e){
				Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
			}
		}
	}
}