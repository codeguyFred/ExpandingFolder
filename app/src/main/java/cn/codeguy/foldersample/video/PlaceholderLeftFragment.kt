package cn.codeguy.foldersample.video

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.codeguy.foldersample.DummyContent
import cn.codeguy.foldersample.text.FolderBean
import cn.codeguy.foldersample.R
import kotlinx.android.synthetic.main.fragment_sample_video.view.*

class PlaceholderLeftFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_sample_video, container, false)
            rootView.rv_video.layoutManager= LinearLayoutManager(context)
            var  a=ArrayList<FolderBean>()
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            DummyContent.ITEMS.add(FolderBean(a,"icon","titile","type"))
            rootView.rv_video.adapter=VideoItemAdapter2(DummyContent.ITEMS,
                    object : VideoItemAdapter2.OnListFragmentInteractionListener {
                        override fun onListFragmentInteraction(item: FolderBean?) {}
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
            fun newInstance(sectionNumber: Int): PlaceholderLeftFragment {
                val fragment = PlaceholderLeftFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }