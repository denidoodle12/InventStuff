package com.ppm.inventstuff.ui.room

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ppm.inventstuff.ViewModelFactory
import com.ppm.inventstuff.adapter.RoomAdapter
import com.ppm.inventstuff.databinding.FragmentRoomBinding

class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoomViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        showData()
        setupAction()
    }

    private fun showData() {
        val adapter = RoomAdapter()
        binding.rvRuangan.layoutManager = LinearLayoutManager(context)
        binding.rvRuangan.adapter = adapter

        viewModel.getAllRoom().observe(viewLifecycleOwner) { results ->
            results.forEach(::println)
            Log.d("Room Fragment", "dbResponse: $results")
            adapter.submitList(results)
        }
    }

    private fun setupAction() {
        binding.fabAddRoom.setOnClickListener {
            val intent = Intent(requireContext(), InputRoomActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
