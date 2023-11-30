package com.example.notes.repository;

import com.example.notes.models.NoteData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteData, Long> {
    List<NoteData> findAllByTitle(String title);

    @Query(value = "select * from notedata where title like (%?1%) or body like (%?1%)", nativeQuery = true)
    List<NoteData> SearchTable(String search_query);
}
