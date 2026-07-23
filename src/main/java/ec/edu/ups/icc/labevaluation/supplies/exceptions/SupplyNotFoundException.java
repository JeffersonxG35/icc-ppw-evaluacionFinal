package ec.edu.ups.icc.labevaluation.supplies.exceptions;

import ec.edu.ups.icc.labevaluation.core.exceptions.domain.NotFoundException;

public class SupplyNotFoundException extends NotFoundException {
    public SupplyNotFoundException(String message) {
        super("SUPPLY_NOT_FOUND", message);
    }
}