package com.backend.spades.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "order_items")
public class OrderItem extends AbstractPersistable<Long> {

    @OneToOne
    private Product product;

    private Integer quantity;

    private Double price;

    @ManyToOne
    private Order order;

    public OrderItem(Product product, Integer quantity, Double price, Order order) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public OrderItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(product, orderItem.product) && Objects.equals(quantity,
                orderItem.quantity) && Objects.equals(price, orderItem.price) && Objects.equals(order,
                orderItem.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity, price, order);
    }
}
