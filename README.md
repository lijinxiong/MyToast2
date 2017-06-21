![](http://upload-images.jianshu.io/upload_images/2038754-2c9409bd1cdb8cb0.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)   



![](http://upload-images.jianshu.io/upload_images/2038754-470a9a6dcaf939ee.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)       

先上图    

![](http://upload-images.jianshu.io/upload_images/2038754-6575849bf6fdb86b.gif?imageMogr2/auto-orient/strip)     
 
**效果全部为原生Toast，通过添加自定义view实现**   
 

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




## 开始 ##
开会看到[Toasty](https://github.com/GrenderG/Toasty)挺好的，直自己用Kotlin写了一个，加上一些动画      
布局没有使用anko，还是使用了xml，主要一开始的时候使用的时候出现了些问题，导致后来直接用xml     
## 使用 ##
![](http://upload-images.jianshu.io/upload_images/2038754-fc8e0b70c21d382c.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)      


	// custom layout
	fun Activity.layoutToast(@LayoutRes layoutRes: Int, init: View.() -> Unit,ktToastInit: KtToast.() -> KtToast = { this }) {
	    val view = this.layoutInflater.inflate(layoutRes, null)
	    view.init()
	    KtToast(this, view).ktToastInit().show()
	}	

- layoutRes: 布局文件id
- init：对布局文件中的View的一些初始化函数    
- ktToastInit:对Toast的设置，位置，时长   

![](http://upload-images.jianshu.io/upload_images/2038754-7c3895f1cde76b01.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)    
## 说明 ##
动画的开始时在View attach 开始的时候    
借助接口*OnAttachStateChangeListener*    

![](http://upload-images.jianshu.io/upload_images/2038754-87007ca23bf25ee8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)    

![](http://upload-images.jianshu.io/upload_images/2038754-a66902992c638d50.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)     

[github地址](https://github.com/lijinxiong/MyToast2)
