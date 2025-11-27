package com.herf.controller;
import com.herf.entity.GlucoseLevel;
import com.herf.service.GlucoseLevelService;
import com.herf.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/glucose-level")
public class GlucoseLevelController {

    @Autowired
    private GlucoseLevelService glucoseLevelService;
    
    @Autowired
    private UserService userService;
    

    @PostMapping("/add")
    public ResponseEntity<GlucoseLevel> addGlucoseLevel(@RequestBody GlucoseLevel glucoseLevel, @RequestParam Long userId) {
      
    	Long fetchedUserId = userService.getUserId(userId);

        if (fetchedUserId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        glucoseLevel.setUserId(fetchedUserId);
        GlucoseLevel savedGlucoseLevel = glucoseLevelService.addGlucoseLevel(glucoseLevel);
        return ResponseEntity.ok(savedGlucoseLevel);
    }
    @GetMapping("/user/{userId}/date-range")
    public ResponseEntity<List<GlucoseLevel>> getGlucoseLevelsByUserIdAndDateRange(
            @PathVariable Long userId,
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        List<GlucoseLevel> glucoseLevelList = glucoseLevelService.getGlucoseLevelsByUserIdAndDateRange(userId, startDate, endDate);
        return ResponseEntity.ok(glucoseLevelList);
    }
    @GetMapping("/user/{userId}/getmysaveddata")
    public ResponseEntity<List<GlucoseLevel>> getfullinfo(
            @PathVariable Long userId){
    	try {
        List<GlucoseLevel> bloodPressureList =glucoseLevelService.getallGlucoseLevelsByUserId(userId);
        return ResponseEntity.ok(bloodPressureList);}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    	}
    }

    @PutMapping("/{glucoseLevelId}")
    public ResponseEntity<GlucoseLevel> updateGlucoseLevel(
            @PathVariable Long glucoseLevelId,
            @RequestBody GlucoseLevel updatedGlucoseLevel) {
        GlucoseLevel glucoseLevel = glucoseLevelService.updateGlucoseLevel(glucoseLevelId, updatedGlucoseLevel);
        return ResponseEntity.ok(glucoseLevel);
    }

    @DeleteMapping("/{glucoseLevelId}")
    public ResponseEntity<Void> deleteGlucoseLevel(@PathVariable Long glucoseLevelId) {
        glucoseLevelService.deleteGlucoseLevel(glucoseLevelId);
        return ResponseEntity.noContent().build();
    }
}
