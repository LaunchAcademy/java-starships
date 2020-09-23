package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
import com.launchacademy.javastarships.services.StarShipSessionBasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/starships")
public class StarShipsController {

  @Autowired
  private StarShipService service;

  @GetMapping
  public String listStarShips(Model model){
    model.addAttribute("starships", service.findAll());
    return "starships/index";
  }

  @GetMapping("/{starShipId}")
  public String getStarShip(@PathVariable Integer starShipId, Model model) {
    model.addAttribute("starShip", service.get(starShipId));
    return "starships/show";
  }

  @GetMapping("/new")
  public String createStarShip(@ModelAttribute StarShip starShip) {
    return "starships/new";
  }

  @PostMapping
  public String getStarShip(@ModelAttribute StarShip starShip) {
    starShip.setId(service.findAll().size() + 1);
    service.addToList(starShip);
    return "redirect:/starships/" + starShip.getId();
  }

}
