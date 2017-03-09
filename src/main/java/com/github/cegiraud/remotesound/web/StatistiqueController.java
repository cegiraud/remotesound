package com.github.cegiraud.remotesound.web;

import com.github.cegiraud.remotesound.service.StatistiqueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by cegiraud on 27/02/2017.
 */
@Controller
@RequestMapping("/statistiques")
public class StatistiqueController {

    private StatistiqueService statistiqueService;

    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping
    @ResponseBody
    public Map<String, Map<String, Long>> statistiques() {
        return statistiqueService.findAll();
    }


}