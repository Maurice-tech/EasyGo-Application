package com.easyGo.easyGo.entities.model;

import com.easyGo.easyGo.entities.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "order_table")
public class OrderEntity extends BaseEntity {

    @Column(name = "tracking_num", length = 22)
    private String trackingNum;

    private String deliveryCost;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private OrderDescriptionEntity orderDescriptionEntity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH,CascadeType.MERGE
    ,CascadeType.PERSIST,CascadeType.REFRESH})
    private UserEntity userEntity;

    @OneToMany(cascade = CascadeType.ALL)
    @Builder.Default
    private List<TrackingLocationEntity> trackingLocationEntities = new ArrayList<>();

    @OneToOne
    private TransactionEntity transactionEntity;

    @ManyToOne
    private DriverTaskEntity driverTaskEntity;
    public void addTrackingLocation(TrackingLocationEntity location){
        trackingLocationEntities.add(location);
        location.setOrderEntity(this);

    }

}
