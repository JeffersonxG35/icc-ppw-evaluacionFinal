package ec.edu.ups.icc.labevaluation.supplies.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.services.SupplyService;
import jakarta.validation.Valid;
import ec.edu.ups.icc.labevaluation.supplies.dtos.UpdateSupplyQuantityDto;

@RestController
@RequestMapping("/supplies")
public class SupplyController {

    private final SupplyService service;

    public SupplyController(SupplyService service) {
        this.service = service;
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINATOR')")
    public SupplyResponseDto create(@Valid @RequestBody CreateSupplyDto dto) {
        return service.create(dto);
    }

    @GetMapping("/low-stock")
    @PreAuthorize("isAuthenticated()")
    public List<SupplyResponseDto> findLowStock(@RequestParam Integer maxQuantity) {
        return service.findLowStock(maxQuantity);
    }

    @PatchMapping("/{id}/quantity")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINATOR')")
    public SupplyResponseDto updateQuantity(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSupplyQuantityDto dto
    ) {
        return service.updateQuantity(id, dto);
    }
}