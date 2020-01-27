package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pokemon-types")
@RestController
public class PokemonTypeController {

    PokemonTypeService pokemonTypeService;

    @Autowired
    public PokemonTypeController(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @GetMapping(value = "/", params = "name")
    public PokemonType getPokemonTypeFromName(@RequestParam String name){
        return pokemonTypeService.getPokemonTypeByName(name);
    }

    @GetMapping(value = "/", params = "types")
    public List<PokemonType> getPokemonTypeFromTypes(@RequestParam List<String> types){
        return pokemonTypeService.getPokemonTypeFromTypes(types);
    }


    @GetMapping("/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
        return pokemonTypeService.getPokemonType(id);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return pokemonTypeService.getAllPokemonTypes();
    }


}
