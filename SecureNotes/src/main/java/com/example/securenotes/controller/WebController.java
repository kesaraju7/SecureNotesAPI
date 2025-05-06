package com.example.securenotes.controller;

import com.example.securenotes.dto.NoteDto;
import com.example.securenotes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final NoteService noteService;

    public WebController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/notes";
    }

    @GetMapping("/notes")
    public String notesPage(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("newNote", new NoteDto());
        return "notes";
    }
}
