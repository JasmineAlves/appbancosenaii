package com.example.appbancosenai.controller;

import com.example.appbancosenai.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository < Person, Integer > {

}