package cn.codeguy.foldersample.text

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import cn.codeguy.foldersample.R
import kotlinx.android.synthetic.main.activity_popularize.view.*

/**
 * TODO: document your custom view class.
 */
class FolderParentHide : LinearLayout {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr) {

        setWillNotDraw(false)
        orientation= VERTICAL
    }

}
