package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

    PokemonTypeRepository pokemonTypeRepository;

    TranslationRepository translationRepository;

    @Autowired
    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository, TranslationRepository translationRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.translationRepository = translationRepository;
    }


    @Override
    public PokemonType getPokemonType(int id) {
        PokemonType pokemonType = pokemonTypeRepository.findPokemonTypeById(id);
        pokemonType.setName(translationRepository.getPokemonName(id,LocaleContextHolder.getLocale()));
        return pokemonType;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(){
        return pokemonTypeRepository.findAllPokemonType().stream().map(pokemonType -> {
            pokemonType.setName(translationRepository.getPokemonName(pokemonType.getId(), LocaleContextHolder.getLocale()));
            return pokemonType;
        }).sorted(Comparator.comparingInt(PokemonType::getId)).collect(Collectors.toList());
    }

    @Override
    public PokemonType getPokemonTypeByName(String name){
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }

    @Override
    public List<PokemonType> getPokemonTypeFromTypes(List<String> types) {
        return pokemonTypeRepository.findPokemonTypeByTypes(types);
    }
}
