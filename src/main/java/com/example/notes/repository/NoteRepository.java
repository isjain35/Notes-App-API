package com.example.notes.repository;

import com.example.notes.models.NoteData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<NoteData, Long> {
}
