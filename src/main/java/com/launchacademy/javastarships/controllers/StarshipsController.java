package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.*;
import com.launchacademy.javastarships.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/starships")
public class StarshipsController {
    //bring in session
    @Autowired
    StarShipService starShipService;

    @GetMapping
    public String getStarships(Model model) {
        model.addAttribute("starships", starShipService.findAll());
        return "/starships/index";
    }

    @PostMapping
    public String postNewStarShip(@ModelAttribute StarShip starShip) {
        starShip.setId(this.starShipService.findAll().size() + 1);
        starShipService.addToList(starShip);
        return "redirect:/starships/" + starShip.getId();
    }

    @GetMapping("/{id}")
    public String getStarShipShow(@PathVariable int id, Model model) {
        StarShip thisStarShip = starShipService.get(id);
        model.addAttribute("starship", starShipService.get(id));
        //addatt fuel capacity avg etc.
        System.out.println(thisStarShip);
        return "/starships/show";
    }

    @GetMapping("/new")
    public String getStarShipForm(@ModelAttribute("starship") StarShip starship) {
        return "/starships/form";
    }

}

