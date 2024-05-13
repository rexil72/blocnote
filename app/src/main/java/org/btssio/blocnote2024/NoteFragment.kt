package org.btssio.blocnote2024

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

// Les constantes pour les clés de paramètres
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * Un simple [Fragment] qui permet de créer et d'envoyer des notes.
 * Utilisez la méthode factory [NoteFragment.newInstance] pour créer une instance de ce fragment.
 */
class NoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflater la vue du fragment
        val view = inflater.inflate(R.layout.fragment_note, container, false)

        // Récupérer la vue contenant la note
        val note: FrameLayout = view.findViewById(R.id.note_fragment)

        // Définir une action pour le bouton de retour
        val backButton = view.findViewById<ImageButton>(R.id.backButton)
        backButton.setOnClickListener {

            // Retour à l'écran précédent
            requireActivity().supportFragmentManager.popBackStack()

            // Rendre le header de MainActivity cliquable et visible
            MainActivity.header.isClickable = true
            MainActivity.header.setVisibility(View.VISIBLE)
        }

        // Définir une action pour le bouton d'envoi
        val sendButton : ImageButton = view.findViewById(R.id.sendButton)
        sendButton.setOnClickListener {

            // Récupérer le texte saisi pour le titre et la description
            val titreTexte = view.findViewById<TextInputEditText>(R.id.titreText)
            val titreText: String = titreTexte.text.toString()
            val descriptionTexte = view.findViewById<TextInputEditText>(R.id.descriptionText)
            val descriptionText: String = descriptionTexte.text.toString()

            // Vérifier si les champs titre et description ne sont pas vides
            if (titreText.isNotEmpty() && descriptionText.isNotEmpty())
            {

                // Afficher un message de confirmation avec une Snackbar
                val snb1: Snackbar =
                    Snackbar.make(note, "Note envoyé", Snackbar.LENGTH_LONG)
                snb1.show()
            } else {

                // Afficher un message d'erreur si des informations sont manquantes
                val snb1: Snackbar =
                    Snackbar.make(note, "Informations manquante", Snackbar.LENGTH_LONG)
                snb1.show()
            }
        }

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        /**
         * Utilisez cette méthode factory pour créer une nouvelle instance de
         * ce fragment en utilisant les paramètres fournis.
         *
         * @return Une nouvelle instance de fragment NoteFragment.
         */
        @JvmStatic
        fun newInstance() =
            NoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}