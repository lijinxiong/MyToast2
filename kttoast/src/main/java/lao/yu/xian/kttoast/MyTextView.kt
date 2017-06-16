package lao.yu.xian.kttoast

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import android.widget.TextView

/**
 * Created by xian_yu_lao on 2017/6/15.

 */
class MyTextView : TextView, View.OnAttachStateChangeListener {


    override fun onViewDetachedFromWindow(v: View?) {

    }

    override fun onViewAttachedToWindow(v: View?) {
        val anim = TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0f)
        anim.duration = 1500
        anim.interpolator = BounceInterpolator()
        anim.fillAfter = true
        v?.startAnimation(anim)
    }

    init {
        this.addOnAttachStateChangeListener(this)

    }

    constructor(ctx: Context) : super(ctx)
    constructor(ctx: Context, attributeSet: AttributeSet) : super(ctx, attributeSet)


}