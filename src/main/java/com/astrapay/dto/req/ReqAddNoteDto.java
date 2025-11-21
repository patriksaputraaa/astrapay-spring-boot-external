package com.astrapay.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqAddNoteDto {
    @NotBlank(message = "Title tidak boleh kosong")
    private String title;

    @NotBlank(message = "Content tidak boleh kosong")
    private String content;
}
