package com.example.personnel_management_system.Service;

import com.example.personnel_management_system.Repository.BookingRepository;
import com.example.personnel_management_system.model.Booking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository ;


    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }


    public void deleteById(Long id) {
        bookingRepository.deleteById(id);
    }
}
