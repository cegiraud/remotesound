package com.github.cegiraud.remotesound.rest;

import com.github.cegiraud.remotesound.config.RemoteSoundApplicationProperties;
import com.github.cegiraud.remotesound.types.SoundActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

/**
 * Created by cegiraud on 27/02/2017.
 */
@Controller
@RequestMapping("/soundcontrol")
public class SoundController {

    private final RemoteSoundApplicationProperties applicationProperties;

    @Autowired
    public SoundController(RemoteSoundApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void play(@RequestParam SoundActionType action) throws IOException {
        String command = "powershell /Command \"" + applicationProperties.getSound().getControl().get(action) + "\"";
        Runtime.getRuntime().exec(command);
    }

}