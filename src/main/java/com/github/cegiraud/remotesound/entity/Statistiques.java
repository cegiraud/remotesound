package com.github.cegiraud.remotesound.entity;


import java.util.List;

public class Statistiques {


    private String host;

    private List<Entry> entries;

    public Statistiques(String host, List<Entry> entries) {
        this.host = host;
        this.entries = entries;
    }

    public String getHost() {
        return host;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public static class Entry {


        private String url;

        private Long count;

        public Entry(String url, Long count) {
            this.url = url;
            this.count = count;
        }

        public String getUrl() {
            return url;
        }

        public Long getCount() {
            return count;
        }
    }
}
