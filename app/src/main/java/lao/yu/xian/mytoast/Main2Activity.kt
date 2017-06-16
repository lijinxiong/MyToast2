package lao.yu.xian.mytoast

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import lao.yu.xian.kttoast.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        this.show_success.onClick {
            this@Main2Activity.showSuccessMessageToast("success message")
        }

        this.show_error.onClick {
            this@Main2Activity.showErrorMessageToast("error message")
        }

        this.show_info.onClick {
            this@Main2Activity.showInfoMessageToast("info message")
        }

        this.show_warn.onClick {
            this@Main2Activity.showWarnMessageToast("warn message")
        }
        this.show_normal.onClick {
            this@Main2Activity.showNormalMessageToast("normal message")
        }

        this.show_custom_background.onClick {
            this@Main2Activity.showMessageToast("show custom message_toast_text_view_background ", R.drawable.custom_background)
        }

        this.show_custom_background_and_other.onClick {
            this@Main2Activity.messageToast({
                text = "show_custom_background_and_other"
                background = this@Main2Activity.resources.getDrawable(R.mipmap.ic_launcher)
                setTextSize(12.0f)

            }, {
                gravity(android.view.Gravity.CENTER or android.view.Gravity.TOP, yOffset = 100)

                this
            })
        }

        this.showSuccessImageMessageToast.onClick {
            this@Main2Activity.showSuccessImageMessageToast("showSuccessImageMessageToast")

        }
        this.showErrorImageMessageToast.onClick {
            this@Main2Activity.showErrorImageMessageToast("showErrorImageMessageToast")
        }



        this.showInfoImageMessageToast.onClick {
            this@Main2Activity.showInfoImageMessageToast("showInfoImageMessageToast")
        }

        this.showWarnImageMessageToast.onClick {
            this@Main2Activity.showWarnImageMessageToast("showWarnImageMessageToast")
        }

        this.showLoadCompleteToast.onClick {

            this@Main2Activity.showLoadCompleteToast()

        }


        this.showLoadCompleteCarVertical.onClick {
            this@Main2Activity.showLoadCompleteCarVertical("加载完成!")
        }


        this.showLoadCompleteCarHorizontal.onClick {
            this@Main2Activity.showLoadCompleteCarHorizontal("加载完成!")
        }


    }
}
