package com.example.personnel_management_system.Repository;

import com.example.personnel_management_system.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository  extends JpaRepository<Booking,Long> {
}
