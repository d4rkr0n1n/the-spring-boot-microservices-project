package io.d4rkr0n1n.sample.service;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.d4rkr0n1n.sample.helper.ResponseHelper;
import io.d4rkr0n1n.sample.model.Note;

@Service
public class NotesResponseService {

  private final NotesService notesService;

  public NotesResponseService(NotesService notesService) {
    this.notesService = notesService;
  }

  public ResponseEntity<List<Note>> retrieveAllNotes() {
    return ResponseHelper.ok(notesService.retrieveAllNotes());
  }

  public ResponseEntity<Note> createNotes() {
    return ResponseHelper.created(notesService.createNotes());
  }

  public ResponseEntity<Note> retrieveNote(UUID id) {
    try {
      return ResponseHelper.ok(notesService.retrieveNote(id));
    } catch (Exception e) {
      return ResponseHelper.notFound();
    }
  }

  public ResponseEntity<Note> updateNote(UUID id, String updatedContents) {
    try {
      return ResponseHelper.ok(notesService.updateNote(id, updatedContents));
    } catch (Exception e) {
      return ResponseHelper.notFound();
    }
  }

  public ResponseEntity<Note> deleteNote(UUID id) {
    try {
      return ResponseHelper.ok(notesService.deleteNote(id));
    } catch (Exception e) {
      return ResponseHelper.notFound();
    }
  }

}
