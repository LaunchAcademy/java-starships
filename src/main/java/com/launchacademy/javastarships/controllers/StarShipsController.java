package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/starships")
public class StarShipsController {

    @Autowired
    private StarShipService service;

    @GetMapping
    public String listStarShips(Model model) {
        model.addAttribute("starships", service.findAll());
        return "starships/index";
    }

    @GetMapping("/{starshipId}")
    public String getStarShip(@PathVariable Integer starshipId, Model model) {
        model.addAttribute("starship", service.get(starshipId));
        return "starships/show";
    }

    @GetMapping("/new")
    public String starshipForm(@ModelAttribute StarShip starship) {

        return "starships/new";

    }
    @PostMapping
    public String createStarShip(@ModelAttribute StarShip starship){
        starship.setId(service.findAll().size() +1);
        service.addToList(starship);
        return "redirect:/starships/" + starship.getId();
    }


}
