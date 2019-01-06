package com.example.egemenorecipeapp.services;

import com.example.egemenorecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();

}
