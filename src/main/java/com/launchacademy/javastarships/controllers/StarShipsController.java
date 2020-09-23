package com.launchacademy.javastarships.controllers;

import com.launchacademy.javastarships.models.StarShip;
import com.launchacademy.javastarships.services.StarShipService;
import com.launchacademy.javastarships.services.StarShipSessionBasedService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/starships")
public class StarShipsController {

  @Autowired
  private StarShipService service;

  @GetMapping
  public String getIndex(Model model) {
    List<StarShip> starShips = service.findAll();
    model.addAttribute("starShips", starShips);
    return "starships/index";
  }
}
