package edu.ntnu.idatt2106.backend.service;

import edu.ntnu.idatt2106.backend.model.waste.Waste;
import edu.ntnu.idatt2106.backend.model.user.User;
import edu.ntnu.idatt2106.backend.model.waste.WasteRequest;
import edu.ntnu.idatt2106.backend.repository.WasteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class WasteService {

    private final WasteRepository wasteRepository;

    @Autowired
    public WasteService(WasteRepository wasteRepository) {
        this.wasteRepository = wasteRepository;
    }

    public ResponseEntity<String> addWasteEntry(User user, WasteRequest wasteRequest) {
        Waste wasteEntry = new Waste(user, wasteRequest.getWeight(), LocalDate.parse(wasteRequest.getEntryDate()));
        wasteRepository.save(wasteEntry);
        return ResponseEntity.status(HttpStatus.OK).body("Waste was added successfully");
    }

    public Double getTotalWasteByUser(User user) {
        return wasteRepository.getTotalWasteByUser(user);
    }

    public ResponseEntity<Double> getTotalWasteByUserBetweenDates(User user, LocalDate startDate, LocalDate endDate) {
        Double totalWaste = wasteRepository.getTotalWasteByUserBetweenDates(user, startDate, endDate);
        if (totalWaste == null || totalWaste == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(0.0);
        }
        return ResponseEntity.status(HttpStatus.OK).body(totalWaste);
    }
}
