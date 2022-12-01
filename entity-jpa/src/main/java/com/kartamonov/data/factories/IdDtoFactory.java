package com.kartamonov.data.factories;

import com.kartamonov.data.dto.IdDto;

import java.util.UUID;

public class IdDtoFactory {
    public static IdDto makeIdDto(UUID id) {
        return IdDto.builder().id(id).build();
    }
}
