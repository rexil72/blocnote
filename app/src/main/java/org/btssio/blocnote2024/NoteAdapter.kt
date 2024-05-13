package org.btssio.blocnote2024

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : ListAdapter<Note, NoteAdapter.ClientViewHolder>(ClientDiffCallback()) {
    companion object {
        var pos: Int = 0;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vue_note, parent, false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = getItem(position)
        holder.bind(client)
    }

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tv_titre: TextView = itemView.findViewById(R.id.titreNote)
        private val tv_desc: TextView = itemView.findViewById(R.id.descriptionNote)
        fun bind(note: Note) {
            tv_titre.text = note.titre
            tv_desc.text = note.description
        }
    }
}

class ClientDiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}