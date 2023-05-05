package ru.bukhtaev.ShortLink.service;

/**
 * Сервис для {@link ru.bukhtaev.ShortLink.model.Link}
 */
public interface LinkService {

    /**
     * Создает короткую ссылку для переданной длинной ссылки.
     * @param originalLink длинная ссылка
     * @return короткую ссылку
     */
    String createShortLink(final String originalLink);

    /**
     * Получает длинную ссылку по переданной короткой ссылке
     * @param shortLink короткая ссылка
     * @return длинная ссылка
     */
    String getOriginalLink(final String shortLink);
}
