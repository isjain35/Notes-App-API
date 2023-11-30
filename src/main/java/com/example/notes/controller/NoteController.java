package com.example.notes.controller;

import com.example.notes.models.NoteData;
import com.example.notes.services.NoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
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
    public NoteData listNoteById(@PathVariable Long id){
        try{
            return noteService.listNoteById(id);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @RequestMapping(value = "add" ,method = RequestMethod.POST)
    public NoteData addNote(@RequestBody NoteData noteData){
        return noteService.addNote(noteData);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public HashMap<String, String> updateNote(@PathVariable Long id, @RequestBody NoteData noteData){
        try {
            NoteData newNodeData = noteService.updateNote(id, noteData);
            return new HashMap<>(){{
                put("id", newNodeData.getId().toString());
                put("title",newNodeData.getTitle());
                put("body", newNodeData.getBody());
                put("msg", "Note Updated Successfully.");

            }};
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public HashMap<String, String> deleteNote(@PathVariable Long id){
        try {
            noteService.deleteNote(id);
            return new HashMap<>(){{
                put("msg", "Note Deleted Successfully.");
            }};
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("title/{title}")
    public List<NoteData> listNotesByTitle(@PathVariable String title){
        try {
            return noteService.listNotesByTitle(title);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("search")
    public  List<NoteData> listNotesBySearch(@RequestParam("query") String search_query){
        try{
            return noteService.listNotesBySearch(search_query);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

}
