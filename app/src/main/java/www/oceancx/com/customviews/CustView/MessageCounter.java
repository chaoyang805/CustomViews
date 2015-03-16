package www.oceancx.com.customviews.CustView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import www.oceancx.com.customviews.R;

/**
 * Created by oceancx on 15/3/16.
 * 用于仿微博下方的消息气泡
 * 可以给这个视图本身设置图片,还可以添加一个bubbleView,对bubbleView的个数进行设置
 * 视图中第一个孩子为50dp 50dp 然后在其上面画一个10dp 10dp的数字气泡
 */
public class MessageCounter extends View {


    private Drawable mDrawable;
    private Drawable mBubble;

    private CharSequence mText;
    private StaticLayout mTextLayout;

    private TextPaint mTextPaint;
    private Point mTextOrigin;


    public MessageCounter(Context context) {
        super(context);
    }

    public MessageCounter(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextOrigin = new Point(0, 0);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MessageCounter);
        Drawable d = a.getDrawable(R.styleable.MessageCounter_android_drawable);
        if (d != null) {
            mDrawable = d;
        }
        d = a.getDrawable(R.styleable.MessageCounter_android_drawableRight);
        if (d != null) {
            mBubble = d;
        }

        mTextPaint.setColor(getResources().getColor(R.color.text));
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.abc_text_size_body_1_material));
        DebugLog.e("size:"+getResources().getDimensionPixelSize(R.dimen.abc_text_size_body_1_material));
        DebugLog.e("mDrawable:" + mDrawable + " mBubble:" + mBubble);
        CharSequence text = a.getText(R.styleable.MessageCounter_android_text);
        mText = text;
        DebugLog.e(mText + "");
        a.recycle();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MessageCounter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //此方法根据父视图提供的规格 测量出自己的规格
        int widthSize = MeasureUtils.getMeasurement(getDesiredWidth(), widthMeasureSpec);
        int heightSize = MeasureUtils.getMeasurement(getDesiredHeight(), heightMeasureSpec);
        DebugLog.e("w:" + widthSize + " h:" + heightSize);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onSizeChanged(int left, int top, int oldw, int oldh) {

        left=top=0;
        mDrawable.setBounds(left, top, left + getWidth(), top + getHeight());

        //设置为父视图右上角1/4处
        mBubble.setBounds(left + getWidth() / 4 * 3, top, left + getWidth(), top + getHeight() / 4);

        float textWidth = mTextPaint.measureText(mText, 0, mText.length());
        if (mTextLayout == null) {
            mTextLayout = new StaticLayout(mText, mTextPaint, (int) textWidth,
                    Layout.Alignment.ALIGN_CENTER, 1f, 0f, true);
            Paint.FontMetrics fm=mTextPaint.getFontMetrics();
            mTextOrigin.set((int) (left + getWidth() / 4 * 3.5 - textWidth / 2), (int) (top + getHeight() / 8 - (fm.descent - fm.ascent) / 2));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mDrawable.draw(canvas);
        mBubble.draw(canvas);

        if (mTextLayout != null) {
            canvas.save();
            canvas.translate(mTextOrigin.x, mTextOrigin.y);

            mTextLayout.draw(canvas);

            canvas.restore();
        }

    }

    public int getDesiredHeight() {
        //获取此视图的高度 50dp
        return mDrawable.getIntrinsicHeight();
    }

    public int getDesiredWidth() {
        //获取此视图的宽度
        return mDrawable.getIntrinsicWidth();
    }

    public void addCount() {
        mText = (1+Integer.valueOf((String) mText)) +"";
        DebugLog.e((String) mText);
        mTextLayout = new StaticLayout(mText, mTextPaint, (int) mTextPaint.measureText((String) mText),
                Layout.Alignment.ALIGN_CENTER, 1f, 0f, true);
        requestLayout();
        invalidate();
    }
}
