package com.orderService.OrderService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "orderType", discriminatorType = DiscriminatorType.STRING)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`order`")
public abstract class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    String orderId;

    @NotBlank(message = "productId.required")
    String productId;

    @NotBlank(message = "userId.required")
    String userId;

    @NotBlank(message = "quantity.required")
    int quantity;

    int points;

    @JsonIgnore
    @ManyToOne(targetEntity = Shipment.class)
    @JoinColumn(name = "shipId")
    @ToString.Exclude
    Shipment shipment;

    @CreationTimestamp
    LocalDateTime orderDate;

    LocalDateTime expectedDeliveryDate;

    public Order(String productId, String userId, int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }
}
