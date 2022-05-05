package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
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
  private StarShipService starShipService;

  @Autowired
  public StarShipsController(StarShipService starShipService) {
    this.starShipService = starShipService;
  }

  @GetMapping()
  public String findAll(Model model) {
    // we've used our service to `findAll` starships
    List<StarShip> starships = starShipService.findAll();
    // we hand those starships to our view with `model.addAttribute`
    model.addAttribute("starships", starships);
    // we render our index page as our view
    return "starships/index";
  }

  @GetMapping("/{id}")
  public String getStarship(@PathVariable int id, Model model) {
    // @PathVariable annotation gives us access to any dynamic parts of our path
    // in the @GetMapping, we put `id` in curly brackets
    // then, we can take it in as an argument with @PathVariable
    StarShip starship = starShipService.get(id);
//    System.out.println(starship.getName());
    model.addAttribute("starship", starship);
    return "starships/show";
  }

  @GetMapping("/new")
  public String getForm(@ModelAttribute StarShip starShip) {
    return "starships/new";
  }

  // below, @ModelAttribute automatically populates our StarShip object with the user's input
  @PostMapping()
  public String createStarShip(@ModelAttribute StarShip starShip) {
    // we need to add an id (the user doesn't provide one)
    // we get the existing list, figure out its size, and add 1 for our new id
    int id = starShipService.findAll().size() + 1;
    // we attach that id to our new StarShip object
    starShip.setId(id);
    // we add our new StarShip to our existing list in our service
    starShipService.addToList(starShip);
    // we redirect to the show page
    // the below redirect sends an ADDITIONAL HTTP request:
    // a GET request to "/starships/4", for example
    return "redirect:/starships/" + id;
  }
}
