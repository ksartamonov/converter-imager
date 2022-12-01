package com.kartamonov.data.dto;

import com.kartamonov.data.model.ItemEntity;
import lombok.*;

import java.util.List;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemsListDto {
    @NonNull
    private List<ItemEntity> items;
}
