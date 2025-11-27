package com.herf.controller;

import com.herf.entity.BloodPressure;
import com.herf.service.BloodPressureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/blood-pressure")
public class BloodPressureController {
	
    @Autowired
    private final BloodPressureService bloodPressureService;

    public BloodPressureController(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBloodPressure(@RequestBody BloodPressure bloodPressure, @RequestParam Long userId) {
        // Assume the userId is valid and set it directly
        bloodPressure.setUserId(userId);
        bloodPressureService.addBloodPressureRecord(bloodPressure);
        return ResponseEntity.status(HttpStatus.CREATED).body("Blood pressure record added successfully.");
    }

    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<BloodPressure>> getBloodPressureByUserIdAndDate(
            @PathVariable Long userId,
            @PathVariable Date date) {
        List<BloodPressure> bloodPressureList = bloodPressureService.getBloodPressureByUserIdAndDate(userId, date);
        return ResponseEntity.ok(bloodPressureList);
    }

    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<BloodPressure>> getBloodPressureByUserIdAndDateRange(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate) {
        List<BloodPressure> bloodPressureList = bloodPressureService.getBloodPressureByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(bloodPressureList);
    }

    @GetMapping("/user/{userId}/getmysaveddata")
    public ResponseEntity<List<BloodPressure>> getfullinfo(
            @PathVariable Long userId){
        try {
            List<BloodPressure> bloodPressureList = bloodPressureService.getallBloodPressureByUserId(userId);
            return ResponseEntity.ok(bloodPressureList);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<String> updateBloodPressureRecord(
            @PathVariable Long recordId,
            @RequestBody BloodPressure updatedBloodPressure) {
        bloodPressureService.updateBloodPressureRecord(recordId, updatedBloodPressure);
        return ResponseEntity.ok("Blood pressure record updated successfully");
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<String> deleteBloodPressureRecord(@PathVariable Long recordId) {
        bloodPressureService.deleteBloodPressureRecord(recordId);
        return ResponseEntity.ok("Blood pressure record deleted successfully");
    }

    @DeleteMapping("/user/{userId}/date/{date}")
    public ResponseEntity<String> deleteBloodPressureRecordsByUserIdAndDate(
            @PathVariable Long userId,
            @PathVariable Date date) {
        bloodPressureService.deleteBloodPressureRecordsByUserIdAndDate(userId, date);
        return ResponseEntity.ok("Blood pressure records for user " + userId + " and date " + date + " deleted successfully");
    }
}
