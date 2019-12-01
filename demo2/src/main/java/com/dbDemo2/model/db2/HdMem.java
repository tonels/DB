package com.dbDemo2.model.db2;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class HdMem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String memberId;
    private String cardNumber;
}
