package cn.codeguy.foldersample.video

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.codeguy.foldersample.DummyContent
import cn.codeguy.foldersample.text.FolderBean
import cn.codeguy.foldersample.R
import kotlinx.android.synthetic.main.fragment_sample_video.view.*

class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_sample_video, container, false)
            var viewPagerLayoutManager = ViewPagerLayoutManager(context, OrientationHelper.VERTICAL)
            viewPagerLayoutManager.setOnViewPagerListener(object:OnViewPagerListener{
                override fun onInitComplete() {
                }

                override fun onPageRelease(isNext: Boolean, position: Int) {
                }

                override fun onPageSelected(position: Int, isBottom: Boolean) {
                }
            })
            rootView.rv_video.layoutManager= viewPagerLayoutManager
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
            rootView.rv_video.adapter=HomeVideoItemAdapter(DummyContent.ITEMS,
                    object : HomeVideoItemAdapter.OnListFragmentInteractionListener {
                        override fun onListFragmentInteraction(item: FolderBean?) {
                            startActivity(Intent(activity,UserDetailActivity::class.java))
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
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }