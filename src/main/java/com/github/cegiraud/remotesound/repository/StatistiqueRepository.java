package com.github.cegiraud.remotesound.repository;

import com.github.cegiraud.remotesound.entity.Statistiques;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Repository
public class StatistiqueRepository {

    private ConcurrentHashMap<String, Map<String, LongAdder>> statistiques = new ConcurrentHashMap<>();

    public void increment(String host, String uri) {
        statistiques.computeIfAbsent(host, x -> new HashMap<>())
                .computeIfAbsent(uri, x -> new LongAdder()).increment();
    }

    public List<Statistiques> findall() {
        return statistiques.entrySet().stream()
                .map(e -> new Statistiques(
                        e.getKey(),
                        e.getValue().entrySet().stream()
                                .map(entry -> new Statistiques.Entry(entry.getKey(), entry.getValue().longValue()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}