package ru.bukhtaev.ShortLink.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Модель ссылки.
 */
@Data
@Entity
@Table(name = "link")
public class Link {

    /**
     * Идентификатор.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Длинная ссылка.
     */
    @Column(name = "originalLink")
    @NotNull
    private String originalLink;

    /**
     * Короткая ссылка.
     */
    @Column(name = "shortLink")
    @NotNull
    private String shortLink;
}
