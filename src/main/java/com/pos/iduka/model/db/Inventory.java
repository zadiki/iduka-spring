package com.pos.iduka.model.db;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please provide a product")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "Please provide a shop")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Schema(description = "quantity of product in inventory", example = "10")
    private Integer quantity;
    @Schema(description = "unit cost of product in inventory", example = "5.00")
    private BigDecimal unitCost;

    @Schema(description = "selling price of product in inventory", example = "10.00")
    private BigDecimal sellingPrice;

    @Schema(description = "date and time when inventory item was created", example = "2022-01-01T12:00:00")
    private LocalDateTime creationDate;

    @Schema(description = "date and time when inventory item was last updated", example = "2022-01-01T12:00:00")
    private LocalDateTime updateDate;


    // getters an
}
