import { Component, OnInit } from '@angular/core';
import { BirthdayRecordService } from '../services/birthdayRecord/birthday-record.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(public service: BirthdayRecordService) { }

  ngOnInit(): void {
  }

}
