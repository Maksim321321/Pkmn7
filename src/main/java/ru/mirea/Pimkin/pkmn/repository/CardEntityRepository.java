package ru.mirea.Pimkin.pkmn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.Pimkin.pkmn.entity.CardEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CardEntityRepository extends JpaRepository<CardEntity, UUID> {
    <S extends CardEntity> S save(S entity);

    Optional<CardEntity> findById(UUID  id); // Поиск по ID

    List<CardEntity> findAll(); // Найти все строки

    List<CardEntity> findByName(String name);

    List<CardEntity> findByPokemonOwner_id(UUID studentId);

    boolean existsByName(String name);
}
