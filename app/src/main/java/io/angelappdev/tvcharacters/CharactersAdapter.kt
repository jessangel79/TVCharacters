package io.angelappdev.tvcharacters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.cell_character.view.*

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharacterViewholder>() {
    val _characterList: RealmResults<Character>
    init {
        val realm = Realm.getDefaultInstance()
//        _characterList = realm.where<Character>().sort("show").findAll()
        _characterList = realm.where(Character::class.java).sort("show").findAll()
        if (_characterList.size == 0) {
            val initialCharacterList = arrayOf(Character("Richard Hendricks", "Silicon Valley"),
                Character("Jared Dunn", "Silicon Valley"),
                Character("Will MacAvoy", "The Newsroom"),
                Character("Mackenzie MacHale", "The Newsroom"),
                Character("Jim Harper", "The Newsroom"),
                Character("Ross Geller", "Friends"),
                Character("Monica Geller", "Friends"),
                Character("Joey Tribbiani", "Friends"),
                Character("Chandler Bing", "Friends"),
                Character("Charlie Harper", "Two and a Half Men"),
                Character("Allan Harper", "Two and a Half Men"),
                Character("Jake Harper", "Two and a Half Men"))
            realm.beginTransaction()
            for (character in initialCharacterList) {
                realm.copyToRealm(character)
            }
            realm.commitTransaction()
        }
    }

//    private val _characterList = arrayOf(Character("Richard Hendricks", "Silicon Valley"),
//        Character("Jared Dunn", "Silicon Valley"),
//        Character("Will MacAvoy", "The Newsroom"),
//        Character("Mackenzie MacHale", "The Newsroom"),
//        Character("Jim Harper", "The Newsroom"),
//        Character("Ross Geller", "Friends"),
//        Character("Monica Geller", "Friends"),
//        Character("Joey Tribbiani", "Friends"),
//        Character("Chandler Bing", "Friends"),
//        Character("Charlie Harper", "Two and a Half Men"),
//        Character("Allan Harper", "Two and a Half Men"),
//        Character("Jake Harper", "Two and a Half Men"))

    private val NAME_CHARACTER = "nameCharacter"
    private val SHOW_CHARACTER = "showCharacter"

    fun onCharacterClick(index: Int, context: Context) {
        val character = _characterList[index]
        if (character != null) {
            Toast.makeText(context, character.name, Toast.LENGTH_SHORT).show()
            selectedCharacterCell(context, character)
        }
    }

    private fun selectedCharacterCell(context: Context, character: Character) {
        val intent = Intent(context, DetailsCharacterActivity::class.java)
        intent.putExtra(NAME_CHARACTER, character.name)
        intent.putExtra(SHOW_CHARACTER, character.show)
        context.startActivity(intent)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CharacterViewholder {
        // 1 - Charger la vue en XML
        val rootView = LayoutInflater.from(p0.context).inflate(R.layout.cell_character, p0, false)

        // 2 - Créer un viewholder pour contrôler cette vue
        val holder = CharacterViewholder(rootView)

        // 3 - Retourner le viewholder
        return holder
    }

    override fun getItemCount(): Int {
        return _characterList.size
    }

    override fun onBindViewHolder(p0: CharacterViewholder, p1: Int) {
        // 1 - Obtenir le personnage
        val character = _characterList[p1]

        // 2 - Envoyer les infos du personnage dans le holder
        if (character != null) {
            p0.fillWithCharacter(character)
        }
    }

    inner class CharacterViewholder(rootView: View) : RecyclerView.ViewHolder(rootView),
        View.OnClickListener {
        private val ui_title = rootView.ui_title
        private val ui_subtitle = rootView.ui_subtitle
        init {
            rootView.setOnClickListener(this)
        }

        fun fillWithCharacter(character: Character) {
            ui_title.text = character.name
            ui_subtitle.text = character.show
        }

        override fun onClick(v: View?) {
            if (v != null) {
                onCharacterClick(adapterPosition, v.context)
            }
        }
    }
}