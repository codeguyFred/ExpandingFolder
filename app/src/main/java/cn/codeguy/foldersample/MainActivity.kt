package cn.codeguy.foldersample

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import cn.codeguy.foldersample.text.FolderBean
import cn.codeguy.foldersample.text.FolderView
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    private val mBounds = Rect()
    val removeList: MutableList<FolderBean> = ArrayList()
    var onClickListener: View.OnClickListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this, DividerItemDecoration.HORIZONTAL, false)
        rv.adapter = MyItemRecyclerViewAdapter(DummyContent.ITEMS,
                object : MyItemRecyclerViewAdapter.OnListFragmentInteractionListener {
                    override fun onListFragmentInteraction(item: FolderBean?) {
                        createHeadItem(item)
                    }
                })

        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                var childLayoutPosition = parent.getChildLayoutPosition(view)
                if (childLayoutPosition == state.itemCount - 1) {
                    outRect.set(0, 0, 0, 0)
                } else {
                    outRect.set(0, 0, 30, 0)
                }
            }

            override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                super.onDraw(c, parent, state)
                drawHorizontal(c, parent)
            }
        })
        /*   rv.addItemDecoration(object :DividerItemDecoration(
                   this,
                   LinearLayoutManager.HORIZONTAL
           ){
           })*/


//        var inputStream = assets.open("folder.json")
//        var buffer = ByteArray(inputStream.available())
//        inputStream.read(buffer)
//        var s= String(buffer)


        assets.open("folder2.json").buffered().reader().use {
            var bean = Gson().fromJson(it.readText(), FolderBean::class.java)
            createHeadItem(bean)
        }


    }

    private fun createHeadItem(item: FolderBean?) {
        folder_container.removeAllViews()
        creteFolderView(item)

        var indexOf = DummyContent.ITEMS.indexOf(item)

        DummyContent.ITEMS.forEachIndexed { index, dummyItem ->
            if (index > indexOf) {
                removeList.add(dummyItem)
            }
        }

        DummyContent.ITEMS.removeAll(removeList)
        rv.adapter!!.notifyDataSetChanged()
    }

    private fun creteFolderView(bean: FolderBean?): FolderView {
        var folderView = FolderView(this)
        var layoutParams = LinearLayout.LayoutParams(1000, 200)
        folder_container.addView(folderView, layoutParams)

        folderView.bean = bean

        if (onClickListener == null) {
            onClickListener = View.OnClickListener {
                sc.fullScroll(View.FOCUS_DOWN)
                hs.fullScroll(View.FOCUS_RIGHT)
                var _bean = (it as FolderView).bean!!

                if (_bean.type.equals("folder")) {
                    DummyContent.ITEMS.add(_bean)
                    (rv.adapter as MyItemRecyclerViewAdapter).notifyDataSetChanged()

                    folder_container.removeAllViews()

                    _bean.folders.forEach {
                        creteFolderView(it)
                    }
                }

            }
        }

        folderView.callback = onClickListener

        return folderView
    }

    private var mDivider: Drawable = ColorDrawable(0)

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
        } else {
            top = 0
            bottom = parent.height
        }

        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, this.mBounds)
            val right = this.mBounds.right + Math.round(child.translationX)
            val left = right - this.mDivider.getIntrinsicWidth()

            var rect1 = Rect(left - 30, top, right, bottom)
            this.mDivider.setBounds(rect1)

            val paint = Paint()
            paint.textSize = 28f
            paint.color = Color.BLACK

            var rect = Rect()
            paint.getTextBounds(">", 0, 1, rect)

//          left - (rect1.width() - rect.width()) / 2F
            canvas.drawText(">", left - 30 + ((rect1.width() - rect.width()).toFloat() / 2F),
                    (rect1.height() - rect.height()).toFloat() / 2F + rect.height(), paint)

            this.mDivider.draw(canvas)
        }

        canvas.restore()
    }


}
