package www.oceancx.com.customviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import www.oceancx.com.customviews.CustView.MessageCounter;

/**
 * Created by oceancx on 15/3/16.
 */
public class MessageCountActivity extends Activity {
    MessageCounter wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.weibomessage);

        wv= (MessageCounter) findViewById(R.id.weibo);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv.addCount();
            }
        });
           
    }
}
