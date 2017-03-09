package com.github.cegiraud.remotesound.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Repository
public class StatistiqueRepository {

    ConcurrentHashMap<String, Map<String, LongAdder>> statistiques = new ConcurrentHashMap<>();

    public void increment(String host, String uri) {
        statistiques.computeIfAbsent(host, x -> new HashMap<>())
                .computeIfAbsent(uri, x -> new LongAdder()).increment();
    }

    public Map<String, Map<String, Long>> findall() {
        return statistiques.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, sum -> sum.getValue().longValue())))
        );
    }
}