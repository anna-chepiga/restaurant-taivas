package ua.goit.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
@NamedQueries({
        @NamedQuery(name = "Menu.findAll", query = "select m from Menu m"),
        @NamedQuery(name = "Menu.findByName", query = "select m from Menu m where m.name like :name"),
        @NamedQuery(name = "Menu.deleteById", query = "delete from Menu m where m.id = :id")
})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_details",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "dish_id")
    )
    private List<Dish> dishes;

    public Menu(String name, List<Dish> dishes) {
        this.name = name;
        this.dishes = dishes;
    }

    public Menu() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        return getName() != null ? getName().equals(menu.getName()) : menu.getName() == null;

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}