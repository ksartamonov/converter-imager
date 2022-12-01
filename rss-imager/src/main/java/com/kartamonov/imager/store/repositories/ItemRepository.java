package com.kartamonov.imager.store.repositories;

import com.kartamonov.data.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, UUID> {
    Optional<List<ItemEntity>> findAllByAuthor(String author);
}
