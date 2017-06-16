package lao.yu.xian.kttoast

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.*

/**
 * Created by xian_yu_lao on 2017/6/15.
 */
class KtToast internal constructor(context: Context, view: View) {
    private val toast = Toast(context)

    init {
        toast.view = view
    }

    fun gravity(gravity: Int, xOffset: Int = 0, yOffset: Int = 0): KtToast {
        toast.setGravity(gravity, xOffset, yOffset)
        return this
    }

    fun duration(duration: Int): KtToast {

        if (duration != 0 && duration != 1) toast.duration = 1
        else toast.duration = duration
        return this
    }

    fun show() {
        toast.show()
    }
}

// custom layout
fun Activity.layoutToast(@LayoutRes layoutRes: Int, init: View.() -> Unit,
                         ktToastInit: KtToast.() -> KtToast = { this }) {

    val view = this.layoutInflater.inflate(layoutRes, null)
    view.init()
    KtToast(this, view).ktToastInit().show()

}

//only TextView
fun Activity.messageToast(init: TextView.() -> Unit, ktToastInit: KtToast.() -> KtToast = { this }) {

    layoutToast(R.layout.message_toast, {
        val textView = (this as LinearLayout).getChildAt(0) as TextView
        textView.init()
    }, { ktToastInit() })


}


private inline fun Activity.messageToast(type: MessageType = MessageType.OTHER, message: String,
                                         @DrawableRes resId: Int = R.drawable.message_toast_text_view_background) {

    val drawable = ContextCompat.getDrawable(this, resId)

    messageToast({
        text = message
        setTextColor(Color.WHITE)
        if (type != MessageType.OTHER)
            background = changeBackgroundColor(drawable, type.color)
        else
            background = drawable
    }, {
        duration(1)
        this
    })

}

fun Activity.showMessageToast(message: String, @DrawableRes resId: Int) {
    messageToast(message = message, resId = resId)
}

fun Activity.showErrorMessageToast(message: String) {
    this.messageToast(lao.yu.xian.kttoast.MessageType.ERROR, message)
}

fun Activity.showSuccessMessageToast(message: String) {
    this.messageToast(lao.yu.xian.kttoast.MessageType.SUCCESS, message)
}

fun Activity.showInfoMessageToast(message: String) {
    this.messageToast(lao.yu.xian.kttoast.MessageType.INFO, message)
}

fun Activity.showWarnMessageToast(message: String) {
    this.messageToast(lao.yu.xian.kttoast.MessageType.WARN, message)
}

fun Activity.showNormalMessageToast(message: String) {
    this.messageToast(lao.yu.xian.kttoast.MessageType.NORMAL, message)
}


private fun Activity.imageMessageToast(type: MessageType, textViewInit: TextView.() -> Unit,
                                       imageViewInit: ImageView.() -> Unit,
                                       ktToastInit: KtToast.() -> KtToast = { this }) {


    layoutToast(R.layout.image_message_toast, {

        val root = this as LinearLayout
        val drawable = ContextCompat.getDrawable(this@imageMessageToast, R.drawable.message_toast_text_view_background)
        root.background = changeBackgroundColor(drawable, type.color)

        (root.getChildAt(0) as ImageView).imageViewInit()
        (root.getChildAt(1) as TextView).textViewInit()
    }, ktToastInit)


}

fun Activity.showSuccessImageMessageToast(message: String) {
    imageMessageToast(MessageType.SUCCESS, {
        text = message

    }, {
        setImageDrawable(ContextCompat.getDrawable(this@showSuccessImageMessageToast,
                R.drawable.ic_check_white_48dp))
    })
}


fun Activity.showErrorImageMessageToast(message: String) {
    imageMessageToast(MessageType.ERROR, {
        text = message

    }, {
        setImageDrawable(ContextCompat.getDrawable(this@showErrorImageMessageToast,
                R.drawable.ic_error_outline_white_48dp))
    })
}


fun Activity.showInfoImageMessageToast(message: String) {
    imageMessageToast(MessageType.INFO, {
        text = message

    }, {
        setImageDrawable(ContextCompat.getDrawable(this@showInfoImageMessageToast,
                R.drawable.ic_info_outline_white_48dp))
    })
}


