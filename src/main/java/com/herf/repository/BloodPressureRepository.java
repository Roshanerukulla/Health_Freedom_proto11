package com.herf.repository;
import com.herf.entity.BloodPressure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {

    List<BloodPressure> findByUserIdAndDate(Long userId, Date date);

    List<BloodPressure> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate);

	void deleteByUserIdAndDate(Long userId, Date date);

	List<BloodPressure> findByUserId(Long userId);

    // Add other custom query methods as needed
}
