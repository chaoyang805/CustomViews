package www.oceancx.com.customviews.CustView;

import android.view.View;

/**
 * Created by oceancx on 15/3/16.
 */
public class MeasureUtils {
    public static int getMeasurement(int desiredSize, int measureSpec) {
        //根据设定的size 和 mode  返回响应的spec
        int size = View.MeasureSpec.getSize(measureSpec);
        int mode = View.MeasureSpec.getMode(measureSpec);
        DebugLog.e("Size:"+size);
        switch (mode) {
            case View.MeasureSpec.UNSPECIFIED:
                //如果是不限定子视图大小的话 , 那么返回子视图自身大小
                DebugLog.e("UNSPECIFIED");
                return desiredSize;
            case View.MeasureSpec.EXACTLY:
                DebugLog.e("EXACTLY");
                return size;
            case View.MeasureSpec.AT_MOST:
                DebugLog.e("AT_MOST");
                return Math.min(desiredSize, size);
        }
        return size;
    }

    public static float scale;

    public static int dip2pix(int i) {
        return (int)(i * scale + 0.5);
    }
}
