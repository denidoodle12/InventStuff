package com.ppm.inventstuff.ui.recapitulation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ppm.inventstuff.R
import com.ppm.inventstuff.ViewModelFactory
import com.ppm.inventstuff.databinding.FragmentRecapitulationBinding
import com.ppm.inventstuff.databinding.FragmentRoomBinding
import com.ppm.inventstuff.ui.room.RoomViewModel
import java.text.NumberFormat
import java.util.Locale

class RecapitulationFragment : Fragment() {

    private var _binding: FragmentRecapitulationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecapitulationViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecapitulationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showData()
    }

    private fun showData() {
        viewModel.getTotalRecapitulation().observe(viewLifecycleOwner) { results->

            val formatPriceID = formatCurrency(results.totalAssets)

            binding.tvTotalInventoryItem.text = results.totalQuantity.toString()
            binding.tvTotalInventoryAsset.text = getString(R.string.total_format, formatPriceID)
        }
    }

    private fun formatCurrency(amount: Int): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return formatter.format(amount)
        // .replace("Rp", "Rp.")
    }
}