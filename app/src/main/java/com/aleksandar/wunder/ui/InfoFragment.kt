package com.aleksandar.wunder.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aleksandar.wunder.App
import com.aleksandar.wunder.R
import com.aleksandar.wunder.vo.Placemark
import com.aleksandar.wunder.vo.Placemarks
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_info.*
import java.io.Serializable
import javax.inject.Inject


class InfoFragment : Fragment(), ListItemClickListener {

    val TAG = "INFO_FRAGMENT"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mainViewModel: MainViewModel
    private lateinit var infoAdapter: InfoAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (context!!.applicationContext as App).appComponent!!.inject(this)
        mainViewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MainViewModel::class.java)

        initLayoutManager()
        observeInfo()
    }

    /**
     * Initializing the LayoutManager and setting it to RecyclerView
     */
    private fun initLayoutManager(){
        val linearLayoutManager = LinearLayoutManager(context)
        info_recycler_view.layoutManager = linearLayoutManager
    }

    /**
     * Observing the LiveData from the MainViewModel
     * and setting the result to InfoAdapter and RecyclerView
     */
    private fun observeInfo(){
        mainViewModel.getPlacemarks().observe(this, object : Observer<Placemarks>{
            override fun onChanged(placemarks: Placemarks?) {
                infoAdapter = InfoAdapter(placemarks!!, this@InfoFragment)
                info_recycler_view.adapter = infoAdapter
            }
        })
    }

    /**
     * Interface for selected item on the adapter
     */
    override fun selectItem(adapterPosition: Int, placemark: Placemark) {
        //Sets the selected placemark to LiveData in the MainViewModel
        mainViewModel.getPlacemark().value = placemark
        //Sets the current view of the second fragment(MapFragment)
        activity!!.main_view_pager.setCurrentItem(1)
    }
}
