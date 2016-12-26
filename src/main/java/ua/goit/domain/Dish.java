package ua.goit.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String dishName;

    @ManyToOne
    @JoinColumn(name = "category")
    private DishCategory category;

    @Column(name = "price")
    private Double price;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "dish_details",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    public Dish(String dishName, DishCategory category, Double price, Integer weight, String photoUrl, List<Ingredient> ingredients) {
        this.dishName = dishName;
        this.category = category;
        this.price = price;
        this.weight = weight;
        this.photoUrl = photoUrl;
        this.ingredients = ingredients;
    }

    public Dish() {
    }

    public void setDetails(String dishName, DishCategory category, Integer weight, Double price) {
        this.dishName = dishName;
        this.category = category;
        this.weight = weight;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public DishCategory getCategory() {
        return category;
    }

    public void setCategory(DishCategory category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", weight=" + weight +
                ", photoUrl='" + photoUrl + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dish dish = (Dish) o;

        return getDishName().equals(dish.getDishName());

    }

    @Override
    public int hashCode() {
        return getDishName().hashCode();
    }
}