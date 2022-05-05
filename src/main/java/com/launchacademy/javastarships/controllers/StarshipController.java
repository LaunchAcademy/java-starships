package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StarshipController {

  private StarShipService starShipService;

  @Autowired
  public StarshipController(StarShipService starShipService){
    this.starShipService = starShipService;
  }

  @GetMapping
  public String redirectToStarShips(Model model) {
    return "redirect:/starships";
  }

  @GetMapping("/starships")
  public String getStarship(Model model){

  List<StarShip> starShips =  starShipService.findAll();
    model.addAttribute("starships", starShips);
    return "starships/index";}

  @GetMapping("/starships/{index}")
  public String showStarship(@PathVariable int index, Model model) {
    model.addAttribute("ship",starShipService.get(index));
    return "starships/show";
  }
  @GetMapping("/starships/new")
  public String getForm(@ModelAttribute StarShip starShip){
    return "starships/new";
  }

  @PostMapping("/starships")
  public String createNewShip(@ModelAttribute StarShip starShip){
    int size = starShipService.findAll().size();
    starShip.setId(size + 1);
    starShipService.addToList(starShip);
    return "redirect:/starships/"+ starShip.getId();
  }
}
