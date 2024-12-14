package io.d4rkr0n1n.database.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.d4rkr0n1n.database.model.Note;
import io.d4rkr0n1n.database.service.NotesResponseService;

@RestController

@RequestMapping("/api/v1")
public class NotesRestController {

    private final NotesResponseService notesResponseService;

    public NotesRestController(NotesResponseService notesResponseService) {
        this.notesResponseService = notesResponseService;
    }

    @GetMapping("/retrieveAllNotes")
    public ResponseEntity<List<Note>> retrieveAllNotes() {
        return notesResponseService.retrieveAllNotes();
    }    
    
    @GetMapping("/retrieveNote")
    public ResponseEntity<Note> retrieveNote(@RequestParam UUID id) {
        return notesResponseService.retrieveNote(id);
    }    

    @PostMapping("/saveNote")
    public ResponseEntity<Note> saveNote(@RequestBody Note note) {
        return notesResponseService.saveNote(note);
    }

    @GetMapping("/countNotes")
    public ResponseEntity<Long> countNotes() {
        return notesResponseService.countNotes();
    }

    @DeleteMapping("/deleteNote")
    public ResponseEntity<String> deleteNote(@RequestBody Note note) {
        return notesResponseService.deleteNote(note);
    }

}
