package com.toko.online.model.dto;

import lombok.Data;

@Data
public class CustomerOrder {
  private String namaProduct;
  private String kodeProduct;
  private int jumlahProduct;
  private int totalHarga;
}
