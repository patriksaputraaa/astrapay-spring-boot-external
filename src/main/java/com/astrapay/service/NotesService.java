package com.astrapay.service;

import com.astrapay.dto.req.ReqAddNoteDto;
import com.astrapay.dto.req.ReqUpdateNoteDto;
import com.astrapay.dto.res.ResNoteDto;
import com.astrapay.dto.res.ResNotesListDto;

import java.util.UUID;

public interface NotesService {
    ResNotesListDto getAllNotes();
    ResNoteDto getNote(UUID id);
    ResNoteDto addNote(ReqAddNoteDto note);
    ResNoteDto updateNote(ReqUpdateNoteDto note, UUID id);
    void deleteNote(UUID id);
}
