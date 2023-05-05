package ru.bukhtaev.ShortLink.service.impl;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bukhtaev.ShortLink.model.Link;
import ru.bukhtaev.ShortLink.repository.LinkRepository;
import ru.bukhtaev.ShortLink.service.LinkService;
import ru.bukhtaev.ShortLink.util.LinkNotFoundException;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class LinkServiceImpl implements LinkService {

    private static final String BASE_URL = "http://localhost:8080/";
    private final LinkRepository linkRepository;

    @Autowired
    public LinkServiceImpl(final LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    @Transactional
    public String createShortLink(final String originalLink) {

        final String shortLink = generateShortLink(originalLink);
        final Link newLink = new Link();
        newLink.setOriginalLink(originalLink);
        newLink.setShortLink(shortLink);

        linkRepository.save(newLink);

        return BASE_URL + shortLink;
    }

    @Override
    public String getOriginalLink(final String shortLink) {
        if (shortLink.isEmpty()) {
            return null;
        }

        return linkRepository.findByShortLink(shortLink)
                .orElseThrow(() -> new LinkNotFoundException("Короткая ссылка " + shortLink + "не найдена!"))
                .getOriginalLink();
    }

    /**
     * Генерирует коротку ссылку для переданной длинной ссылки.
     * @param originalLink длинная ссылка
     * @return короткая ссылка
     */
    private String generateShortLink(final String originalLink) {
        final LocalDateTime dateTime = LocalDateTime.now();
        return Hashing.murmur3_32_fixed()
                .hashString(
                        originalLink.concat(dateTime.toString()),
                        StandardCharsets.UTF_8
                ).toString();
    }
}
