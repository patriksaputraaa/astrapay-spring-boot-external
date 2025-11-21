package com.astrapay;

import com.astrapay.dto.req.ReqAddNoteDto;
import com.astrapay.dto.req.ReqUpdateNoteDto;
import com.astrapay.dto.res.ResNoteDto;
import com.astrapay.exception.CustomException;
import com.astrapay.service.impl.NotesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class NotesServiceImplTest {
    private NotesServiceImpl notesService;

    private ResNoteDto initialNote;

    @BeforeEach
    void setUp() {
        notesService = new NotesServiceImpl();
        ReqAddNoteDto initialReq = new ReqAddNoteDto();
        initialReq.setTitle("Note Try");
        initialReq.setContent("Content");

        initialNote = notesService.addNote(initialReq);
    }

    @Test
    void addNote_success() {
        ReqAddNoteDto newReq = new ReqAddNoteDto();
        newReq.setTitle("Note Baru");
        newReq.setContent("Konten baru.");
        int initialSize = notesService.getAllNotes().getNotes().size();

        ResNoteDto result = notesService.addNote(newReq);

        assertEquals("Note Baru", result.getTitle());
        assertEquals(initialSize + 1, notesService.getAllNotes().getNotes().size(), "Ukuran list harus bertambah");
    }

    @Test
    void getAllNotes_success() {
        int size = notesService.getAllNotes().getNotes().size();
        assertEquals(1, size);
    }

    @Test
    void getNote_success() {
        ResNoteDto result = notesService.getNote(UUID.fromString(initialNote.getId()));

        assertEquals(initialNote.getTitle(), result.getTitle());
        assertEquals(initialNote.getContent(), result.getContent());
    }

    @Test
    void getNote_notFound() {
        UUID nonExistentId = UUID.randomUUID();
        CustomException thrown = assertThrows(CustomException.class, () -> {
            notesService.getNote(nonExistentId);
        });

        assertTrue(thrown.getMessage().contains("tidak ditemukan!"));
    }

    @Test
    void updateNote_success() {
        ReqUpdateNoteDto updateReq = new ReqUpdateNoteDto();
        updateReq.setId(initialNote.getId());
        updateReq.setTitle("Judul Baru");
        updateReq.setContent("Konten Diperbarui!");

        ResNoteDto updatedNote = notesService.updateNote(updateReq);

        assertEquals("Judul Baru", updatedNote.getTitle());
        assertEquals("Konten Diperbarui!", updatedNote.getContent());

        ResNoteDto verifiedNote = notesService.getNote(UUID.fromString(initialNote.getId()));
        assertEquals("Konten Diperbarui!", verifiedNote.getContent());
    }

    @Test
    void updateNote_notFound() {
        ReqUpdateNoteDto updateReq = new ReqUpdateNoteDto();
        updateReq.setId(UUID.randomUUID().toString());
        updateReq.setTitle("Test");
        updateReq.setContent("Test");

        assertThrows(CustomException.class, () -> {
            notesService.updateNote(updateReq);
        });
    }

    @Test
    void deleteNote_success() {
        UUID idToDelete = UUID.fromString(initialNote.getId());
        int initialSize = notesService.getAllNotes().getNotes().size();

        notesService.deleteNote(idToDelete);

        assertEquals(initialSize - 1, notesService.getAllNotes().getNotes().size());

        assertThrows(CustomException.class, () -> {
            notesService.getNote(idToDelete);
        });
    }

    @Test
    void deleteNote_notFound() {
        UUID nonExistentId = UUID.randomUUID();

        assertThrows(CustomException.class, () -> {
            notesService.deleteNote(nonExistentId);
        });
    }
}