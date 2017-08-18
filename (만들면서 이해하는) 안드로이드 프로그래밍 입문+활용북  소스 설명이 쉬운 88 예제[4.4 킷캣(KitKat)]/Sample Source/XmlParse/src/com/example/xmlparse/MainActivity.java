package com.example.xmlparse;

import java.io.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    TextView mTextSource;
    TextView mTextMessage;
    String mStrXml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSource = (TextView)findViewById(R.id.textSource);
        mTextMessage = (TextView)findViewById(R.id.textMessage);
        mStrXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<grade>\n"
                + "<name>M.I.T</name>\n"
                + "<student><name>Obama</name><math>50</math></student>\n"
                + "<student><name>Psy</name><math>70</math></student>\n"
                + "<student><name>Yuna</name><math>65</math></student>\n"
                + "</grade>\n";
        mTextSource.setText(mStrXml);
    }
    
    public Element getXmlElement(String strXml) {
        Element element = null;
        try {
            InputStream is = new ByteArrayInputStream(strXml.getBytes("utf-8"));
            DocumentBuilder db = 
                    DocumentBuilderFactory.newInstance().newDocumentBuilder();
            element = db.parse(is).getDocumentElement();
        } catch(Exception e) {
            Log.d("tag", "Parse Error");
            return null;
        }

        return element;
    }

    public void onBtnParse1() {
        Element element = getXmlElement(mStrXml);
        if( element == null )
            return;

        String strData = "Name";
        NodeList nodeList = element.getElementsByTagName("name");
        for(int i=0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            Node childNode = item.getFirstChild();
            strData += " - " + childNode.getNodeValue() ;
        }

        mTextMessage.setText(strData);
    }

    public void onBtnParse2() {
        Element element = getXmlElement(mStrXml);
        if( element == null )
            return;

        String strData="";
        NodeList nodeList = element.getElementsByTagName("student");
        for(int i=0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            NodeList childList = item.getChildNodes();
            Node child1 = childList.item(0).getFirstChild();
            Node child2 = childList.item(1).getFirstChild();
            strData += child1.getNodeValue() + " - " + child2.getNodeValue() + "\n";
        }

        mTextMessage.setText(strData);
    }

    public void onClick(View v) {
        switch( v.getId() ) {
        case R.id.btnParse1 :
            onBtnParse1();
            break;
        case R.id.btnParse2 :
            onBtnParse2();
            break;
        }
    }

}
