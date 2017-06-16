package lao.yu.xian.kttoast

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import android.widget.ImageView

/**
 * Created by xian_yu_lao on 2017/6/16.
 */

class MyImageView : ImageView, View.OnAttachStateChangeListener {
    override fun onViewDetachedFromWindow(v: View?) {

    }

    override fun onViewAttachedToWindow(v: View?) {

        if (v == null) return
        val animation = TranslateAnimation(Animation.RELATIVE_TO_SELF, 2f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, -2.0f, Animation.RELATIVE_TO_SELF, 0f)
        animation.duration = 1500
        animation.interpolator = BounceInterpolator()
        v.startAnimation(animation)
    }


    init {
        addOnAttachStateChangeListener(this)
    }

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attributeSet: AttributeSet) : super(ctx, attributeSet)


}