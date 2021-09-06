package com.udacity.shoestore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodel.ShoeViewModel
import com.udacity.shoestore.viewmodel.ShoeViewModelFactory
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ShoeListFragment : Fragment() {

    private var _binding: FragmentShoeListBinding? = null
    private val binding: FragmentShoeListBinding
        get() = _binding!!
    private lateinit var shoeViewModel: ShoeViewModel
    private lateinit var shoeViewModelFactory: ShoeViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_shoe_list, container, false)
        setHasOptionsMenu(true)

        val args by navArgs<ShoeListFragmentArgs>()
        shoeViewModelFactory = ShoeViewModelFactory(shoes = args.shoes)
        shoeViewModel = ViewModelProvider(this, shoeViewModelFactory).get(ShoeViewModel::class.java)
        binding.shoeViewModel = shoeViewModel
        binding.lifecycleOwner = this
        Timber.d("Shoe List Size: ${shoeViewModel.shoeList.value}")
        shoeViewModel.shoeList.observe(viewLifecycleOwner,  { shoeList ->
            setEntries(binding.shoeItemLinearLayout as ViewGroup,
                shoeList, R.layout.shoe_view)
        })

        binding.seeShoeDetailButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment(shoeViewModel.shoeList.value))
        }

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    //Disable Back button
                    Timber.d("Back button press disabled")
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.logout -> {
                val intent = Intent(activity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        @BindingAdapter("android:entries", "android:layout")
        @JvmStatic
        fun setEntries(
            viewGroup: ViewGroup,
            entries: List<Shoe?>?, layoutId: Int
        ) {
            entries?.let {
                viewGroup.removeAllViews()
                val inflater = viewGroup.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                for (i in entries.indices) {
                    val entry = it[i]
                    if(entry!=null) {
                        Timber.d("Inflater: $inflater")
                        Timber.d("actual layoutId: $layoutId")
                        Timber.d("expected layoutId: ${R.layout.shoe_view}")
                        val binding = DataBindingUtil
                            .inflate<ViewDataBinding>(inflater, layoutId, viewGroup, true)
                        Timber.d("Binding: $binding")
                        Timber.d("Entry: $entry")
                        binding.setVariable(BR.shoe, entry)
                    }
                }
            }

        }
    }
}