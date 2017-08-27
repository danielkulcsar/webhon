package net.npaka.helloworld;//(1)
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

//HelloWorld
public class HelloWorld extends Activity {//(2)(3)
    //èâä˙âª
    @Override
    public void onCreate(Bundle icicle) {//(4)
        super.onCreate(icicle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//(5)
        setContentView(new HelloView(this));//(6)
    }
}