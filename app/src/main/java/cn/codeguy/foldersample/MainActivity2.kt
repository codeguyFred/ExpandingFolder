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
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.ArrayList
import cn.codeguy.foldersample.text.FolderBean
import cn.codeguy.foldersample.text.FolderParentHide
import cn.codeguy.foldersample.text.FolderView
import com.google.gson.Gson


/**
 * 
 */

class MainActivity2 : AppCompatActivity() {
    private val mBounds = Rect()
    val removeList: MutableList<FolderBean> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        rv.layoutManager = LinearLayoutManager(this, DividerItemDecoration.HORIZONTAL, false)
        rv.adapter = MyItemRecyclerViewAdapter(DummyContent.ITEMS,
                object : MyItemRecyclerViewAdapter.OnListFragmentInteractionListener {
                    override fun onListFragmentInteraction(item: FolderBean?) {
                        createHeadItem(item)
                        createFolderView(item, folder_container)
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
            createFolderView(bean, folder_container)
        }


    }

    /**
     * 标题栏
     */
    private fun createHeadItem(item: FolderBean?) {
        folder_container.removeAllViews()

        var indexOf = DummyContent.ITEMS.indexOf(item)

        DummyContent.ITEMS.forEachIndexed { index, dummyItem ->
            if (index > indexOf) {
                removeList.add(dummyItem)
            }
        }

        DummyContent.ITEMS.removeAll(removeList)
        rv.adapter!!.notifyDataSetChanged()
    }


    private fun createFolderView(bean: FolderBean?, folderParentHide: FolderParentHide) {
        bean?.apply {
            if (bean.type.equals("folder")) {
                createFolder(bean, folderParentHide)
                DummyContent.ITEMS.add(bean)
                (rv.adapter as MyItemRecyclerViewAdapter).notifyDataSetChanged()
            } else {
                createFile(bean, folderParentHide)
            }
        }

    }


    /**
     * 创建文件夹
     */
    private fun createFolder(bean: FolderBean?, folder_container: LinearLayout) {
        var folderParentHide = FolderParentHide(this)

        var folderView = FolderView(this)
        var layoutParams = LinearLayout.LayoutParams(1000, 200)
        folderView.bean = bean
        var onClickListener = View.OnClickListener {
            var currentParent = it.parent as FolderParentHide
            if (currentParent.childCount == 0) {
                sc.fullScroll(View.FOCUS_DOWN)
                hs.fullScroll(View.FOCUS_RIGHT)
                bean?.folders?.apply {
                    forEach { bean1 ->
                        createFolderView(bean1, folderParentHide)
                    }
                }
            } else {
                if (currentParent.childCount!=1){
                    var childAt = currentParent.getChildAt(0)
                    currentParent.removeAllViews()
                    currentParent.addView(childAt)
                }else{
                    sc.fullScroll(View.FOCUS_DOWN)
                    hs.fullScroll(View.FOCUS_RIGHT)
                    bean?.folders?.apply {
                        forEach { bean1 ->
                            createFolderView(bean1, folderParentHide)
                        }
                    }
                }
            }
        }

        folderView.callback = onClickListener
        folderParentHide.addView(folderView, layoutParams)


        var layoutParams2 = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        layoutParams2.leftMargin = 40

        folder_container?.addView(folderParentHide,layoutParams2)
    }

    /**
     * 创建文件
     */
    private fun createFile(bean: FolderBean?, folder_container: LinearLayout) {
        var folderView = FolderView(this)
        var layoutParams = LinearLayout.LayoutParams(1000, 200)
        layoutParams.leftMargin = 20

        folderView.bean = bean
        folder_container.addView(folderView, layoutParams)
    }

    private var mDivider: Drawable = ColorDrawable(0)

    /**
     * 绘制分隔线
     */
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
