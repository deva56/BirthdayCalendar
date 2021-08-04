package io.birthdaycalendar.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.birthdaycalendar.models.BirthdayRecord;

public interface BirthdayRecordRepository extends CrudRepository<BirthdayRecord, Long> {

	 List<BirthdayRecord> findBirthdayRecordsByUserName(String username);
}
