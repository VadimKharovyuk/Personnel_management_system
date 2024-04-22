package com.example.personnel_management_system.Service;

import com.example.personnel_management_system.Repository.PositionRepository;
import com.example.personnel_management_system.model.Position;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public List<Position> findAll() {
        return positionRepository.findAll();
    }

    public Position findById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    public Position save(Position position) {
        return positionRepository.save(position);
    }

    public void deleteById(Long id) {
        positionRepository.deleteById(id);
    }
}
