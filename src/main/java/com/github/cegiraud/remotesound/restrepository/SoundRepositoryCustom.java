package com.github.cegiraud.remotesound.restrepository;

import com.github.cegiraud.remotesound.entity.Sound;
import org.springframework.data.repository.query.Param;

public interface SoundRepositoryCustom {
    Sound save(@Param("sound") Sound sound);
}