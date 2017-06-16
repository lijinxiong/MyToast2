# MyToast2
![](http://i.imgur.com/6xowrcq.png)       

先上图    

![](http://i.imgur.com/Pzjv0DQ.gi
## 开始 ##
开会看到[Toasty](https://github.com/GrenderG/Toasty)挺好的，直自己用Kotlin写了一个，加上一些动画      
布局没有使用anko，还是使用了xml，主要一开始的时候使用的时候出现了些问题，导致后来直接用xml     
## 使用 ##
![](http://i.imgur.com/NyILDoq.jpg)      


	// custom layout
	fun Activity.layoutToast(@LayoutRes layoutRes: Int, init: View.() -> Unit,ktToastInit: KtToast.() -> KtToast = { this }) {
	    val view = this.layoutInflater.inflate(layoutRes, null)
	    view.init()
	    KtToast(this, view).ktToastInit().show()
	}	

- layoutRes: 布局文件id
- init：对布局文件中的View的一些初始化函数    
- ktToastInit:对Toast的设置，位置，时长   

![](http://i.imgur.com/2qgaGkv.jpg)    
## 说明 ##
动画的开始时在View attach 开始的时候    
借助接口*OnAttachStateChangeListener*    

![](http://i.imgur.com/Ug8J24s.png)    

![](http://i.imgur.com/iyHGPcf.png)    


