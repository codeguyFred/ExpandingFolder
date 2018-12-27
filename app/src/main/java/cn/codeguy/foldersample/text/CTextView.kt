package cn.codeguy.foldersample.text

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

class CTextView @JvmOverloads constructor(context: Context,
                                          attrs: AttributeSet? = null,
                                          defStyleAttr: Int = 0) :
        AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        val fromAsset = Typeface.createFromAsset(context.assets, "iconfont.ttf")
        typeface = fromAsset
    }
}
