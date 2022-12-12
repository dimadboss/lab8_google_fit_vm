package com.example.lab8_google_fit.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.lab8_google_fit.MainActivity
import com.example.lab8_google_fit.R
import com.example.lab8_google_fit.data.StepsData

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mainActivity: MainActivity

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        mainActivity = this.activity as MainActivity
        viewModel.init(mainActivity)
        viewModel.getSteps(mainActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listView = mainActivity.findViewById(R.id.steps_list_view)

        val nameObserver = Observer<MutableList<StepsData>> { newList ->
            val recipeList = newList//viewModel.stepsListLive.value ?: mutableListOf<StepsData>()
            val listItems = arrayOfNulls<String>(recipeList.size)
            for (i in 0 until recipeList.size) {
                val recipe = recipeList[i]
                listItems[i] = recipe.timestamp.toString()
            }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, listItems)
            listView.adapter = adapter
        }

        viewModel.stepsListLive.observe(viewLifecycleOwner, nameObserver)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

}