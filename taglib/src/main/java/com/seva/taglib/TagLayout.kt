package com.seva.taglib

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import kotlin.math.max

class TagLayout(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : ViewGroup(context, attrs, defStyleAttr) {

    private val mTextViewList: MutableList<TextView> = ArrayList()

    private var mTextSize: Float = 0F
    private var mTextColor: Int = Color.BLACK
    private var mTypeface: Typeface? = null
    private var mHorizontalPadding: Float = 0F
    private var mVerticalPadding: Float = 0F
    private var mTagMargin: Float = 0F
    private var mBackground: GradientDrawable? = null
    private var mBackgroundColor: Int
    private var mBackgroundBorderColor: Int
    private var mBackgroundBorderWidth: Float
    private var mBackgroundCornerRadius: Float

    //Constructor **********************************************************************************
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    constructor(context: Context) : this(context, null, 0)

    //Initializer **********************************************************************************
    init {
        val typedArray = context.obtainStyledAttributes(
            attrs, R.styleable.TagLayout, 0, 0
        )

        mTextSize = typedArray.getDimension(
            R.styleable.TagLayout_android_textSize,
            resources.getDimension(R.dimen.textsize_normal)
        )

        mTextColor = typedArray.getColor(
            R.styleable.TagLayout_android_textColor,
            Color.BLACK
        )

        val fontId = typedArray.getResourceId(R.styleable.TagLayout_android_fontFamily, -1)
        if (fontId != -1) {
            mTypeface = ResourcesCompat.getFont(context, fontId)
        }

        mHorizontalPadding = typedArray.getDimension(
            R.styleable.TagLayout_tl_horizontal_padding, 0F
        )
        mVerticalPadding = typedArray.getDimension(
            R.styleable.TagLayout_tl_vertical_padding, 0F
        )
        mTagMargin = typedArray.getDimension(
            R.styleable.TagLayout_tl_tag_margin, 0F
        )

        mBackgroundColor = typedArray.getColor(
            R.styleable.TagLayout_tl_background_color,
            Color.WHITE
        )
        mBackgroundBorderColor = typedArray.getColor(
            R.styleable.TagLayout_tl_background_border_color,
            Color.BLACK
        )
        mBackgroundBorderWidth = typedArray.getDimension(
            R.styleable.TagLayout_tl_background_border_width,
            0F
        )
        mBackgroundCornerRadius = typedArray.getDimension(
            R.styleable.TagLayout_tl_background_corner_radius,
            0F
        )

        mBackground?.setColor(mBackgroundColor)
        mBackground?.setStroke(mBackgroundBorderWidth.toInt(), mBackgroundBorderColor)
        mBackground?.cornerRadius = mBackgroundCornerRadius

        typedArray.recycle()

    }

    //**********************************************************************************************
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val unspecifiedMeasureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)

        var widthSum = 0
        var totalHeight = 0
        var heightMax = 0

        mTextViewList.forEach { textView: TextView ->
            textView.measure(unspecifiedMeasureSpec, unspecifiedMeasureSpec)

            if (textView.measuredWidth + widthSum > widthSize) {
                widthSum = 0
                totalHeight += heightMax + mTagMargin.toInt()
                heightMax = 0
            }

            widthSum += textView.measuredWidth + mTagMargin.toInt()
            heightMax = max(heightMax, textView.measuredHeight)
        }

        totalHeight += heightMax

        val totalHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
            totalHeight, MeasureSpec.EXACTLY
        )

        setMeasuredDimension(widthMeasureSpec, totalHeightMeasureSpec)

    }

    //**********************************************************************************************
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        val width = right - left

        var widthSum = 0
        var heightMax = 0
        var currentTop = 0
        var currentLeft = 0

        mTextViewList.forEach { textView: TextView ->
            if (textView.measuredWidth + widthSum > width) {
                widthSum = 0
                currentLeft = 0
                currentTop += heightMax + mTagMargin.toInt()
                heightMax = 0
            }

            textView.layout(
                currentLeft,
                currentTop,
                currentLeft + textView.measuredWidth,
                currentTop + textView.measuredHeight
            )

            currentLeft += textView.measuredWidth + mTagMargin.toInt()
            widthSum += textView.measuredWidth + mTagMargin.toInt()
            heightMax = max(heightMax, textView.measuredHeight)
        }

    }

    //**********************************************************************************************
    fun addTags(tags: List<String>) {
        tags.forEach { tag: String ->
            addTag(tag)
        }

        requestLayout()
    }

    fun addTag(tag: String) {
        val textView = TextView(context)
        textView.apply {
            if (mTypeface != null) {
                setTypeface(mTypeface, Typeface.NORMAL)
            }

            text = tag
            setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
            setTextColor(mTextColor)
            gravity = Gravity.CENTER
            setPadding(
                mHorizontalPadding.toInt(),
                mVerticalPadding.toInt(),
                mHorizontalPadding.toInt(),
                mVerticalPadding.toInt()
            )

            setBackgroundResource(R.drawable.tag_background)
            val background = background.mutate() as? GradientDrawable
            background?.setColor(mBackgroundColor)
            background?.setStroke(mBackgroundBorderWidth.toInt(), mBackgroundBorderColor)
            background?.cornerRadius = mBackgroundCornerRadius
        }

        mTextViewList.add(textView)
        addView(textView)

        requestLayout()
    }

}