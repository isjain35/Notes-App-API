package com.example.notes.services;

import com.example.notes.models.NoteData;
import com.example.notes.repository.NoteRepository;
import com.example.notes.repository.RedisRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    RedisRepository redisRepository;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS");

    @Override
    public void testFunction(){
        redisRepository.saveKey("name", "Ishan Jain");
        redisRepository.saveExpiringKey("tempName", "Tilak Jain", 60, TimeUnit.SECONDS);
    }

    @Override
    public List<NoteData> listNotes() {
        redisRepository.saveLog(dtf.format(LocalDateTime.now()), "List of all notes requested.");
        return noteRepository.findAll();
    }

    @Override
    public NoteData listNoteById(Long id) throws Exception {
        redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Note with id: "+ id +" requested.");
        Optional<NoteData> optionalNoteData = noteRepository.findById(id);
        if(optionalNoteData.isPresent()) {
            redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Note with id: "+ id +" served.");
            return optionalNoteData.get();
        }
        else {
            redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Note with id: "+ id +" not present.");
            throw new Exception("Not Found!!");
        }
    }

    @Override
    public NoteData addNote(NoteData noteData){
        redisRepository.saveLog(dtf.format(LocalDateTime.now()), "New note added.");
        return noteRepository.saveAndFlush(noteData);
    }

    @Override
    public NoteData updateNote(Long id, NoteData noteData) throws Exception{
        redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Update to note with id: "+ id +" requested.");
        try {
            NoteData existingNoteData = listNoteById(id);
            BeanUtils.copyProperties(noteData,existingNoteData,"id");
            redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Note with id: "+ id +" updated.");
            return noteRepository.saveAndFlush(existingNoteData);
        } catch(Exception e){
            redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Note with id: "+ id +" not updated.");
            throw new Exception("Not Found!!");
        }
    }

    @Override
    public void deleteNote(Long id) throws Exception{
        redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Deletion of note with id: "+ id +" requested.");
        try{
            NoteData noteData = listNoteById(id);
            redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Note with id: "+ id +" not deleted.");
            noteRepository.deleteById(id);
        }
        catch(Exception e) {
            redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Note with id: "+ id +" not deleted.");
            throw new Exception("Not Found!!");
        }
    }

    @Override
    public List<NoteData> listNotesByTitle(String title) throws Exception{
        redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Search by exact title requested.");
        List<NoteData>  notes = noteRepository.findAllByTitle(title);
        if (!notes.isEmpty()){
            return notes;
        }else {
            throw new Exception("Not Found!!");
        }
    }

    @Override
    public List<NoteData> listNotesBySearch(String search_query) throws Exception{
        redisRepository.saveLog(dtf.format(LocalDateTime.now()), "Search by phrase in title and body requested.");
        List<NoteData>  notes = noteRepository.SearchTable(search_query);
        if (!notes.isEmpty()){
            return notes;
        }else {
            throw new Exception("Not Found!!");
        }
    }

}
