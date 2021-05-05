package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/starships")
public class StarshipsController {

    private StarShipService starShipService;

    @Autowired
    public StarshipsController(StarShipService starShipService) {
        this.starShipService = starShipService;
    }

    @GetMapping
    public String listStarships(Model model) {
        model.addAttribute("starships", starShipService.findAll());
        return "starships/index";
    }

    @GetMapping("/{id}")
    public String getStarship(@PathVariable Integer id, Model model) {
        if (id > starShipService.findAll().size() || id <= 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            //if you use findAll() you need to include - 1
            StarShip starship = starShipService.get(id);
            model.addAttribute("starship", starship);
            return "starships/show";
        }
    }

    @GetMapping("/new")
    public String addNewStarShip(@ModelAttribute StarShip starShip) {
        return "starships/new";
    }

    @PostMapping
    public String createNewStarShip(@ModelAttribute StarShip starShip) {
        int id = starShipService.findAll().size() + 1 ;
        starShip.setId(id);
        starShipService.addToList(starShip);
        return "redirect:/starships/" + starShip.getId();
    }
}
