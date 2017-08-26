package jp.co.se.android.recipe.chapter15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class Ch1512 extends Activity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTextView = new TextView(this);
        setContentView(mTextView);

        // JSON 대이터 읽기
        String json = readTestJson("ch1512.json");
        if (json == null) {
            mTextView.setText("파일 읽기에 실패했습니다.");
            return;
        }
        /*
         * 읽어 들인 JSON 데이터： { "data": [{ "title": "hoge", "body": "fugafuga" }, {
         * "title": "foo", "body": "barbar" }] }
         */
        mTextView.setText("");
        mTextView.append("json:\n");
        mTextView.append(json);

        try {
            mTextView.append("\n\n parse 결과");
            // 읽은 JSON 데이터 parse
            JSONObject jsRoot = new JSONObject(json);
            JSONArray jsDataList = jsRoot.getJSONArray("data");

            for (int i = 0, iL = jsDataList.length(); i < iL; i++) {
                JSONObject jsData = jsDataList.getJSONObject(i);
                String title = jsData.getString("title");
                String body = jsData.getString("body");

                mTextView.append("\n title: " + title + ", body: " + body);
            }
        } catch (JSONException e) {
            mTextView.setText("JSON parse에 실패했습니다. JSONException=" + e);
        }
    }

    private String readTestJson(String filePath) {
        AssetManager assets = getResources().getAssets();
        InputStream is = null;
        BufferedReader br = null;

        StringBuilder sb = new StringBuilder();
        try {
            is = assets.open(filePath);
            br = new BufferedReader(new InputStreamReader(is));

            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
