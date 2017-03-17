package com.github.cegiraud.remotesound.service;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PlayerService {

    private final StatistiqueService statistiqueService;

    private final SimpMessagingTemplate template;

    private static AtomicLong id = new AtomicLong();

    public PlayerService(StatistiqueService statistiqueService, SimpMessagingTemplate template) {
        this.statistiqueService = statistiqueService;
        this.template = template;
    }

    @Async
    public void play(URI uri, String hostname) throws Exception {
        URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/stream")
                .addParameter("uri", uri.toString());

        Map<String, Object> payload = new HashMap<>();
        payload.put("hostname", hostname);
        payload.put("uri", uri.toString());
        payload.put("id", id.getAndIncrement());

        final Media media = new Media(uriBuilder.build().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnPlaying(() -> template.convertAndSend("/topic/playing/start", payload));
        mediaPlayer.setOnEndOfMedia(() -> {
            template.convertAndSend("/topic/playing/stop", payload);
            statistiqueService.increment(hostname, uri.toString());
        });
        mediaPlayer.play();
    }

}