package ru.bukhtaev.ShortLink.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.bukhtaev.ShortLink.model.dto.LinkDto;
import ru.bukhtaev.ShortLink.service.LinkService;

@RestController
public class LinkController {

    private final LinkService linkService;

    @Autowired
    public LinkController(final LinkService linkService) {
        this.linkService = linkService;
    }

    @PostMapping("/create")
    private ResponseEntity<String> createShortLink(@RequestBody @Valid final LinkDto dto) {

        final String shortLink = linkService.createShortLink(dto.getOriginalLink());

        return new ResponseEntity<>(
                shortLink,
                HttpStatus.OK
        );
    }

    @RequestMapping("/")
    public String welcome() {
        return "<body>\n" +
                "\n" +
                "<form>\n" +
                "    <input id=\"link\" type=\"text\">\n" +
                "    <input onclick=\"create()\" type=\"button\" value=\"Сделать\">\n" +
                "</form>\n" +
                "\n" +
                "<p id=\"shortLink\"></p>\n" +
                "\n" +
                "</body>\n" +
                "<script>\n" +
                "    async function create() {\n" +
                "        let link = document.querySelector('#link').value;\n" +
                "\n" +
                "        var myHeaders = new Headers();\n" +
                "        myHeaders.append(\"Content-Type\", \"application/json\");\n" +
                "\n" +
                "        var raw = JSON.stringify({\n" +
                "            \"originalLink\": link,\n" +
                "            \"shortLink\": null\n" +
                "        });\n" +
                "\n" +
                "        var requestOptions = {\n" +
                "            method: 'POST',\n" +
                "            headers: myHeaders,\n" +
                "            body: raw,\n" +
                "            redirect: 'follow'\n" +
                "        };\n" +
                "\n" +
                "        fetch(\"http://localhost:8080/create\", requestOptions)\n" +
                "            .then(response => response.text())\n" +
                "            .then(result => document.querySelector('#shortLink').innerHTML = result)\n" +
                "            .catch(error => console.log('error', error));\n" +
                "\n" +
                "\n" +
                "\n" +
                "        \n" +
                "    }\n" +
                "</script>\n";
    }

    @GetMapping("/{link}")
    @ResponseBody
    public RedirectView redirectWithUsingRedirectView(@PathVariable("link") final String shortLink) {
        final String originalLink = linkService.getOriginalLink(shortLink);
        return new RedirectView(originalLink);
    }
}
