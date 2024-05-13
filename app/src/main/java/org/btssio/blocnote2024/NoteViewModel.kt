package org.btssio.blocnote2024

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.btssio.siozon2024.ApiService

class NoteViewModel : ViewModel() {
        val _notes = MutableLiveData<List<Note>>()
        fun getNotes(): LiveData<List<Note>> {
            var lesNotes: LiveData<List<Note>> = _notes
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    Log.i("ERREUR", "Avant")
                    val laliste = ApiService.getInstance().getNotes()
                    Log.i("ERREUR", "Après")
                    _notes.postValue(laliste)
                } catch (e: Throwable) {
                    Log.i("ERREUR", "erreur avec bdd ???" + e.toString())
                }
            }
            return _notes
        }
    }