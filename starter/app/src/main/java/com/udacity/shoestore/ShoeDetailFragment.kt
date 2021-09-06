package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.models.Shoes


/**
 * A simple [Fragment] subclass.
 */
class ShoeDetailFragment : Fragment() {
    private var _binding: FragmentShoeDetailBinding? = null
    private val binding: FragmentShoeDetailBinding
        get() = _binding!!

//    private lateinit var shoeViewModel: ShoeViewModel
//    private lateinit var shoeViewModelFactory: ShoeViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val args by navArgs<ShoeDetailFragmentArgs>()
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shoe_detail, container, false)
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(args.shoes))
        }

        binding.saveButton.setOnClickListener {
            val shoeNameText = binding.shoeNameEditText.text.toString()
            val shoeCompanyText = binding.shoeCompanyEditText.text.toString()
            val shoeSizeText = binding.shoeSizeEditText.text.toString()
            val shoeDescriptionText = binding.shoeDescriptionEditText.text.toString()
            val shoe = Shoe(shoeNameText, shoeSizeText.toDouble(), shoeCompanyText, shoeDescriptionText)
            var shoes = args.shoes
            if(shoes == null) {
                shoes = Shoes()
            }
            shoes.add(shoe)
            val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(shoes)
            findNavController().navigate(action)
        }
        return binding.root
    }
}