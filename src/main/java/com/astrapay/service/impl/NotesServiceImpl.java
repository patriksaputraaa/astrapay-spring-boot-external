package com.astrapay.service.impl;

import com.astrapay.dto.req.ReqAddNoteDto;
import com.astrapay.dto.req.ReqUpdateNoteDto;
import com.astrapay.dto.res.ResNoteDto;
import com.astrapay.dto.res.ResNotesListDto;
import com.astrapay.exception.CustomException;
import com.astrapay.service.NotesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NotesServiceImpl implements NotesService {

    private final List<ResNoteDto> notes = new ArrayList<>();

    @Override
    public ResNotesListDto getAllNotes() {
        ResNotesListDto notesList = new ResNotesListDto();
        notesList.setNotes(new ArrayList<>(notes));
        return notesList;
    }

    @Override
    public ResNoteDto getNote(UUID id) {
        return notes.stream()
                .filter(n -> n.getId().equals(id.toString()))
                .findFirst()
                .orElseThrow(() -> new CustomException("Note dengan id: " + id + " tidak ditemukan!"));
    }

    @Override
    public ResNoteDto addNote(ReqAddNoteDto note) {
        ResNoteDto newNote = new ResNoteDto();
        newNote.setId(UUID.randomUUID().toString());
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        notes.add(newNote);
        return newNote;
    }

    @Override
    public ResNoteDto updateNote(ReqUpdateNoteDto note, UUID id) {
        ResNoteDto existing = notes.stream()
                .filter(n -> n.getId().equals(id.toString()))
                .findFirst()
                .orElseThrow(() -> new CustomException("Note dengan id: " + id + " tidak ditemukan!"));

        existing.setTitle(note.getTitle());
        existing.setContent(note.getContent());
        return existing;
    }

    @Override
    public void deleteNote(UUID id) {
        boolean removed = notes.removeIf(n -> n.getId().equals(id.toString()));
        if (!removed) {
            throw new CustomException("Note dengan id: " + id + " tidak ditemukan!");
        }
    }
}