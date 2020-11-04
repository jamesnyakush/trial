package com.example.ricky.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ricky.R
import com.example.ricky.ui.adapter.CharacterAdapter
import com.example.ricky.ui.viewmodel.CharacterViewModel
import com.example.ricky.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_detail_fragment.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterDetail : Fragment(R.layout.character_detail_fragment) {

    private val viewModel by viewModels<CharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCharacters()
        viewModel.getCharacter()
    }

    private fun observeCharacters() {
        viewModel.characterResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        Toast.makeText(requireContext(),it.value.results.toString(), Toast.LENGTH_SHORT).show()
                        viewModel.saveToDb(it.value.results)
                        recycler_character.apply {
                            layoutManager = LinearLayoutManager(requireContext())
                            hasFixedSize()
                            adapter = CharacterAdapter(it.value.results)
                        }

                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Failed Fetching", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}