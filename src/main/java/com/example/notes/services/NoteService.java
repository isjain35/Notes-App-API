package com.example.notes.services;

import com.example.notes.models.NoteData;

import java.util.List;

public interface NoteService {
    List<NoteData> listNotes();
    NoteData listNoteById(Long id) throws Exception;
    NoteData addNote(NoteData noteData);

    NoteData updateNote(NoteData noteData);

    void deleteNote(Long id) throws Exception;
}
