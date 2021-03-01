package com.blandinf.neighbors.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.blandinf.neighbors.R
import com.blandinf.neighbors.databinding.FragmentNeighborDetailsBinding
import com.blandinf.neighbors.models.Neighbor
import com.blandinf.neighbors.viewmodels.NeighborViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * A simple [Fragment] subclass.
 * Use the [NeighborDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NeighborDetailsFragment : Fragment() {

    private lateinit var binding: FragmentNeighborDetailsBinding
    private var neighbor: Neighbor? = null
    private lateinit var viewModel: NeighborViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNeighborDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        neighbor = arguments?.getParcelable("neighbor")
        bindNeighbor(neighbor)
        viewModel = ViewModelProvider(this).get(NeighborViewModel::class.java)
    }

    private fun bindNeighbor(neighbor: Neighbor?) {
        context?.let {
            Glide.with(it)
                .load(neighbor?.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .skipMemoryCache(false)
                .into(binding.neighborImg)
        }
        binding.neighborFullname.text = neighbor?.name
        binding.neighborAbout.text = neighbor?.aboutMe
        binding.neighborPhone.text = neighbor?.phoneNumber
        binding.neighborWebsite.text = neighbor?.webSite
        binding.neighborAddress.text = neighbor?.address

        displayCurrentState(neighbor!!.favorite)

        binding.addToFavorite.setOnClickListener {
            neighbor.favorite = !neighbor.favorite
            viewModel.updateNeighborFavoriteStatus(neighbor.favorite, neighbor.id)
            Toast.makeText(context, R.string.ajouter_aux_favoris, Toast.LENGTH_SHORT)
            displayCurrentState(neighbor.favorite)
        }
    }

    private fun displayCurrentState(favoriteStatus: Boolean) {
        if (favoriteStatus) {
            binding.addToFavorite.text = getString(R.string.retirer_des_favoris)
        } else {
            binding.addToFavorite.text = getString(R.string.ajouter_aux_favoris)
        }
    }
}
