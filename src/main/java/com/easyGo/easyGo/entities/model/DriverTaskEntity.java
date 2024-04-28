package com.easyGo.easyGo.entities.model;

import com.easyGo.easyGo.entities.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
@Builder
public class DriverTaskEntity extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "driverTaskEntity",
    cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @Builder.Default
    private List<OrderEntity> orderEntity = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE
               ,CascadeType.PERSIST,CascadeType.REFRESH})
    private UserEntity driverEntity;

    public void addOrder(OrderEntity order){
        orderEntity.add(order);
        order.setDriverTaskEntity(this);
    }
}
