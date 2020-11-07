package com.example.ricky.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.ricky.R
import com.example.ricky.data.db.model.Character
import com.example.ricky.utils.CharacterItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.character_more_fragment.*

@AndroidEntryPoint
class CharacterMore : Fragment(R.layout.character_more_fragment) ,CharacterItemClickListener{
    val characterDetailsSafeArgs : CharacterMore by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClick(character: Character) {
        bannerImgView.load(character.image)
        nameTxtView.text = character.name
        genderTxtView.text = character.gender
        typeTxtView.text = character.origin.name
        statusTxtView.text = character.status
    }
}