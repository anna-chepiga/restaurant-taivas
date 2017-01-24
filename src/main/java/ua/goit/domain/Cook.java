package ua.goit.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cook extends Employee {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cooker_id")
    private List<CookedDish> cookedDishes;

    public Cook(String firstName, String lastName, Position position, Integer salary) {
        super(firstName, lastName, position, salary);
    }

    public Cook(List<CookedDish> cookedDishes) {
        this.cookedDishes = cookedDishes;
    }

    public Cook() {
    }

    public List<CookedDish> getCookedDishes() {
        return cookedDishes;
    }

    public void setCookedDishes(List<CookedDish> cookedDishes) {
        this.cookedDishes = cookedDishes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cook{id=").append(super.getId())
                .append(", name=").append(super.getFirstName())
                .append(" ").append(super.getLastName()).append(", cooked dishes:\n");

        cookedDishes.forEach(cookedDish -> sb.append("  ")
                .append(cookedDish.getDishDescription().getDishName())
                .append(", order id: ").append(cookedDish.getOrder().getId()).append("; "));

        sb.append("\n}\n");

        return sb.toString();
    }
}