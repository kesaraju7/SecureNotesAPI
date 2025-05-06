package com.example.securenotes.service;


import com.example.securenotes.dto.NoteDto;
import com.example.securenotes.model.Notes;
import com.example.securenotes.model.User;
import com.example.securenotes.repository.NoteRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserService userService;

    public NoteService(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    public List<NoteDto> getAllNotes() {
        User currentUser = getCurrentUser();
        return noteRepository.findAllByUser(currentUser).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public NoteDto getNoteById(Long id) {
        User currentUser = getCurrentUser();
        Notes note = noteRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new AccessDeniedException("Note not found or access denied"));
        return convertToDto(note);
    }

    public NoteDto createNote(NoteDto noteDto) {
        User currentUser = getCurrentUser();
        Notes note = new Notes();
        note.setContent(noteDto.getContent());
        note.setUser(currentUser);
        Notes savedNote = noteRepository.save(note);
        return convertToDto(savedNote);
    }

    public void deleteNote(Long id) {
        User currentUser = getCurrentUser();
        Notes note = noteRepository.findByIdAndUser(id, currentUser)
                .orElseThrow(() -> new AccessDeniedException("Note not found or access denied"));
        noteRepository.delete(note);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(authentication.getName());
    }

    private NoteDto convertToDto(Notes note) {
        NoteDto noteDto = new NoteDto();
        noteDto.setId(note.getId());
        noteDto.setContent(note.getContent());
        return noteDto;
    }
}