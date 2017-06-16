package lao.yu.xian.kttoast

import android.graphics.drawable.Drawable
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View

/**
 * Created by xian_yu_lao on 2017/6/16.
 */


inline fun View.getScreenWidth() = this.context.resources.displayMetrics.widthPixels

inline fun View.getScreenHeight() = this.context.resources.displayMetrics.heightPixels

inline fun changeBackgroundColor(drawable: Drawable, tint: Int): Drawable {
    val tem = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(tem, tint)
    return tem
}
