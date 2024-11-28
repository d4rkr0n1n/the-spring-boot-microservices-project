package io.d4rkr0n1n.sample.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.d4rkr0n1n.sample.service.NotesService;

@Controller
public class NotesViewController {

  private final NotesService notesService;

  public NotesViewController(NotesService notesService) {
    this.notesService = notesService;
  }

  @GetMapping("/")
  public String home(Model model) {
    if (notesService.checkDB() == 0) {
      notesService.createNotes();
      notesService.createNotes();
    }
    refreshNotesTable(model);
    model.addAttribute("message", notesService.checkDB());
    return "index";
  }

  @GetMapping("/refresh1")
  public String addNote(Model model, @RequestParam String contents) {
    notesService.createNotesC(contents);
    refreshNotesTable(model);
    return "fragments :: myDiv";
  }

  @DeleteMapping("/refreshdel")
  public String deleteNote(Model model, @RequestParam UUID id) {
    notesService.deleteNote(id);
    refreshNotesTable(model);
    return "fragments :: myDiv";
  }

  private void refreshNotesTable(Model model) {
    model.addAttribute("notes", notesService.retrieveAllNotes());
  }
}
