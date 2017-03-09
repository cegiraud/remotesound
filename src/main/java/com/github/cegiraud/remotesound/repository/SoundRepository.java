package com.github.cegiraud.remotesound.repository;

import com.github.cegiraud.remotesound.entity.Sound;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundRepository extends PagingAndSortingRepository<Sound, Long> {

    Page<Sound> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}