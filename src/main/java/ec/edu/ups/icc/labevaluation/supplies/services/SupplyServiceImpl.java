package ec.edu.ups.icc.labevaluation.supplies.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.entities.SupplyEntity;
import ec.edu.ups.icc.labevaluation.supplies.exceptions.SupplyConflictException;
import ec.edu.ups.icc.labevaluation.supplies.mappers.SupplyMapper;
import ec.edu.ups.icc.labevaluation.supplies.repositories.SupplyRepository;
import ec.edu.ups.icc.labevaluation.supplies.dtos.UpdateSupplyQuantityDto;
import ec.edu.ups.icc.labevaluation.supplies.exceptions.SupplyNotFoundException;

@Service
public class SupplyServiceImpl implements SupplyService {
    private final SupplyRepository repository;

    public SupplyServiceImpl(SupplyRepository repository) {
    this.repository = repository;
    }

    @Override
    @Transactional
    public SupplyResponseDto create(CreateSupplyDto dto) {
        if (repository.existsByNameIgnoreCaseAndDeletedFalse(dto.name())) {
            throw new SupplyConflictException("Supply already exists");
        }

        SupplyEntity entity = SupplyMapper.toEntity(dto);
        SupplyEntity saved = repository.save(entity);
        return SupplyMapper.toResponse(saved);
    }

   @Override
    @Transactional(readOnly = true)
    public List<SupplyResponseDto> findLowStock(Integer maxQuantity) {
        return repository
                .findByActiveTrueAndDeletedFalseAndQuantityLessThanOrderByQuantityAsc(maxQuantity)
                .stream()
                .map(SupplyMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public SupplyResponseDto updateQuantity(Long id, UpdateSupplyQuantityDto dto) {
        SupplyEntity entity = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new SupplyNotFoundException("Supply not found"));

        entity.setQuantity(dto.quantity());
        SupplyEntity updated = repository.save(entity);
        return SupplyMapper.toResponse(updated);
    }
}