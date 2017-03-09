package com.github.cegiraud.remotesound.service;

import com.github.cegiraud.remotesound.entity.Sound;
import com.github.cegiraud.remotesound.repository.StatistiqueRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by ceGiraud on 08/03/2017.
 */
@Service
public class StatistiqueService {

    private StatistiqueRepository statistiqueRepository;

    public StatistiqueService(StatistiqueRepository statistiqueRepository) {
        this.statistiqueRepository = statistiqueRepository;
    }

    public void increment(String host, String uri) {
        statistiqueRepository.increment(host, uri);
    }


    public Map<String , Map<String, Long>> findAll() {
        return statistiqueRepository.findall();
    }
}
