package io.birthdaycalendar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.birthdaycalendar.models.BirthdayRecord;
import io.birthdaycalendar.repository.BirthdayRecordRepository;

@Service
public class BirthdayRecordService {
	
	private final BirthdayRecordRepository birthdayRecordRepository;
	
	@Autowired
	public BirthdayRecordService(BirthdayRecordRepository birthdayRecordRepository) {
		this.birthdayRecordRepository = birthdayRecordRepository;
	}

	public List<BirthdayRecord> getBirthdayRecords(String userName) {
		List<BirthdayRecord> records = new ArrayList<>();
		Iterable<BirthdayRecord> iterable = birthdayRecordRepository.findBirthdayRecordsByUserName(userName);
		for(BirthdayRecord record: iterable) {
			records.add(record);
		}
		return records;
	}

	public BirthdayRecord getBirthdayRecord(Long id) {
		return birthdayRecordRepository.findById(id).get();
	}
	
	
	public void addBirthdayRecord(BirthdayRecord birthdayRecord)
	{
		birthdayRecordRepository.save(birthdayRecord);
	}
	
	public void updateBirthdayRecord(BirthdayRecord birthdayRecord)
	{
		birthdayRecordRepository.save(birthdayRecord);
	}
	
	public void deleteBirthdayRecord(Long id)
	{
		birthdayRecordRepository.deleteById(id);
	}
}
