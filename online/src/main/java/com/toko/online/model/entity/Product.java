package com.toko.online.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int id;
    private String namaProduct;
    private String jenisProduct;
    private String kodeProduct;
    private int hargaProduct;

//    @JsonIgnore
//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
//    private Order order;

}
