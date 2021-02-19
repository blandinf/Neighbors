package com.blandinf.neighbors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ListNeighborsFragment : Fragment() {

    ListNeighborsFragmentBinding binding;

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ListNeighborsFragment.inflate(inflater, container, false)
        return binding.root
    }
}