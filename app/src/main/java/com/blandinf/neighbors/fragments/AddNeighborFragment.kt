package com.blandinf.neighbors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.blandinf.neighbors.R
import com.blandinf.neighbors.adapters.ListNeighborsAdapter
import com.blandinf.neighbors.data.repositories.NeighborRepository
import com.blandinf.neighbors.databinding.AddNeighborBinding
import com.blandinf.neighbors.models.Neighbor


class AddNeighborFragment : Fragment() {

    private lateinit var binding: AddNeighborBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNeighborButton.setOnClickListener {
            var neighbor = Neighbor(
                name = binding.nameTF.text.toString(),
                avatarUrl = binding.imageTF.text.toString(),
                phoneNumber = binding.phoneTF.text.toString(),
                webSite = binding.websiteTF.text.toString(),
                address = binding.adressTF.text.toString(),
                aboutMe = binding.aboutTF.text.toString(),
                favorite = false
            )
            NeighborRepository.getInstance().createNeighbor(neighbor)
            Toast.makeText(context,getString(R.string.neighbor_added), 1)
            activity?.onBackPressed()
        }
    }

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddNeighborBinding.inflate(inflater, container, false)

        return binding.root
    }
}