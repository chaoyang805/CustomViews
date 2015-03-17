package www.oceancx.com.customviews;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.Arrays;

import www.oceancx.com.customviews.CustView.DebugLog;
import www.oceancx.com.customviews.CustView.MeasureUtils;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        MeasureUtils.scale = getResources().getDisplayMetrics().density;
        DebugLog.e("scale:" + MeasureUtils.scale);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_2, android.R.id.text2,
                Arrays.asList(getResources().getStringArray(R.array.names)));

        setListAdapter(adapter);


        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, MessageCountActivity.class);
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, ActivityCompatTest.class);
                        ActivityOptionsCompat options= ActivityOptionsCompat.makeScaleUpAnimation(view, 0 , 0 , 1000, 1000);
                        ActivityCompat.startActivity(MainActivity.this, intent,options.toBundle() );
                        return;
                    case 5:
                        intent.setClass(MainActivity.this, ActivityCompatTest.class);
                        break;
                }
                ActivityOptionsCompat options = ActivityOptionsCompat.makeCustomAnimation(MainActivity.this, R.anim.slide_bottom_in, R.anim.slide_bottom_out);
                ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
