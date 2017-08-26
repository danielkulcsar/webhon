package jp.co.se.android.recipe.chapter19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;

public class Ch1904 extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ch1904_main);

        MyAsyncTask task = new MyAsyncTask();
        task.execute();
    }

    /**
     * jsoup로 Elements을 구하기
     */
    private class MyAsyncTask extends AsyncTask<Void, Void, Elements> {

        @Override
        protected Elements doInBackground(Void... params) {
            Elements result = null;
            try {
                Document doc = Jsoup
                        .connect("http://www.infopub.co.kr")
                        .get();
                // CSS 셀렉터로 img 목록을 구하기
                result = doc.select("table tr img");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Elements result) {
            if (result != null) {
                List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
                for (Element element : result) {
                    Map<String, String> m = new HashMap<String, String>();
                    m.put("src", element.attr("src"));
                    m.put("alt", element.attr("alt"));
                    dataList.add(m);
                }
                SimpleAdapter adapter = new SimpleAdapter(Ch1904.this,
                        dataList, android.R.layout.simple_list_item_2,
                        new String[] { "alt", "src" }, new int[] {
                                android.R.id.text1, android.R.id.text2 });
                setListAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            findViewById(R.id.progressBar).setVisibility(View.INVISIBLE);
        }
    }
}
