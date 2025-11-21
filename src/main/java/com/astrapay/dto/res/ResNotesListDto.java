package com.astrapay.dto.res;

import lombok.Data;
import java.util.List;

@Data
public class ResNotesListDto {
    private List<ResNoteDto> notes;
}
