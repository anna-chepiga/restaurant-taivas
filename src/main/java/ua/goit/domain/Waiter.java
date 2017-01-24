package ua.goit.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Waiter extends Employee {

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "waiter_id")
    private Set<Orders> orders;

    public Waiter(String firstName, String lastName, Position position, Integer salary) {
        super(firstName, lastName, position, salary);
    }

    public Waiter(Set<Orders> orders) {
        this.orders = orders;
    }

    public Waiter() {
    }

    public Set<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Waiter{id=").append(super.getId())
                .append(", name=").append(super.getFirstName())
                .append(" ").append(super.getLastName()).append(", orders:\n");

        orders.forEach(order -> {
            sb.append(" order id=").append(order.getId()).append(", dishes list: ");
            order.getDishes().forEach(dish -> sb.append(dish.getDishDescription().getDishName()).append(", "));
            sb.append("\n");
        });

        sb.append("}");

        return sb.toString();
    }
}