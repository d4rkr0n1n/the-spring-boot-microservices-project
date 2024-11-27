package io.d4rkr0n1n.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    model.addAttribute("notes", notesService.retrieveAllNotes());
    model.addAttribute("message", notesService.checkDB());
    return "index";
  }

  @GetMapping("/update-div-fragment")
  public String updateDiv(Model model) {
    model.addAttribute("notes", notesService.retrieveAllNotes());
    model.addAttribute("message", notesService.checkDB());
    model.addAttribute("updatedContent", "This is the updated content!");
    return "fragments :: myDiv";
  }
}
