package ec.edu.ups.icc.labevaluation.supplies.dtos;

import ec.edu.ups.icc.labevaluation.supplies.dtos.UpdateSupplyQuantityDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateSupplyQuantityDto(
        @NotNull
        @Min(0)
        Integer quantity
) {
}