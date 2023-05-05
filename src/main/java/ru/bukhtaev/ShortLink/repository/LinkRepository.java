package ru.bukhtaev.ShortLink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bukhtaev.ShortLink.model.Link;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Integer> {

    Optional<Link> findByShortLink(final String shortLink);
}
