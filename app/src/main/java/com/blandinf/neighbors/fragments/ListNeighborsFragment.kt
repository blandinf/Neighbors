package com.blandinf.neighbors.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blandinf.neighbors.R
import com.blandinf.neighbors.adapters.ListNeighborHandler
import com.blandinf.neighbors.adapters.ListNeighborsAdapter
import com.blandinf.neighbors.data.repositories.NeighborRepository
import com.blandinf.neighbors.databinding.ListNeighborsFragmentBinding
import com.blandinf.neighbors.listeners.NavigationListener
import com.blandinf.neighbors.models.Neighbor


class ListNeighborsFragment : Fragment(), ListNeighborHandler {

    // lateinit permet d'indiquer au compilateur que la variable sera initialisé plus tard -> Dans le onCreateView
    private lateinit var binding: ListNeighborsFragmentBinding
    private lateinit var neighbors: List<Neighbor>
    private lateinit var adapter: ListNeighborsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        neighbors = NeighborRepository.getInstance().getNeighbours()
        adapter = ListNeighborsAdapter(neighbors, this)
        binding.neighborsList.adapter = adapter

        binding.redirectToAddNeighbor.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(AddNeighborFragment())
            }
        }

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.neighbors)
        }
    }

    // Quand on clique sur le bouton delete dans la liste, l'adapteur appelera cette méthode
    override fun onDeleteNeibor(neighbor: Neighbor) {
        displayDeleteDialog(neighbor)
    }

    fun displayDeleteDialog (neighbor: Neighbor) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.confirmation)
        builder.setMessage(getString(R.string.confirm))

        builder.setPositiveButton(R.string.yes) { dialog, which ->
            NeighborRepository.getInstance().deleteNeighbor(neighbor)
            adapter.notifyDataSetChanged()
        }

        builder.setNegativeButton(R.string.no) { dialog, which ->
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