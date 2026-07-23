package ec.edu.ups.icc.labevaluation.supplies.dtos;

import java.math.BigDecimal;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateSupplyDto(
    @NotBlank
    String name,
    String description,

    @NotNull
    @Min(0)
    Integer quantity,

    @NotNull
    @Min(0)
    Integer minimumStock,

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    BigDecimal unitPrice
) {
}