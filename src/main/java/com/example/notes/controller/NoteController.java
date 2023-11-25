package com.example.notes.controller;

import com.example.notes.models.NoteData;
import com.example.notes.services.NoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping
    public List<NoteData> listNotes(){
        return noteService.listNotes();
    }

    @GetMapping("{id}")
    public ResponseEntity<NoteData> listNoteById(@PathVariable Long id){
        try{
            return new ResponseEntity<NoteData>(noteService.listNoteById(id), HttpStatus.OK);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Note not found!!");
        }
    }

    @PostMapping
    public NoteData addNote(@RequestBody NoteData noteData){
        return noteService.addNote(noteData);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public NoteData updateNote(@PathVariable Long id, @RequestBody NoteData noteData){
        try {
            NoteData existingNoteData = noteService.listNoteById(id);
            BeanUtils.copyProperties(noteData,existingNoteData,"id");
            return noteService.updateNote(existingNoteData);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Requested note not available.");
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteNote(@PathVariable Long id){
        try {
            noteService.deleteNote(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Requested note not available.");
        }
    }

}
