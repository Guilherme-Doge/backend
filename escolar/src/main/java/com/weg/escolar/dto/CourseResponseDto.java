package com.weg.escolar.dto;

import java.util.List;

public record CourseResponseDto(
        String nome,
        String codigo,
        List<Long> professorIds
) {
}
