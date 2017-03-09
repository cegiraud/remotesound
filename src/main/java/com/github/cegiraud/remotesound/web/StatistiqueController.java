package com.github.cegiraud.remotesound.web;

import com.github.cegiraud.remotesound.entity.Statistiques;
import com.github.cegiraud.remotesound.service.StatistiqueService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/statistiques")
public class StatistiqueController {

    private StatistiqueService statistiqueService;

    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping
    @ResponseBody
    public List<Statistiques> statistiques() {
        return statistiqueService.findAll();
    }


}