fun Activity.showWarnImageMessageToast(message: String) {
    imageMessageToast(MessageType.WARN, {
        text = message

    }, {
        setImageDrawable(ContextCompat.getDrawable(this@showWarnImageMessageToast,
                R.drawable.ic_clear_white_48dp))
    })
}


fun Activity.showLoadCompleteToast(message: String = "加载完成!", @DrawableRes resId: Int = R.drawable.complete
) {
    layoutToast(R.layout.image_toast, {
        val rootView = this as FrameLayout
        val linearLayout = rootView.getChildAt(0) as LinearLayout

        linearLayout.layoutParams.width = getScreenWidth()
        linearLayout.layoutParams.height = getScreenHeight() / 2

        (linearLayout.getChildAt(0) as ImageView).setImageDrawable(ContextCompat.getDrawable(
                this@showLoadCompleteToast, resId))

        (linearLayout.getChildAt(1) as TextView).text = message

    }, {
        gravity(Gravity.CENTER)
        this
    })
}

fun Activity.showLoadCompleteCarVertical(message: String = "加载完成!", @DrawableRes resId: Int = R.drawable.car) {
    layoutToast(R.layout.car_load_complete_vertical, {
        val rootView = this as FrameLayout
        val linearLayout = rootView.getChildAt(0) as LinearLayout

        linearLayout.layoutParams.width = getScreenWidth()
        val textView = linearLayout.getChildAt(0) as TextView
        val imageView = linearLayout.getChildAt(1) as ImageView

        textView.text = message
        imageView.setImageDrawable(ContextCompat.getDrawable(this@showLoadCompleteCarVertical, resId))

        imageView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View?) {

                if (v == null) return

                val valueAnimator = ValueAnimator()
                val rect = Rect()
                imageView.getDrawingRect(rect)

                valueAnimator.duration = 1500
                valueAnimator.interpolator = LinearInterpolator()
                valueAnimator.setFloatValues(0f, v.getScreenWidth().toFloat())

                valueAnimator.addUpdateListener {
                    val value = it.animatedValue as Float
                    v.translationX = -value

                    if (textView.visibility == View.INVISIBLE && value >= getScreenWidth()) {
                        textView.visibility = View.VISIBLE
                    }

                }

                valueAnimator.start()
            }

            override fun onViewDetachedFromWindow(v: View?) {
            }
        })


    }, {
        gravity(Gravity.CENTER)
        this
    })
}

fun Activity.showLoadCompleteCarHorizontal(message: String = "加载完成!", @DrawableRes resId: Int = R.drawable.car) {
    layoutToast(R.layout.car_load_complete_horizontal, {
        val rootView = this as FrameLayout
        val linearLayout = rootView.getChildAt(0) as LinearLayout

        linearLayout.layoutParams.width = getScreenWidth()
        val imageView = linearLayout.getChildAt(0) as ImageView
        val textView = linearLayout.getChildAt(1) as TextView

        textView.text = message
        imageView.setImageDrawable(ContextCompat.getDrawable(this@showLoadCompleteCarHorizontal, resId))

        imageView.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View?) {

                if (v == null) return

                val valueAnimator = ValueAnimator()
                val rect = Rect()
                imageView.getDrawingRect(rect)

                valueAnimator.duration = 3500
                valueAnimator.interpolator = LinearInterpolator()

                valueAnimator.setFloatValues(0f, v.getScreenWidth().toFloat())

                valueAnimator.addUpdateListener {
                    val value = it.animatedValue as Float
                    v.translationX = -value
                    textView.translationX = -value
                }

                valueAnimator.start()
            }

            override fun onViewDetachedFromWindow(v: View?) {
            }
        })


    }, {
        gravity(Gravity.CENTER)
        this
    })
}

enum class MessageType(val color: Int) {
    ERROR(-2545881), SUCCESS(-10312603), INFO(-11509574), WARN(-151785), NORMAL(-10197916),
    OTHER(0)
}








