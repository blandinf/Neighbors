package com.blandinf.neighbors.ui.fragments

import android.app.AlertDialog
import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blandinf.neighbors.R
import com.blandinf.neighbors.adapters.ListNeighborHandler
import com.blandinf.neighbors.adapters.ListNeighborsAdapter
import com.blandinf.neighbors.databinding.ListNeighborsFragmentBinding
import com.blandinf.neighbors.di.DI
import com.blandinf.neighbors.listeners.NavigationListener
import com.blandinf.neighbors.models.Neighbor
import com.blandinf.neighbors.viewmodels.NeighborViewModel

class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var adapter: ListNeighborsAdapter
    private lateinit var application: Application
    private lateinit var viewModel: NeighborViewModel
    private var isInMemory: Boolean? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        binding.neighborsList.layoutManager = LinearLayoutManager(context)
        binding.neighborsList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        application = activity?.application ?: return
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
        isInMemory = arguments?.getBoolean("isInMemory")
        getDatasAccordingToUserChoice()

        binding.redirectToAddNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighborFragment())
        }

        (activity as? NavigationListener)?.updateTitle(R.string.neighbors)
    }

    private fun getDatasAccordingToUserChoice() {
        when (isInMemory) {
            null, false -> setData()
            true -> {
                val neighbors = DI.repository.getFakesNeighbors()
                adapter = ListNeighborsAdapter(neighbors, this)
                binding.neighborsList.adapter = adapter
            }
        }
    }

    private fun setData() {
        viewModel.getNeighbors().observe(
            viewLifecycleOwner,
            Observer<List<Neighbor>> { list ->
                adapter = ListNeighborsAdapter(list, this@ListNeighborsFragment)
                binding.neighborsList.adapter = adapter
            }
        )
    }

    override fun onDeleteNeibor(neighbor: Neighbor) {
        displayDeleteDialog(neighbor)
    }

    override fun showNeighborDetails(neighbor: Neighbor) {
        val bundle = Bundle()
        bundle.putParcelable("neighbor", neighbor)
        val fragment = NeighborDetailsFragment()
        fragment.arguments = bundle
        fragmentManager!!.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    private fun displayDeleteDialog(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.confirmation)
        builder.setMessage(getString(R.string.confirm))

        builder.setPositiveButton(R.string.yes) { _, _ ->
            viewModel.deleteNeighbor(neighbor)
        }

        builder.setNegativeButton(R.string.no) { _, _ ->
            //
        }
        builder.show()
    }
}
