package com.example.notes.services;

import com.example.notes.models.NoteData;
import com.example.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    NoteRepository noteRepository;

    @Override
    public List<NoteData> listNotes() {
        return noteRepository.findAll();
    }

    @Override
    public NoteData listNoteById(Long id) throws Exception {
        Optional<NoteData> optionalNoteData = noteRepository.findById(id);
        if(optionalNoteData.isPresent()) {
            return optionalNoteData.get();
        }
        else {
            throw new Exception("Not Found!!");
        }
    }

    @Override
    public NoteData addNote(NoteData noteData){
        return noteRepository.saveAndFlush(noteData);
    }

    @Override
    public NoteData updateNote(NoteData noteData){
        return noteRepository.saveAndFlush(noteData);
    }

    @Override
    public void deleteNote(Long id) throws Exception{
        try{
            NoteData noteData = listNoteById(id);
            noteRepository.deleteById(id);
        }
        catch(Exception e) {
            throw new Exception("Not Found!!");
        }
    }

}
