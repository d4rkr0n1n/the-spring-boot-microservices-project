package io.d4rkr0n1n.backend.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.d4rkr0n1n.backend.clients.DatabaseClient;
import io.d4rkr0n1n.backend.model.Note;
import io.d4rkr0n1n.backend.service.NotesResponseService;
import io.d4rkr0n1n.backend.utils.TimeUtils;

@RestController
@RequestMapping("/api/v1")
public class NotesRestController {

    private final NotesResponseService notesResponseService;
    private final DatabaseClient databaseClient;

    public NotesRestController(NotesResponseService notesResponseService, DatabaseClient databaseClient) {
        this.notesResponseService = notesResponseService;
        this.databaseClient = databaseClient;
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes() {
        return notesResponseService.retrieveAllNotes();
    }

    @PostMapping("/note/random")
    public ResponseEntity<Note> post() {
        return notesResponseService.createNotesRandom();
    }

    @PostMapping("/note")
    public ResponseEntity<Note> post(@RequestParam String contents) {
        return notesResponseService.createNotes(contents);
    }

    @GetMapping("/note")
    public ResponseEntity<Note> getNote(@RequestParam UUID id) {
        return notesResponseService.retrieveNote(id);
    }

    @PutMapping("/note")
    public ResponseEntity<Note> put(@RequestParam UUID id, @RequestParam String updatedContents) {
        return notesResponseService.updateNote(id, updatedContents);
    }

    @DeleteMapping("/note")
    public ResponseEntity<Note> delete(@RequestParam UUID id) {
        return notesResponseService.deleteNote(id);
    }

    @GetMapping("/retrieveAllNotes")
    public List<Note> retrieveAllNotes() {
        return databaseClient.retrieveAllNotes();
    }

    @GetMapping("/retrieveNote")
    public Optional<Note> retrieveNote(@RequestParam UUID id) {
        return databaseClient.retrieveNote(id);
    }

    @GetMapping("/countNotes")
    public Long countNotes() {
        return databaseClient.countNotes();
    }

    @PostMapping("/saveNote")
    public Note saveNote() {
        Note note = new Note(UUID.randomUUID(), "Note_" + TimeUtils.getCurrentTime(), "Random Note", TimeUtils.getCurrentTime());
        return databaseClient.saveNote(note);
    }

    @DeleteMapping("/deleteNote")
    public String deleteNote(@RequestBody Note note) {
        return databaseClient.deleteNote(note);
    }
}
