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
        binding.shoe = Shoe()
        binding.saveButton.setOnClickListener {
            val shoe = binding.shoe
            var shoes = args.shoes
            if(shoes == null) {
                shoes = Shoes()
            }
            shoe?.let {
                shoes.add(shoe)
            }

            val action = ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(shoes)
            findNavController().navigate(action)
        }
        return binding.root
    }
}