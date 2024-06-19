package com.example.personnel_management_system.Service;

import com.example.personnel_management_system.Repository.EmployeeRepository;
import com.example.personnel_management_system.Repository.TeamNameRepository;
import com.example.personnel_management_system.model.Employee;
import com.example.personnel_management_system.model.TeamName;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamNameService {

    private final TeamNameRepository teamNameRepository;
    private final EmployeeRepository employeeRepository;

    public List<TeamName> findAll() {
        return teamNameRepository.findAll();
    }

    public Optional<TeamName> findById(Long id) {
        return teamNameRepository.findById(id);
    }

    public TeamName save(TeamName teamName) {
        return teamNameRepository.save(teamName);
    }

    public void deleteById(Long id) {
        teamNameRepository.deleteById(id);
    }
    public List<Employee> findEmployeesByTeamId(Long teamId) {
        return employeeRepository.findByTeamId(teamId);
    }

}
