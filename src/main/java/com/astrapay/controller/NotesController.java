package com.astrapay.controller;

import com.astrapay.dto.req.ReqAddNoteDto;
import com.astrapay.dto.req.ReqUpdateNoteDto;
import com.astrapay.dto.res.BaseResponse;
import com.astrapay.dto.res.ResNoteDto;
import com.astrapay.dto.res.ResNotesListDto;
import com.astrapay.service.NotesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @GetMapping
    @ApiOperation(value = "Get all notes")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = BaseResponse.class)
    })
    public ResponseEntity<BaseResponse<ResNotesListDto>> getAllNotes() {
        List<ResNoteDto> notes = notesService.getAllNotes().getNotes();

        BaseResponse<ResNotesListDto> response = new BaseResponse<>();
        response.setMessage(notes.isEmpty() ? "Tidak ada notes!" : "Berhasil mendapatkan semua notes");
        ResNotesListDto notesListDto = new ResNotesListDto();
        notesListDto.setNotes(notes);
        response.setData(notesListDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get note by id")
    public ResponseEntity<BaseResponse<ResNoteDto>> getNote(@PathVariable UUID id) {
        ResNoteDto note = notesService.getNote(id);
        BaseResponse<ResNoteDto> response = new BaseResponse<>();
        response.setMessage("Berhasil mendapatkan note");
        response.setData(note);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiOperation(value = "Add new note")
    public ResponseEntity<BaseResponse<ResNoteDto>> addNote(@Valid @RequestBody ReqAddNoteDto note) {
        ResNoteDto created = notesService.addNote(note);
        BaseResponse<ResNoteDto> response = new BaseResponse<>();
        response.setMessage("Note berhasil ditambahkan");
        response.setData(created);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping
    @ApiOperation(value = "Update note")
    public ResponseEntity<BaseResponse<ResNoteDto>> updateNote(@Valid @RequestBody ReqUpdateNoteDto note) {
        ResNoteDto updated = notesService.updateNote(note);
        BaseResponse<ResNoteDto> response = new BaseResponse<>();
        response.setMessage("Note berhasil diperbarui");
        response.setData(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete note")
    public ResponseEntity<BaseResponse<Void>> deleteNote(@PathVariable UUID id) {
        notesService.deleteNote(id);
        BaseResponse<Void> response = new BaseResponse<>();
        response.setMessage("Note berhasil dihapus");
        response.setData(null);
        return ResponseEntity.ok(response);
    }
}
