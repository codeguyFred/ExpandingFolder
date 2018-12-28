package cn.codeguy.foldersample.video

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.codeguy.foldersample.R
import cn.codeguy.foldersample.text.FolderBean
import kotlinx.android.synthetic.main.rv_item_video.*
import kotlinx.android.synthetic.main.rv_item_video.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class HomeVideoItemAdapter(
        private val mValues: List<FolderBean>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<HomeVideoItemAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_home_video, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        with(holder.mView) {
            setOnClickListener{
//                mListener?.onListFragmentInteraction(mValues.get(position))

                var intent = Intent(context, VideoDetailActivity::class.java)
                var bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        context as Activity,iv_video,"ppp").toBundle();
                context.startActivity(intent,bundle)

            }
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: FolderBean?)
    }
}
