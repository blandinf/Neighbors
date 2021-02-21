package com.blandinf.neighbors.fragments

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.blandinf.neighbors.R
import com.blandinf.neighbors.data.repositories.NeighborRepository
import com.blandinf.neighbors.databinding.AddNeighborBinding
import com.blandinf.neighbors.listeners.NavigationListener
import com.blandinf.neighbors.models.Neighbor


class AddNeighborFragment : Fragment() {

    private lateinit var binding: AddNeighborBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenTFChanges()

        binding.addNeighborButton.setOnClickListener {
            if (binding.addNeighborButton.isEnabled) {
                handleFormValidation()
                var neighbor = Neighbor(
                    name = binding.nameTF.text.toString(),
                    avatarUrl = binding.imageTF.text.toString(),
                    phoneNumber = binding.phoneTF.text.toString(),
                    webSite = binding.websiteTF.text.toString(),
                    address = binding.addressTF.text.toString(),
                    aboutMe = binding.aboutTF.text.toString(),
                    favorite = false
                )
                NeighborRepository.getInstance().createNeighbor(neighbor)
                Toast.makeText(context,getString(R.string.neighbor_added), Toast.LENGTH_SHORT)
                activity?.onBackPressed()
            } else {
                Toast.makeText(context, getString(R.string.fill_all_text_fields), Toast.LENGTH_SHORT)
            }
        }

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.add_neighbor)
        }
    }

    private fun listenTFChanges () {
        binding.nameTF.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                checkRequiredFields()
            }
        })

        binding.imageTF.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                checkRequiredFields()
            }
        })

        binding.phoneTF.addTextChangedListener(object : PhoneNumberFormattingTextWatcher() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkRequiredFields()
            }
        })

        binding.websiteTF.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                checkRequiredFields()
            }
        })

        binding.addressTF.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                checkRequiredFields()
            }
        })

        binding.aboutTF.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence, i: Int, i1: Int, i2: Int) {
                checkRequiredFields()
            }
        })
    }

    private fun handleFormValidation() {
        val formValidation = AwesomeValidation(ValidationStyle.BASIC)

        // fields required
        formValidation.addValidation(activity, binding.imageTF.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        formValidation.addValidation(activity, binding.nameTF.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        formValidation.addValidation(activity, binding.phoneTF.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        formValidation.addValidation(activity, binding.websiteTF.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        formValidation.addValidation(activity, binding.addressTF.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)
        formValidation.addValidation(activity, binding.aboutTF.id, RegexTemplate.NOT_EMPTY, R.string.validation_required)

        // specific cases
        formValidation.addValidation(activity, binding.websiteTF.id, android.util.Patterns.WEB_URL, R.string.validation_web_url)
        formValidation.addValidation(activity, binding.imageTF.id, android.util.Patterns.WEB_URL, R.string.validation_web_url)
        formValidation.addValidation(activity, binding.phoneTF.id, android.util.Patterns.PHONE, R.string.validation_phone)

        formValidation.validate()
    }

    private fun checkRequiredFields() {
        binding.addNeighborButton.isEnabled =
            binding.nameTF.text.toString().isNotEmpty() &&
            binding.imageTF.text.toString().isNotEmpty() &&
            binding.phoneTF.text.toString().isNotEmpty() &&
            binding.websiteTF.text.toString().isNotEmpty() &&
            binding.addressTF.text.toString().isNotEmpty() &&
            binding.aboutTF.text.toString().isNotEmpty()
    }

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddNeighborBinding.inflate(inflater, container, false)

        return binding.root
    }
}
