package com.github.cegiraud.remotesound.restrepository;

import com.github.cegiraud.remotesound.entity.Sound;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

public class SoundRepositoryImpl implements SoundRepositoryCustom {

    private final RestTemplate restTemplate;

    private final EntityManager entityManager;

    @Autowired
    public SoundRepositoryImpl(RestTemplate restTemplate, EntityManager entityManager) {
        this.restTemplate = restTemplate;
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public Sound save(Sound sound) {
        ResponseEntity<byte[]> reponse = restTemplate.getForEntity(sound.getUrl(), byte[].class);
        if (!HttpStatus.OK.equals(reponse.getStatusCode())) {
            throw new IllegalArgumentException("le fichier n'existe pas");
        }
        return entityManager.merge(sound);
    }
}