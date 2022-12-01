package com.kartamonov.data.factories;

import com.kartamonov.data.dto.AuthorDto;

public class AuthorDtoFactory {
    public static AuthorDto makeAuthorDto(String author) {
        return AuthorDto.builder().author(author).build();
    }
}
