package ua.goit.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private Employee waiter;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "date")
    private Date orderDate;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "order_details",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "cooked_dish_id")
    )
    private List<CookedDish> dishes;

    public Orders(Employee waiter, Integer tableNumber, Date orderDate, List<CookedDish> dishes) {
        this.waiter = waiter;
        this.tableNumber = tableNumber;
        this.orderDate = orderDate;
        this.dishes = dishes;
    }

    public Orders(Date orderDate, List<CookedDish> dishes) {
        this.orderDate = orderDate;
        this.dishes = dishes;
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getWaiter() {
        return waiter;
    }

    public void setWaiter(Employee waiter) {
        this.waiter = waiter;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<CookedDish> getDishes() {
        return dishes;
    }

    public void setDishes(List<CookedDish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order{id=").append(id);
        sb.append(", waiter=").append(waiter.getFirstName()).append(" ").append(waiter.getLastName());
        sb.append(", tableNumber=").append(tableNumber);
        sb.append(", orderDate=").append(orderDate);
        sb.append("\n dishes={\n");
        dishes.forEach(dish -> sb.append("  id=").append(dish.getId()).append(", name=")
                .append(dish.getDishDescription().getDishName()).append("\n"));
        sb.append(" }\n").append("}\n");

        return sb.toString();
    }
}