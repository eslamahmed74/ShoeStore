package com.udacity.shoestore

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentShoDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoDetailFragment : TextWatcher, Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentShoDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private val shoesViewModel: ShoesViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sho_detail, container, false)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this
        binding.btnSave.setOnClickListener { view ->
            val newItem = detailViewModel.crateNewItem()
            shoesViewModel.addNewItem(newItem)
            view.findNavController().navigate(R.id.action_shoDetailFragment_to_shoListFragment)
        }
        binding.edshocomp.addTextChangedListener(this)
        binding.edshoSize.addTextChangedListener(this)
        binding.edshodesc.addTextChangedListener(this)
        binding.edShoName.addTextChangedListener(this)
        binding.btnCancle.setOnClickListener { view ->
            Navigation.findNavController(view)
                .navigate(R.id.action_shoDetailFragment_to_shoListFragment)
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        binding.btnSave.isEnabled =
            binding.edShoName.text.isNotEmpty() && binding.edshoSize.text.isNotEmpty() && binding.edshocomp.text.isNotEmpty()
                    && binding.edshodesc.text.isNotEmpty()
    }

    override fun afterTextChanged(p0: Editable?) {
    }
}