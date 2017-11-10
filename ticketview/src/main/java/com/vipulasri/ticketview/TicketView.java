package com.vipulasri.ticketview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Vipul Asri on 31/10/17.
 */
public class TicketView extends View {

    public static final String TAG = TicketView.class.getSimpleName();

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ Orientation.HORIZONTAL, Orientation.VERTICAL })
    public @interface Orientation {
        int HORIZONTAL = 0;
        int VERTICAL = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ DividerType.NORMAL, DividerType.DASH })
    public @interface DividerType {
        int NORMAL = 0;
        int DASH = 1;
    }

    private Paint mBackgroundPaint = new Paint();
    private Paint mBorderPaint = new Paint();
    private Paint mDividerPaint = new Paint();
    private int mOrientation;
    private Path mPath = new Path();
    private RectF mRect = new RectF();
    private int mScallopHeight;
    private float mScallopPosition;
    private float mScallopPositionPercent;
    private int mBackgroundColor;
    private boolean mShowBorder;
    private int mBorderWidth;
    private int mBorderColor;
    private boolean mShowDivider;
    private int mScallopRadius;
    private int mDividerDashLength;
    private int mDividerDashGap;
    private int mDividerType;
    private int mDividerWidth;
    private int mDividerColor;

    public TicketView(Context context) {
        super(context);
        init(null);
    }

    public TicketView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TicketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float offset;
        super.onDraw(canvas);
        int left = getPaddingLeft();
        int right = getWidth() - getPaddingRight();
        int top = getPaddingTop();
        int bottom = getHeight() - getPaddingBottom();
        mPath.reset();
        if (mOrientation == Orientation.HORIZONTAL) {
            offset = (float) (((top + bottom) / mScallopPosition) - mScallopRadius);
            mPath.moveTo((float) left, (float) top);
            mPath.lineTo((float) right, (float) top);
            mRect.set((float) (right - mScallopRadius), ((float) top) + offset, (float) (right + mScallopRadius), (((float) mScallopHeight) + offset) + ((float) top));
            mPath.arcTo(mRect, 270, -180.0f, false);
            mPath.lineTo((float) right, (float) bottom);
            mPath.lineTo((float) left, (float) bottom);
            mRect.set((float) (left - mScallopRadius), ((float) top) + offset, (float) (left + mScallopRadius), (((float) mScallopHeight) + offset) + ((float) top));
            mPath.arcTo(mRect, 90.0f, -180.0f, false);
            mPath.close();
        } else {
            offset = (float) (((right + left) / mScallopPosition) - mScallopRadius);
            mPath.moveTo((float) left, (float) top);
            mRect.set(((float) left) + offset, (float) (top - mScallopRadius), (((float) mScallopHeight) + offset) + ((float) left), (float) (top + mScallopRadius));
            mPath.arcTo(mRect, 180.0f, -180.0f, false);
            mPath.lineTo((float) right, (float) top);
            mPath.lineTo((float) right, (float) bottom);
            mRect.set(((float) left) + offset, (float) (bottom - mScallopRadius), (((float) mScallopHeight) + offset) + ((float) left), (float) (bottom + mScallopRadius));
            mPath.arcTo(mRect, 0.0f, -180.0f, false);
            mPath.lineTo((float) left, (float) bottom);
            mPath.close();
        }

        canvas.drawPath(mPath, mBackgroundPaint);

        if (mShowBorder) {
            canvas.drawPath(mPath, mBorderPaint);
        }

        if(mShowDivider) {
            Canvas canvas2;
            if (mOrientation == Orientation.HORIZONTAL) {
                canvas2 = canvas;
                canvas2.drawLine((float) (left + mScallopRadius), ((float) mScallopRadius) + (((float) top) + offset), (float) (right - mScallopRadius), ((float) mScallopRadius) + (((float) top) + offset), mDividerPaint);
                return;
            }
            canvas2 = canvas;
            canvas2.drawLine(((float) mScallopRadius) + (((float) left) + offset), (float) (top + mScallopRadius), ((float) mScallopRadius) + (((float) left) + offset), (float) (bottom - mScallopRadius), mDividerPaint);
        }
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TicketView);
            mOrientation = typedArray.getInt(R.styleable.TicketView_orientation, Orientation.HORIZONTAL);
            mBackgroundColor = typedArray.getColor(R.styleable.TicketView_backgroundColor, getResources().getColor(android.R.color.white));
            mScallopRadius = typedArray.getDimensionPixelSize(R.styleable.TicketView_scallopRadius, Utils.dpToPx(20f, getContext()));
            mScallopPositionPercent = typedArray.getFloat(R.styleable.TicketView_scallopPositionPercent, 50);
            mShowBorder = typedArray.getBoolean(R.styleable.TicketView_showBorder, false);
            mBorderWidth = typedArray.getDimensionPixelSize(R.styleable.TicketView_borderWidth, Utils.dpToPx(2f, getContext()));
            mBorderColor = typedArray.getColor(R.styleable.TicketView_borderColor, getResources().getColor(android.R.color.black));
            mShowDivider = typedArray.getBoolean(R.styleable.TicketView_showDivider, false);
            mDividerType = typedArray.getInt(R.styleable.TicketView_dividerType, DividerType.NORMAL);
            mDividerWidth = typedArray.getDimensionPixelSize(R.styleable.TicketView_dividerWidth, Utils.dpToPx(2f, getContext()));
            mDividerColor = typedArray.getColor(R.styleable.TicketView_dividerColor, getResources().getColor(android.R.color.darker_gray));
            mDividerDashLength = typedArray.getDimensionPixelSize(R.styleable.TicketView_dividerDashLength, Utils.dpToPx(8f, getContext()));
            mDividerDashGap = typedArray.getDimensionPixelSize(R.styleable.TicketView_dividerDashGap, Utils.dpToPx(4f, getContext()));

            typedArray.recycle();
        }

        initElements();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void initElements(){

        if(mDividerWidth>mScallopRadius){
            mDividerWidth = mScallopRadius;
            Log.w(TAG, "You cannot apply divider width greater than scallop radius. Applying divider width to scallop radius.");
        }

        mScallopPosition  = 100 / mScallopPositionPercent;
        mScallopHeight = mScallopRadius * 2;

        setBackgroundPaint();
        setBorderPaint();
        setDividerPaint();

        invalidate();
    }

    private void setBackgroundPaint() {
        mBackgroundPaint.setAlpha(0);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
    }

    private void setBorderPaint() {
        mBorderPaint.setAlpha(0);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    private void setDividerPaint() {
        mDividerPaint.setAlpha(0);
        mDividerPaint.setAntiAlias(true);
        mDividerPaint.setColor(mDividerColor);
        mDividerPaint.setStrokeWidth(mDividerWidth);

        if(mDividerType == DividerType.DASH)
            mDividerPaint.setPathEffect(new DashPathEffect(new float[]{(float) mDividerDashLength, (float) mDividerDashGap}, 0.0f));
        else
            mDividerPaint.setPathEffect(new PathEffect());

    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setOrientation(int orientation) {
        this.mOrientation = orientation;
        initElements();
    }

    public float getScallopPositionPercent() {
        return mScallopPositionPercent;
    }

    public void setScallopPositionPercent(float scallopPositionPercent) {
        this.mScallopPositionPercent = scallopPositionPercent;
        initElements();
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        initElements();
    }

    public boolean isShowBorder() {
        return mShowBorder;
    }

    public void setShowBorder(boolean showBorder) {
        this.mShowBorder = showBorder;
        initElements();
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.mBorderWidth = borderWidth;
        initElements();
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
        initElements();
    }

    public boolean isShowDivider() {
        return mShowDivider;
    }

    public void setShowDivider(boolean showDivider) {
        this.mShowDivider = showDivider;
        initElements();
    }

    public int getScallopRadius() {
        return mScallopRadius;
    }

    public void setScallopRadius(int scallopRadius) {
        this.mScallopRadius = scallopRadius;
        initElements();
    }

    public int getDividerDashLength() {
        return mDividerDashLength;
    }

    public void setDividerDashLength(int dividerDashLength) {
        this.mDividerDashLength = dividerDashLength;
        initElements();
    }

    public int getDividerDashGap() {
        return mDividerDashGap;
    }

    public void setDividerDashGap(int dividerDashGap) {
        this.mDividerDashGap = dividerDashGap;
        initElements();
    }

    public int getDividerType() {
        return mDividerType;
    }

    public void setDividerType(int dividerType) {
        this.mDividerType = dividerType;
        initElements();
    }

    public int getDividerWidth() {
        return mDividerWidth;
    }

    public void setDividerWidth(int dividerWidth) {
        this.mDividerWidth = dividerWidth;
        initElements();
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public void setDividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        initElements();
    }
}
