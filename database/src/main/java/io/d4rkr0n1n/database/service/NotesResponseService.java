package io.d4rkr0n1n.database.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.d4rkr0n1n.database.helper.ResponseHelper;
import io.d4rkr0n1n.database.model.Note;

@Service
public class NotesResponseService {

  private final NotesService notesService;

  public NotesResponseService(NotesService notesService) {
    this.notesService = notesService;
  }

  public ResponseEntity<List<Note>> retrieveAllNotes() {
    return ResponseHelper.ok(notesService.retrieveAllNotes());
  }

  public ResponseEntity<Note> retrieveNote(UUID id) {
    try {
      return ResponseHelper.ok(notesService.retrieveNote(id));
    } catch (Exception e) {
      return ResponseHelper.notFound();
    }
  }

  public ResponseEntity<Note> saveNote(Note note) {
    return ResponseHelper.created(notesService.saveNote(note));
  }

  public ResponseEntity<Long> countNotes() {
    return ResponseHelper.created(notesService.getCount());
  }

  public ResponseEntity<String> deleteNote(Note note) {
    notesService.deleteNote(note);
    return ResponseHelper.ok("Note deleted");
  }

}
