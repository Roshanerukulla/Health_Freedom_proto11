package com.herf.repository;

import com.herf.entity.GlucoseLevel;


import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface GlucoseLevelRepository extends CrudRepository<GlucoseLevel, Long> {

	List<GlucoseLevel> findByUserIdAndDate(Long userId, Date date);

    List<GlucoseLevel> findByUserIdAndDateBetween(Long userId, Date startDate, Date endDate);

	void deleteByUserIdAndDate(Long userId, Date date);

	List<GlucoseLevel> findByUserId(Long userId);

}
