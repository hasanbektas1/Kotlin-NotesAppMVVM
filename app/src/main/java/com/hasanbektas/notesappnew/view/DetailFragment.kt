package com.hasanbektas.notesappnew.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.hasanbektas.notesappnew.R
import com.hasanbektas.notesappnew.viewmodel.NotesViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment() {

    private lateinit var viewModel: NotesViewModel

    private var notesId = 0

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
    val currentDate = sdf.format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            notesId = DetailFragmentArgs.fromBundle(it).noteid
        }

        viewModel = ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)
        viewModel.getDataFromRoom(notesId)

        detailObservers()

        saveButton.setOnClickListener {

            val title = detailTitleText.text.toString()
            val note = detailNoteText.text.toString()

            viewModel.makeNoteData(title, note, currentDate, it)

        }
    }

    private fun detailObservers() {

        viewModel.noteSelectedLiveData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { selectedNote ->

                selectedNote?.let {
                    detailTitleText.setText(selectedNote.title)
                    detailNoteText.setText(selectedNote.note)
                }
            })
    }
}