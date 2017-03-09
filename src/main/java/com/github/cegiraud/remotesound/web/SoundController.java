package com.github.cegiraud.remotesound.web;

import com.github.cegiraud.remotesound.entity.Sound;
import com.github.cegiraud.remotesound.service.SoundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sounds")
public class SoundController {


    private SoundService soundService;

    public SoundController(SoundService soundService) {
        this.soundService = soundService;
    }

    @GetMapping
    public Page<Sound> findByTitleContainingIgnoreCase(@RequestParam String title, Pageable pageable) {
        return soundService.findByTitleContainingIgnoreCase(title, pageable);
    }

    @PostMapping
    public Sound addSound(@RequestBody Sound sound) {
        return soundService.addSound(sound);
    }
}