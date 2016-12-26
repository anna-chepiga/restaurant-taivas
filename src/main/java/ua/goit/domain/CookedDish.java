package ua.goit.domain;

import javax.persistence.*;

@Entity
@Table(name = "cooked_dish")
public class CookedDish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_id")
    private Dish dishDescription;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cooker_id")
    private Employee cook;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Orders order;

    public CookedDish(Dish dishDescription, Employee cook, Orders order) {
        this.dishDescription = dishDescription;
        this.cook = cook;
        this.order = order;
    }

    public CookedDish(Dish dishDescription) {
        this.dishDescription = dishDescription;
    }

    public CookedDish() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(Dish dishDescription) {
        this.dishDescription = dishDescription;
    }

    public Employee getCook() {
        return cook;
    }

    public void setCook(Cook cook) {
        this.cook = cook;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CookedDish{" +
                "id=" + id +
                ", dishDescription=" + dishDescription +
                ", cook=" + cook +
                ", order=" + order +
                '}';
    }
}