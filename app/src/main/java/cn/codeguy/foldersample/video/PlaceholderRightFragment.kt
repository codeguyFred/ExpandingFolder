package cn.codeguy.foldersample.video

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import cn.codeguy.foldersample.DividerItemDecoration
import cn.codeguy.foldersample.DummyContent
import cn.codeguy.foldersample.text.FolderBean
import cn.codeguy.foldersample.R
import kotlinx.android.synthetic.main.fragment_sample_video.view.*
import kotlinx.android.synthetic.main.fragment_sample_video2.*
import kotlinx.android.synthetic.main.rv_item_video.*


class PlaceholderRightFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_sample_video2, container, false)
        rootView.rv_video.layoutManager = GridLayoutManager(context, 3)
        var a = ArrayList<FolderBean>()
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))
        DummyContent.ITEMS.add(FolderBean(a, "icon", "titile", "type"))

        rootView.rv_video.addItemDecoration(android.support.v7.widget.DividerItemDecoration(context, android.support.v7.widget.DividerItemDecoration.VERTICAL))
        rootView.rv_video.addItemDecoration(android.support.v7.widget.DividerItemDecoration(context, android.support.v7.widget.DividerItemDecoration.HORIZONTAL))
        rootView.rv_video.adapter = VideoItemAdapter(DummyContent.ITEMS,
                object : VideoItemAdapter.OnListFragmentInteractionListener {
                    override fun onListFragmentInteraction(item: FolderBean?) {

                    }
                })

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): PlaceholderRightFragment {
            val fragment = PlaceholderRightFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setHomeButtonEnabled(true)
        setHasOptionsMenu(true)
    }
}