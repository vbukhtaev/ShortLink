package ru.bukhtaev.ShortLink.model.dto;

import lombok.Data;

/**
 * DTO для {@link ru.bukhtaev.ShortLink.model.Link}
 */
@Data
public class LinkDto {

    /**
     * Длинная ссылка.
     */
    private String originalLink;

    /**
     * Короткая ссылка.
     */
    private String shortLink;
}
