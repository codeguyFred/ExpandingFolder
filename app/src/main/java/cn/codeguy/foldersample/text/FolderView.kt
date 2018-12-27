package cn.codeguy.foldersample.text

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.codeguy.foldersample.R

/**
 * TODO: document your custom view class.
 */
class FolderView : View {
    var iconTypeFace=Typeface.createFromAsset(context.assets, "iconfont.ttf")
    var textTypeFace=Typeface.createFromAsset(context.assets, "PingFang Regular.ttf")

    var paint: Paint? = Paint()

    var callback: View.OnClickListener? = null

    var bean: FolderBean? =null

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr) {
        //点击事件
        setOnClickListener {
            if (it is FolderView && it.bean!!.type.equals("folder")) {
                callback!!.onClick(it)
            }

        }
        paint?.apply {
            textSize = 120F
            color = Color.BLUE
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        var rect = Rect()
        var rect2 = Rect()
        var icon: String

        bean?.run {
            if (type.equals("folder")) {
                icon = resources.getString(R.string.folder)
            } else {
                icon = resources.getString(R.string.url)
            }

            //文字的坐标是在左下角
            paint?.run {

                typeface =iconTypeFace
                getTextBounds(icon, 0, icon.length, rect)
                var y = ((height - rect.height()) / 2).toFloat()
                canvas!!.drawText(icon,
                        x
                        , y+rect.height(), paint)

                textSize = 64F
                typeface = textTypeFace
                getTextBounds(title, 0, title.length, rect2)


                canvas.drawText(title,  2*x + rect.width(),
                        y+rect2.height(), paint)
                textSize = 120F
            }

        }

    }
}
