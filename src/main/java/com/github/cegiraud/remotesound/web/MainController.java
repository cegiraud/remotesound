package com.github.cegiraud.remotesound.web;

import com.github.cegiraud.remotesound.config.RemoteSoundApplicationProperties;
import com.github.cegiraud.remotesound.entity.Sound;
import com.github.cegiraud.remotesound.service.StatistiqueService;
import com.github.cegiraud.remotesound.types.SoundActionType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by cegiraud on 27/02/2017.
 */
@Controller
public class MainController {

    private final RemoteSoundApplicationProperties applicationProperties;

    private final StatistiqueService statistiqueService;

    public MainController(RemoteSoundApplicationProperties applicationProperties, StatistiqueService statistiqueService) {
        this.applicationProperties = applicationProperties;
        this.statistiqueService = statistiqueService;
    }

    @GetMapping("/")
    public ModelAndView redirect() {
        return new ModelAndView("index.html");
    }


    @PostMapping(value = "/play")
    @ResponseStatus(HttpStatus.OK)
    public void play(@RequestParam MultipartFile file) throws IOException {
        Path tempFile = File.createTempFile("remotesound", ".tmp").toPath();
        Files.write(tempFile, file.getBytes());
        final Media media = new Media(tempFile.toUri().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

    @GetMapping(value = "/playurl")
    @ResponseStatus(HttpStatus.OK)
    public void play(@RequestParam URI uri, HttpServletRequest httpServletRequest) throws Exception {
        URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/stream")
                .addParameter("uri", uri.toString());
        final Media media = new Media(uriBuilder.build().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        statistiqueService.increment(httpServletRequest.getRemoteHost(), uri.toString());
    }

    @GetMapping("/soundcontrol")
    @ResponseStatus(HttpStatus.OK)
    public void play(@RequestParam SoundActionType action) throws IOException {
        String command = "powershell /Command \"" + applicationProperties.getSound().getControl().get(action) + "\"";
        Runtime.getRuntime().exec(command);
    }

}