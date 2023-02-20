package com.hasanbektas.notesappnew.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hasanbektas.notesappnew.R
import com.hasanbektas.notesappnew.model.NoteModel
import com.hasanbektas.notesappnew.view.NotesFragmentDirections
import kotlinx.android.synthetic.main.notes_recycler_row.view.*

class NotesAdapter (var notesList: List<NoteModel>): RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(var view : View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.notes_recycler_row,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateNoteList(){
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.itemView.recyclerRowTitleText.text = notesList.get(position).title
        holder.itemView.recyclerRowNoteText.text = notesList.get(position).note
        holder.itemView.recyclerDateText.text = notesList.get(position).date

        holder.view.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToDetailFragment(notesList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }
}