package com.github.cegiraud.remotesound.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by cegiraud on 06/03/2017.
 */
@Entity
public class Sound {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String url;

    @NotNull
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
