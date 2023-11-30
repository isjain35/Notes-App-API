package com.example.notes.services;

import com.example.notes.models.NoteData;

import java.util.List;

public interface NoteService {
    List<NoteData> listNotes();
    NoteData listNoteById(Long id) throws Exception;
    NoteData addNote(NoteData noteData);
    NoteData updateNote(Long id, NoteData noteData) throws Exception;
    void deleteNote(Long id) throws Exception;
    List<NoteData> listNotesByTitle(String title) throws Exception;
    List<NoteData> listNotesBySearch(String search_query) throws Exception;
}
