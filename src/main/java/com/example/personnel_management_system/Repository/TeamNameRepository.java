package com.example.personnel_management_system.Repository;

import com.example.personnel_management_system.model.Employee;
import com.example.personnel_management_system.model.TeamName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamNameRepository extends JpaRepository<TeamName,Long> {

}
