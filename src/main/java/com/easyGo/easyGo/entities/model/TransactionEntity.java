package com.easyGo.easyGo.entities.model;


import com.easyGo.easyGo.entities.enums.TransactStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_table")
public class TransactionEntity extends BaseEntity{

    @Column(nullable = false,length = 1000)
    private String receipt;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactStatus status;

    @Column(length = 20)
    private String referenceId;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE
            ,CascadeType.PERSIST,CascadeType.REFRESH})
//    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

}
