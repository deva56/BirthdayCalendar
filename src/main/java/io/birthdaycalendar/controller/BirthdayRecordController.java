package io.birthdaycalendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.birthdaycalendar.models.BirthdayRecord;
import io.birthdaycalendar.service.BirthdayRecordService;

@RestController
@RequestMapping("/api")
public class BirthdayRecordController {

	private final BirthdayRecordService birthdayRecordService;
	
	@Autowired
	public BirthdayRecordController(BirthdayRecordService birthdayRecordService) {
		this.birthdayRecordService = birthdayRecordService;
	}

	@GetMapping(value ="/records/{userName}")
	public List<BirthdayRecord> getAllBirthdayRecords(@PathVariable String userName) {
		return birthdayRecordService.getBirthdayRecords(userName);
	}
	
	@GetMapping("/record/{id}")
	public BirthdayRecord getBirthdayRecord(@PathVariable Long id)
	{
		return birthdayRecordService.getBirthdayRecord(id);
	}
	
	@PostMapping(value="/records")
	public void addTopic(@RequestBody BirthdayRecord birthdayRecord)
	{
		birthdayRecordService.addBirthdayRecord(birthdayRecord);
	}
	
	@PutMapping(value="/records/{id}")
	public void updateTopic(@RequestBody BirthdayRecord birthdayRecord)
	{
		birthdayRecordService.updateBirthdayRecord(birthdayRecord);
	}
	
	@DeleteMapping(value="/records/{id}")
	public void deleteTopic(@PathVariable Long id)
	{
		birthdayRecordService.deleteBirthdayRecord(id);
	}
	

}
