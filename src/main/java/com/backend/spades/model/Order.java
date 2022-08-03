package com.backend.spades.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "orders")
public class Order extends AbstractPersistable<Long> {

    @OneToOne(mappedBy = "order")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(CascadeType.DELETE)
    private List<OrderItem> orderItems;

    public Order(User user, List<OrderItem> orderItems) {
        this.user = user;
        this.orderItems = orderItems;
    }

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
