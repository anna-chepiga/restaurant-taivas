package ua.goit.dao;

import ua.goit.domain.Position;

import java.util.Set;

public interface PositionDao {
    Set<Position> getAll();

    Position findByName(String name);
}