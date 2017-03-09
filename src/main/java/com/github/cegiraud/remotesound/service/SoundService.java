package com.github.cegiraud.remotesound.service;

import com.github.cegiraud.remotesound.entity.Sound;
import com.github.cegiraud.remotesound.repository.SoundRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SoundService {

    private SoundRepository soundRepository;
    private RestTemplate restTemplate;

    public SoundService(SoundRepository soundRepository, RestTemplate restTemplate) {
        this.soundRepository = soundRepository;
        this.restTemplate = restTemplate;
    }

    public Page<Sound> findByTitleContainingIgnoreCase(String title, Pageable pageable) {
        return soundRepository.findByTitleContainingIgnoreCase(title, pageable);
    }


    public Sound addSound(Sound sound) {
        ResponseEntity<byte[]> reponse = restTemplate.getForEntity(sound.getUrl(), byte[].class);
        if (!HttpStatus.OK.equals(reponse.getStatusCode())) {
            throw new IllegalArgumentException("le fichier n'existe pas");
        }
        return soundRepository.save(sound);
    }
}