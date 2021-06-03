package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipSessionBasedService;
import java.util.List;
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

  private StarShipSessionBasedService service;

  @Autowired
  public StarShipsController(StarShipSessionBasedService service) {
    this.service = service;
  }

//  private StarShipService service;
//  @Autowired
//  public StarShipsController(StarShipService service) {
//    this.service = service;
//  }

  @GetMapping
  public String getStarShipIndex(Model model){
    List<StarShip> ships = service.findAll();
    model.addAttribute("starships", ships);
    return "starships/index";
  }

  @GetMapping("/{id}")
  public String getShipShow(@PathVariable Integer id, Model model) {
    System.out.println(id);
    StarShip ship = service.get(id);
    model.addAttribute("starship", ship);
    return "starships/show";
  }

  @GetMapping("/new")
  public String getNewShipForm(@ModelAttribute StarShip starShip) {
    return "starships/new";
  }

  @PostMapping
  public String createNewShip(@ModelAttribute StarShip starShip) {
    System.out.println(starShip);
    // how do we figure out the next id?
    // we get the current number of starships
    Integer currentId = this.service.findAll().size();
    // and we add 1 for our next id
    starShip.setId(currentId + 1);

    // now that we have an id, we need to save our starship in our session
    service.addToList(starShip);
    // we do NOT want to do this: this just renders a template, it doesn't send a GET request
//    return "starships/show";
    // we DO want to send a REDIRECT request, which sends a whole separate GET request to our "/starships/{id}" @GetMapping up on line 40
    return "redirect:/starships/" + starShip.getId();
  }
}
