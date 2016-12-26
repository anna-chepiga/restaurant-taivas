package ua.goit.services;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.dao.PositionDao;
import ua.goit.domain.Position;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PositionService {
    private PositionDao positionDao;

    @Transactional
    public Set<Position> getAllPositions() {
         return positionDao.getAll();
    }

    @Transactional
    public List<String> getPositionsNames() {
        return positionDao.getAll().
                stream()
                .map(Position::getName)
                .collect(Collectors.toList());
    }

    @Transactional
    public Position findByName(String name) {
        if (name == null || name.equals("")) throw new RuntimeException("Cannot use empty name");

        return positionDao.findByName(name);
    }

    public void setPositionDao(PositionDao positionDao) {
        this.positionDao = positionDao;
    }
}