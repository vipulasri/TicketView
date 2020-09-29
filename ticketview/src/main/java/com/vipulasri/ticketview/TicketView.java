package com.vipulasri.ticketview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.graphics.Bitmap.Config.ARGB_8888;
import static android.graphics.Color.TRANSPARENT;
import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.graphics.PorterDuff.Mode.SRC_IN;

/**
 * Created by Vipul Asri on 31/10/17.
 */

public class TicketView extends View {

    public static final String TAG = TicketView.class.getSimpleName();

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Orientation.HORIZONTAL, Orientation.VERTICAL})
    public @interface Orientation {
        int HORIZONTAL = 0;
        int VERTICAL = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DividerType.NORMAL, DividerType.DASH})
    public @interface DividerType {
        int NORMAL = 0;
        int DASH = 1;
    }

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CornerType.NORMAL, CornerType.ROUNDED})
    public @interface CornerType {
        int NORMAL = 0;
        int ROUNDED = 1;
        int SCALLOP = 2;
    }

    private Paint mBackgroundPaint = new Paint();
    private Paint mBorderPaint = new Paint();
    private Paint mDividerPaint = new Paint();
    private int mOrientation;
    private Path mPath = new Path();
    private boolean mDirty = true;
    private float mDividerStartX, mDividerStartY, mDividerStopX, mDividerStopY;
    private RectF mRect = new RectF();
    private RectF mRoundedCornerArc = new RectF();
    private RectF mScallopCornerArc = new RectF();
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
    private int mCornerType;
    private int mCornerRadius;
    private int mDividerPadding;
    private Bitmap mShadow;
    private final Paint mShadowPaint = new Paint(ANTI_ALIAS_FLAG);
    private int mShadowColor;
    private float mShadowBlurRadius = 0f;
    private Drawable mBackgroundBeforeDivider;
    private Drawable mBackgroundAfterDivider;

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
        super.onDraw(canvas);
        if (mDirty) {
            doLayout();
        }
        if (mShadowBlurRadius > 0f && !isInEditMode()) {
            canvas.drawBitmap(mShadow, 0f, mShadowBlurRadius / 2f, null);
        }
        canvas.drawPath(mPath, mBackgroundPaint);
        canvas.clipPath(mPath);
        if (mShowBorder) {
            canvas.drawPath(mPath, mBorderPaint);
        }
        if (mShowDivider) {
            canvas.drawLine(mDividerStartX, mDividerStartY, mDividerStopX, mDividerStopY, mDividerPaint);
        }
        if (mBackgroundAfterDivider != null) {
            setTicketBackgroundAfterDivider(canvas);
        }
        if (mBackgroundBeforeDivider != null) {
            setTicketBackgroundBeforeDivider(canvas);
        }
    }

    private void doLayout() {
        float offset;
        float left = getPaddingLeft() + mShadowBlurRadius;
        float right = getWidth() - getPaddingRight() - mShadowBlurRadius;
        float top = getPaddingTop() + (mShadowBlurRadius / 2);
        float bottom = getHeight() - getPaddingBottom() - mShadowBlurRadius - (mShadowBlurRadius / 2);
        mPath.reset();

        if (mOrientation == Orientation.HORIZONTAL) {
            offset = ((top + bottom) / mScallopPosition) - mScallopRadius;

            if (mCornerType == CornerType.ROUNDED) {
                mPath.arcTo(getTopLeftCornerRoundedArc(top, left), 180.0f, 90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerRoundedArc(top, right), -90.0f, 90.0f, false);

            } else if (mCornerType == CornerType.SCALLOP) {
                mPath.arcTo(getTopLeftCornerScallopArc(top, left), 90.0f, -90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerScallopArc(top, right), 180.0f, -90.0f, false);

            } else {
                mPath.moveTo(left, top);
                mPath.lineTo(right, top);
            }

            mRect.set(right - mScallopRadius, top + offset, right + mScallopRadius, mScallopHeight + offset + top);
            mPath.arcTo(mRect, 270, -180.0f, false);

            if (mCornerType == CornerType.ROUNDED) {

                mPath.arcTo(getBottomRightCornerRoundedArc(bottom, right), 0.0f, 90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

                mPath.lineTo(left + mCornerRadius, bottom);
                mPath.arcTo(getBottomLeftCornerRoundedArc(left, bottom), 90.0f, 90.0f, false);

            } else if (mCornerType == CornerType.SCALLOP) {

                mPath.arcTo(getBottomRightCornerScallopArc(bottom, right), 270.0f, -90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

                mPath.lineTo(left + mCornerRadius, bottom);
                mPath.arcTo(getBottomLeftCornerScallopArc(left, bottom), 0.0f, -90.0f, false);

            } else {
                mPath.lineTo(right, bottom);
                mPath.lineTo(left, bottom);
            }

            mRect.set(left - mScallopRadius, top + offset, left + mScallopRadius, mScallopHeight + offset + top);
            mPath.arcTo(mRect, 90.0f, -180.0f, false);
            mPath.close();

        } else {
            offset = (((right + left) / mScallopPosition) - mScallopRadius);

            if (mCornerType == CornerType.ROUNDED) {
                mPath.arcTo(getTopLeftCornerRoundedArc(top, left), 180.0f, 90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

            } else if (mCornerType == CornerType.SCALLOP) {

                mPath.arcTo(getTopLeftCornerScallopArc(top, left), 90.0f, -90.0f, false);
                mPath.lineTo(left + mCornerRadius, top);

            } else {
                mPath.moveTo(left, top);
            }

            mRect.set(left + offset, top - mScallopRadius, mScallopHeight + offset + left, top + mScallopRadius);
            mPath.arcTo(mRect, 180.0f, -180.0f, false);

            if (mCornerType == CornerType.ROUNDED) {

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerRoundedArc(top, right), -90.0f, 90.0f, false);

                mPath.arcTo(getBottomRightCornerRoundedArc(bottom, right), 0.0f, 90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

            } else if (mCornerType == CornerType.SCALLOP) {

                mPath.lineTo(right - mCornerRadius, top);
                mPath.arcTo(getTopRightCornerScallopArc(top, right), 180.0f, -90.0f, false);

                mPath.arcTo(getBottomRightCornerScallopArc(bottom, right), 270.0f, -90.0f, false);
                mPath.lineTo(right - mCornerRadius, bottom);

            } else {
                mPath.lineTo(right, top);
                mPath.lineTo(right, bottom);
            }

            mRect.set(left + offset, bottom - mScallopRadius, mScallopHeight + offset + left, bottom + mScallopRadius);
            mPath.arcTo(mRect, 0.0f, -180.0f, false);

            if (mCornerType == CornerType.ROUNDED) {

                mPath.arcTo(getBottomLeftCornerRoundedArc(left, bottom), 90.0f, 90.0f, false);
                mPath.lineTo(left, bottom - mCornerRadius);

            } else if (mCornerType == CornerType.SCALLOP) {

                mPath.arcTo(getBottomLeftCornerScallopArc(left, bottom), 0.0f, -90.0f, false);
                mPath.lineTo(left, bottom - mCornerRadius);

            } else {
                mPath.lineTo(left, bottom);
            }
            mPath.close();
        }

        // divider
        if (mOrientation == Orientation.HORIZONTAL) {
            mDividerStartX = left + mScallopRadius + mDividerPadding;
            mDividerStartY = mScallopRadius + top + offset;
            mDividerStopX = right - mScallopRadius - mDividerPadding;
            mDividerStopY = mScallopRadius + top + offset;
        } else {
            mDividerStartX = mScallopRadius + left + offset;
            mDividerStartY = top + mScallopRadius + mDividerPadding;
            mDividerStopX = mScallopRadius + left + offset;
            mDividerStopY = bottom - mScallopRadius - mDividerPadding;
        }

        generateShadow();
        mDirty = false;
    }

    private void generateShadow() {
        if (Utils.isJellyBeanAndAbove() && !isInEditMode()) {
            if (mShadowBlurRadius == 0f) return;

            if (mShadow == null) {
                mShadow = Bitmap.createBitmap(getWidth(), getHeight(), ARGB_8888);
            } else {
                mShadow.eraseColor(TRANSPARENT);
            }

            Canvas canvas = new Canvas(mShadow);
            canvas.drawPath(mPath, mShadowPaint);
            if (mShowBorder) {
                canvas.drawPath(mPath, mShadowPaint);
            }

            mShadow = BlurBuilder.blur(getContext(), mShadow, mShadowBlurRadius);
        }
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TicketView);
            mBackgroundBeforeDivider = typedArray.getDrawable(R.styleable.TicketView_ticketBackgroundBeforeDivider);
            mBackgroundAfterDivider = typedArray.getDrawable(R.styleable.TicketView_ticketBackgroundAfterDivider);
            mOrientation = typedArray.getInt(R.styleable.TicketView_ticketOrientation, Orientation.HORIZONTAL);
            mBackgroundColor = typedArray.getColor(R.styleable.TicketView_ticketBackgroundColor, getResources().getColor(android.R.color.white));
            mScallopRadius = typedArray.getDimensionPixelSize(R.styleable.TicketView_ticketScallopRadius, Utils.dpToPx(20f, getContext()));
            mScallopPositionPercent = typedArray.getFloat(R.styleable.TicketView_ticketScallopPositionPercent, 50);
            mShowBorder = typedArray.getBoolean(R.styleable.TicketView_ticketShowBorder, false);
            mBorderWidth = typedArray.getDimensionPixelSize(R.styleable.TicketView_ticketBorderWidth, Utils.dpToPx(2f, getContext()));
            mBorderColor = typedArray.getColor(R.styleable.TicketView_ticketBorderColor, getResources().getColor(android.R.color.black));
            mShowDivider = typedArray.getBoolean(R.styleable.TicketView_ticketShowDivider, false);
            mDividerType = typedArray.getInt(R.styleable.TicketView_ticketDividerType, DividerType.NORMAL);
            mDividerWidth = typedArray.getDimensionPixelSize(R.styleable.TicketView_ticketDividerWidth, Utils.dpToPx(2f, getContext()));
            mDividerColor = typedArray.getColor(R.styleable.TicketView_ticketDividerColor, getResources().getColor(android.R.color.darker_gray));
            mDividerDashLength = typedArray.getDimensionPixelSize(R.styleable.TicketView_ticketDividerDashLength, Utils.dpToPx(8f, getContext()));
            mDividerDashGap = typedArray.getDimensionPixelSize(R.styleable.TicketView_ticketDividerDashGap, Utils.dpToPx(4f, getContext()));
            mCornerType = typedArray.getInt(R.styleable.TicketView_ticketCornerType, CornerType.NORMAL);
            mCornerRadius = typedArray.getDimensionPixelSize(R.styleable.TicketView_ticketCornerRadius, Utils.dpToPx(4f, getContext()));
            mDividerPadding = typedArray.getDimensionPixelSize(R.styleable.TicketView_ticketDividerPadding, Utils.dpToPx(10f, getContext()));
            float elevation = 0f;
            if (typedArray.hasValue(R.styleable.TicketView_ticketElevation)) {
                elevation = typedArray.getDimension(R.styleable.TicketView_ticketElevation, elevation);
            } else if (typedArray.hasValue(R.styleable.TicketView_android_elevation)) {
                elevation = typedArray.getDimension(R.styleable.TicketView_android_elevation, elevation);
            }
            if (elevation > 0f) {
                setShadowBlurRadius(elevation);
            }
            mShadowColor = typedArray.getColor(R.styleable.TicketView_ticketShadowColor, getResources().getColor(android.R.color.black));

            typedArray.recycle();
        }

        initElements();

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    private void initElements() {

        if (mDividerWidth > mScallopRadius) {
            mDividerWidth = mScallopRadius;
            Log.w(TAG, "You cannot apply divider width greater than scallop radius. Applying divider width to scallop radius.");
        }

        mScallopPosition = 100 / mScallopPositionPercent;
        mScallopHeight = mScallopRadius * 2;

        setShadowPaint();
        setBackgroundPaint();
        setBorderPaint();
        setDividerPaint();

        mDirty = true;
        invalidate();
    }

    private void setTicketBackgroundBeforeDivider(Canvas canvas) {
        //getting the bound of view for setting background
        float left = getPaddingLeft() + mShadowBlurRadius;
        float right = getWidth() - getPaddingRight() - mShadowBlurRadius;
        float top = getPaddingTop() + (mShadowBlurRadius / 2);
        float bottom = getHeight() - getPaddingBottom() - mShadowBlurRadius - (mShadowBlurRadius / 2);
        if (mOrientation == Orientation.HORIZONTAL) {
            mBackgroundBeforeDivider.setBounds((int) left, (int) top, (int) right, (int) mDividerStartY);
        } else {
            mBackgroundBeforeDivider.setBounds((int) left, (int) top, (int) mDividerStartX, (int) bottom);
        }
        mBackgroundBeforeDivider.draw(canvas);
    }

    private void setTicketBackgroundAfterDivider(Canvas canvas) {
        //getting the bound of view for setting background
        float left = getPaddingLeft() + mShadowBlurRadius;
        float right = getWidth() - getPaddingRight() - mShadowBlurRadius;
        float top = getPaddingTop() + (mShadowBlurRadius / 2);
        float bottom = getHeight() - getPaddingBottom() - mShadowBlurRadius - (mShadowBlurRadius / 2);
        if (mOrientation == Orientation.HORIZONTAL) {
            mBackgroundAfterDivider.setBounds((int) left, (int) mDividerStopY, (int) right, (int) bottom);
        } else {
            mBackgroundAfterDivider.setBounds((int) mDividerStopX, (int) top, (int) right, (int) bottom);
        }
        mBackgroundAfterDivider.draw(canvas);
    }

    private void setShadowPaint() {
        mShadowPaint.setColorFilter(new PorterDuffColorFilter(mShadowColor, SRC_IN));
        mShadowPaint.setAlpha(51); // 20%
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

        if (mDividerType == DividerType.DASH)
            mDividerPaint.setPathEffect(new DashPathEffect(new float[]{(float) mDividerDashLength, (float) mDividerDashGap}, 0.0f));
        else
            mDividerPaint.setPathEffect(new PathEffect());
    }

    private RectF getTopLeftCornerRoundedArc(float top, float left) {
        mRoundedCornerArc.set(left, top, left + mCornerRadius * 2, top + mCornerRadius * 2);
        return mRoundedCornerArc;
    }

    private RectF getTopRightCornerRoundedArc(float top, float right) {
        mRoundedCornerArc.set(right - mCornerRadius * 2, top, right, top + mCornerRadius * 2);
        return mRoundedCornerArc;
    }

    private RectF getBottomLeftCornerRoundedArc(float left, float bottom) {
        mRoundedCornerArc.set(left, bottom - mCornerRadius * 2, left + mCornerRadius * 2, bottom);
        return mRoundedCornerArc;
    }

    private RectF getBottomRightCornerRoundedArc(float bottom, float right) {
        mRoundedCornerArc.set(right - mCornerRadius * 2, bottom - mCornerRadius * 2, right, bottom);
        return mRoundedCornerArc;
    }

    private RectF getTopLeftCornerScallopArc(float top, float left) {
        mScallopCornerArc.set(left - mCornerRadius, top - mCornerRadius, left + mCornerRadius, top + mCornerRadius);
        return mScallopCornerArc;
    }

    private RectF getTopRightCornerScallopArc(float top, float right) {
        mScallopCornerArc.set(right - mCornerRadius, top - mCornerRadius, right + mCornerRadius, top + mCornerRadius);
        return mScallopCornerArc;
    }

    private RectF getBottomLeftCornerScallopArc(float left, float bottom) {
        mScallopCornerArc.set(left - mCornerRadius, bottom - mCornerRadius, left + mCornerRadius, bottom + mCornerRadius);
        return mScallopCornerArc;
    }

    private RectF getBottomRightCornerScallopArc(float bottom, float right) {
        mScallopCornerArc.set(right - mCornerRadius, bottom - mCornerRadius, right + mCornerRadius, bottom + mCornerRadius);
        return mScallopCornerArc;
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

    public int getDividerPadding() {
        return mDividerPadding;
    }

    public void setDividerPadding(int mDividerPadding) {
        this.mDividerPadding = mDividerPadding;
        initElements();
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public void setDividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        initElements();
    }

    public int getCornerType() {
        return mCornerType;
    }

    public void setCornerType(int cornerType) {
        this.mCornerType = cornerType;
        initElements();
    }

    public int getCornerRadius() {
        return mCornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.mCornerRadius = cornerRadius;
        initElements();
    }

    public void setTicketElevation(float elevation) {
        if (!Utils.isJellyBeanAndAbove()) {
            Log.w(TAG, "Ticket elevation only works with Android Jelly Bean and above");
            return;
        }
        setShadowBlurRadius(elevation);
        initElements();
    }

    private void setShadowBlurRadius(float elevation) {
        if (!Utils.isJellyBeanAndAbove()) {
            Log.w(TAG, "Ticket elevation only works with Android Jelly Bean and above");
            return;
        }
        float maxElevation = Utils.dpToPx(24f, getContext());
        mShadowBlurRadius = Math.min(25f * (elevation / maxElevation), 25f);
    }

    public int getShadowColor() {
        return mShadowColor;
    }

    public void setShadowColor(int color) {
        this.mShadowColor = color;
        initElements();
    }

    public Drawable getBackgroundBeforeDivider() {
        return mBackgroundBeforeDivider;
    }

    public void setBackgroundBeforeDivider(Drawable background) {
        this.mBackgroundBeforeDivider = background;
        initElements();
    }

    public Drawable getBackgroundAfterDivider() {
        return mBackgroundAfterDivider;
    }

    public void setBackgroundAfterDivider(Drawable background) {
        this.mBackgroundAfterDivider = background;
        initElements();
    }
}
