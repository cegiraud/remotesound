package com.github.cegiraud.remotesound.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by cegiraud on 27/02/2017.
 */
@Controller
@RequestMapping("/stream")
public class StreamController {

    private final RestTemplate restTemplate;

    @Inject
    public StreamController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<byte[]> stream(@RequestParam URI uri) throws URISyntaxException {
        return restTemplate.getForEntity(uri, byte[].class);
    }


}