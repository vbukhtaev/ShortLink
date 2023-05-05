package ru.bukhtaev.ShortLink.util;

/**
 * Исключение для случая, когда для переданной короткой ссылки не нашлось длинной ссылки в БД.
 */
public class LinkNotFoundException extends RuntimeException {

    /**
     * Конструктор.
     * @param message сообщение
     */
    public LinkNotFoundException(final String message) {
        super(message);
    }
}
