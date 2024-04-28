package com.easyGo.easyGo.entities.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tracking_location_table")
public class TrackingLocationEntity extends BaseEntity{

    @Column(nullable = false)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,
            CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
}
