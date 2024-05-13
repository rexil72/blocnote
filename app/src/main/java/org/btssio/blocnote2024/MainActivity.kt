package org.btssio.blocnote2024

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    // Définition des éléments de l'interface utilisateur comme des propriétés statiques pour pouvoir y accéder depuis toute la classe
    companion object {

        lateinit var buttonNote: ImageButton
        lateinit var header: LinearLayout

    }

    // Déclaration des variables utilisées dans la classe
    lateinit var notes: RecyclerView
    lateinit var noteViewModel: NoteViewModel
    lateinit var noteAdapter: NoteAdapter

    // Fonction appelée lorsque l'activité est créée
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // Définition du layout à afficher pour cette activité
        setContentView(R.layout.activity_main)

        // Récupération de la référence au layout principal
        val note: LinearLayout = findViewById(R.id.main)

        // Récupération des références aux éléments de l'interface utilisateur
        header = findViewById(R.id.header)
        buttonNote = findViewById(R.id.noteButton)

        // Action à effectuer lors du clic sur le bouton de création de note
        buttonNote.setOnClickListener {

            // Affichage d'un message à l'utilisateur via une Snackbar
            val snb1: Snackbar = Snackbar.make(note, "Veuillez remplir tous les champs", Snackbar.LENGTH_LONG)
            snb1.show()

            // Création et affichage d'un fragment de création de note
            val fragment: NoteFragment = NoteFragment.newInstance()
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()

            // Désactivation de la possibilité de cliquer sur l'entête et masquage de l'entête
            header.isClickable = false
            header.setVisibility(View.GONE)

        }

        // Configuration de la RecyclerView pour afficher les notes
        notes = findViewById(R.id.notes)
        notes.layoutManager = LinearLayoutManager(this)

        // Initialisation du ViewModel pour la gestion des données des notes
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        // Création de l'adaptateur pour la RecyclerView
        noteAdapter = NoteAdapter()

        // Liaison de l'adaptateur à la RecyclerView
        notes.adapter = noteAdapter

        // Observation des changements dans la liste des notes et mise à jour de l'interface utilisateur en conséquence
        noteViewModel.getNotes().observe(this as LifecycleOwner, Observer { notesAff ->

            noteAdapter.submitList(notesAff)

        })
    }
}