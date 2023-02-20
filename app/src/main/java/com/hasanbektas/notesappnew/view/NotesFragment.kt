package com.hasanbektas.notesappnew.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hasanbektas.notesappnew.R
import com.hasanbektas.notesappnew.adapter.NotesAdapter
import com.hasanbektas.notesappnew.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_notes.*

class NotesFragment : Fragment() {

    private lateinit var viewModel : NotesViewModel

    private var noteAdapter = NotesAdapter(arrayListOf())

    private val swipeAction = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            return true
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedNote = noteAdapter.notesList[layoutPosition]
            viewModel.deleteNoteData(selectedNote)
            noteAdapter.updateNoteList()
            Toast.makeText(requireContext(),"'${selectedNote.title}' Silindi", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)

        noteObservers()

        recyclerViewList.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewList.adapter = noteAdapter

        ItemTouchHelper(swipeAction).attachToRecyclerView(recyclerViewList)

        addButton.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun noteObservers() {
        viewModel.noteMainList.observe(viewLifecycleOwner, Observer { notes ->

            notes?.let {
                noteAdapter.notesList = it.reversed()
                noteAdapter.updateNoteList()
            }
        })
    }
}