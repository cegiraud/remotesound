package com.github.cegiraud.remotesound.restrepository;

import com.github.cegiraud.remotesound.entity.Sound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "sounds", path = "sounds")
public interface SoundRepository extends Repository<Sound, String>, SoundRepositoryCustom {

    Page<Sound> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);
}