package com.github.cegiraud.remotesound.rest;

import com.github.cegiraud.remotesound.types.SoundActionType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.text.MessageFormat;

/**
 * Created by cegiraud on 27/02/2017.
 */
@Controller
@RequestMapping("/soundcontrol")
public class SoundController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void play(@RequestParam("action") SoundActionType soundActionType) throws IOException {
        String commandPattern = "powershell /Command " +
                "\"$wshShell = new-object -com wscript.shell;$wshShell.SendKeys([char]{0})\"";

        int codeAction;
        switch (soundActionType) {
            case UP:
                codeAction = 175;
                break;
            case DOWN:
                codeAction = 174;
                break;
            case TOGGLE_MUTE:
                codeAction = 173;
                break;
            default:
                return;
        }

        Runtime.getRuntime().exec(MessageFormat.format(commandPattern, codeAction));
    }

}