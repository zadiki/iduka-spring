package com.pos.iduka.model.db;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shop_id;

    @Schema(description = "shop full name", example = "wallmart")
    @NotBlank(message = "Please provide a shop name")
    private  String shopName;

}
