package com.theonewhocode.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity{

    private Long customerId;

    @Id
    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
