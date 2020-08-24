package io.angelappdev.tvcharacters

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details_character.*

class DetailsCharacterActivity : AppCompatActivity() {

    private val NAME_CHARACTER = "nameCharacter"
    private val SHOW_CHARACTER = "showCharacter"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_character)
        val nameCharacter = intent.getCharSequenceExtra(NAME_CHARACTER)
        val showCharacter = intent.getCharSequenceExtra(SHOW_CHARACTER)
        nameCharacterTextView.text = "$nameCharacter"
        showCharacterTextView.text = "$showCharacter"
    }
}