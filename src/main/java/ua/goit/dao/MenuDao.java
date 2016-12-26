package ua.goit.dao;

import ua.goit.domain.Menu;

import java.util.Set;

public interface MenuDao {
    Set<Menu> getAll();

    Menu findByName(String name);

    void add(Menu menu);

    void remove(Menu menu);

    void edit(Menu menu);
}