package com.jakedelivery.api.store.dto;

import com.jakedelivery.db._common.constant.StoreCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private StoreCategory category;

    @NotBlank
    private String thumbnailUrl;

    @NotNull
    private BigDecimal minimumAmount;

    @NotNull
    private BigDecimal minimumDeliveryAmount;

    @NotBlank
    private String phoneNumber;
}
