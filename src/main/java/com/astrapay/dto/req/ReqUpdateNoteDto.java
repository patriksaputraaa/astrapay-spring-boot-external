package com.astrapay.dto.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ReqUpdateNoteDto {
    @NotBlank(message = "Title tidak boleh kosong")
    private String title;

    @NotEmpty(message = "Content tidak boleh kosong")
    private String content;
}
