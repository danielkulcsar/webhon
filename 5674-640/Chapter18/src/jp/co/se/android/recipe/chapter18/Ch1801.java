package jp.co.se.android.recipe.chapter18;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Ch1801 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        setContentView(textView);

        Person eric = new Person("Eric", "Schmidt");
        Person andy = new ToStringPerson("Andy", "Rubin");

        String result = String.format("Google: %1$s %2$s\nAndroid:%3$s %4$s",
                eric.getFirstName(), eric.getLastName(), andy.getFirstName(),
                andy.getLastName());

        // 1. ↓의 행에 브레이크 포인트를 설정
        textView.setText(result);

        // 객체수( textView, eric, andy, result )에 마우스를 올려 놓고 확인해요
    }

    private static class Person {
        private String firstName;
        private String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

    }

    private static class ToStringPerson extends Person {

        public ToStringPerson(String firstName, String lastName) {
            super(firstName, lastName);
        }

        @Override
        public String toString() {
            try {
                JSONObject jsRoot = new JSONObject();
                jsRoot.put("firstName", this.getFirstName());
                jsRoot.put("lastName", this.getLastName());

                String json = jsRoot.toString();
                return json;
            } catch (JSONException e) {
            }
            return "{}";
        }
    }
}
