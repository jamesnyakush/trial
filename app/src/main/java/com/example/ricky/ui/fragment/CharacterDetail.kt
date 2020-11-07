package com.example.ricky.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import com.example.ricky.R
import com.example.ricky.data.db.model.Character
import com.example.ricky.ui.adapter.CharacterAdapter
import com.example.ricky.ui.viewmodel.CharacterViewModel
import com.example.ricky.utils.CharacterItemClickListener
import com.example.ricky.utils.Resource
import com.example.ricky.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_detail_fragment.*
import kotlinx.android.synthetic.main.character_more_fragment.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CharacterDetail : Fragment(R.layout.character_detail_fragment), CharacterItemClickListener {

    private val viewModel by viewModels<CharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val sessionManager = SessionManager(requireContext())

        //check if its user's first time
        if (!sessionManager.isFirstTime()) {
            viewModel.getCharacter()
        } else {
            viewModel.getLocalCharacter()
        }

        observeCharacters()

    }

    private fun observeCharacters() {
        viewModel.characterResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        Toast.makeText(requireContext(),it.value.results.toString(), Toast.LENGTH_SHORT).show()
                        viewModel.saveToDb(it.value.results)
                        recycler_character.apply {
                            layoutManager = GridLayoutManager(requireContext(),2)
                            hasFixedSize()
                            adapter = CharacterAdapter(it.value.results,this@CharacterDetail)
                        }

                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Failed Fetching", Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    override fun onItemClick(character: Character) {
        bannerImgView.load(character.image)
        nameTxtView.text = character.name
        genderTxtView.text = character.gender
        typeTxtView.text = character.origin.name
        statusTxtView.text = character.status
    }
}