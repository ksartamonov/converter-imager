package com.kartamonov.data.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IdDto {
    private UUID id;
}
