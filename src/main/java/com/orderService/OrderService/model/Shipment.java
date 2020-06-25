package com.orderService.OrderService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Shipment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    String shipmentId;

    @NotEmpty(message = "courierName.required")
    String courierName;

    @NotBlank(message = "packageWeight.required")
    String packageWeight;

    @NotBlank(message = "sourceAddress.required")
    String sourceAddress;

    @NotBlank(message = "destinationAddress.required")
    String destinationAddress;

    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL, mappedBy = "shipment")
    List<Order> orders = new ArrayList<>();

    LocalDateTime shipmentDate;
}
