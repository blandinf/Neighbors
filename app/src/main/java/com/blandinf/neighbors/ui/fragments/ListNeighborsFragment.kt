package com.blandinf.neighbors.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blandinf.neighbors.R
import com.blandinf.neighbors.adapters.ListNeighborHandler
import com.blandinf.neighbors.adapters.ListNeighborsAdapter
import com.blandinf.neighbors.databinding.ListNeighborsFragmentBinding
import com.blandinf.neighbors.listeners.NavigationListener
import com.blandinf.neighbors.models.Neighbor
import com.blandinf.neighbors.repositories.NeighborRepository

class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    // lateinit permet d'indiquer au compilateur que la variable sera initialisé plus tard -> Dans le onCreateView
    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var neighbors: LiveData<List<Neighbor>>
    private lateinit var adapter: ListNeighborsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        neighbors = NeighborRepository.getInstance().getNeighbors()
        setData()

        binding.redirectToAddNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.showFragment(AddNeighborFragment())
        }

        (activity as? NavigationListener)?.updateTitle(R.string.neighbors)
    }

    private fun setData() {
        neighbors.observe(
            viewLifecycleOwner,
            Observer<List<Neighbor>> { list ->
                adapter = ListNeighborsAdapter(list, this@ListNeighborsFragment)
                binding.neighborsList.adapter = adapter
            }
        )
    }

    // Quand on clique sur le bouton delete dans la liste, l'adapteur appelera cette méthode
    override fun onDeleteNeibor(neighbor: Neighbor) {
        displayDeleteDialog(neighbor)
    }

    private fun displayDeleteDialog(neighbor: Neighbor) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.confirmation)
        builder.setMessage(getString(R.string.confirm))

        builder.setPositiveButton(R.string.yes) { _, _ ->
            NeighborRepository.getInstance().deleteNeighbor(neighbor)
            adapter.notifyDataSetChanged()
        }

        builder.setNegativeButton(R.string.no) { _, _ ->
            //
        }
        builder.show()
    }

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListNeighborsFragmentBinding.inflate(inflater, container, false)
        binding.neighborsList.layoutManager = LinearLayoutManager(context)
        binding.neighborsList.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        return binding.root
    }
}
