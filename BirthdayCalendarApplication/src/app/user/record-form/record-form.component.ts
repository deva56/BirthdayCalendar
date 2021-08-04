import { Component, Injectable, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { BirthdayRecord } from 'src/app/models/birthday-record';
import { AuthService } from 'src/app/services/auth/auth.service';
import { BirthdayRecordService } from 'src/app/services/birthdayRecord/birthday-record.service';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-record-form',
  templateUrl: './record-form.component.html',
  styleUrls: ['./record-form.component.css']
})
export class RecordFormComponent implements OnInit {

  recordForm: FormGroup;

  constructor(private authService: AuthService, public recordService: BirthdayRecordService, private toastr: ToastrService) { 
    this.recordForm = new FormGroup({
      personName: new FormControl('', Validators.required),
      birthdayDate: new FormControl('', Validators.required)
    })
  }

  ngOnInit(): void {
  }


  submit(form: NgForm) {
    if (this.recordService.formData.id == 0) {
      this.insertRecord(form);
    }
    else {
      this.updateRecord(form);
    }
  }

  insertRecord(form: NgForm) {
    this.recordService.postBirthdayRecord().subscribe(
      res => {
        this.resetForm(form);
        this.recordService.refreshList(this.authService.getLoggedInUsername());
        this.toastr.success("Record successfully saved", "Sucess")
      },
      error => {
        console.log(error);
        this.toastr.error("Erorr during record saving", "Error")
      }
    );
  }

  updateRecord(form: NgForm) {
    this.recordService.putBirthdayRecord().subscribe(
      res => {
        this.resetForm(form);
        this.recordService.refreshList(this.authService.getLoggedInUsername());
        this.toastr.success("Record successfully updated", "Sucess")
      },
      error => {
        console.log(error);
        this.toastr.error("Erorr during record updating", "Error")
      }
    );
  }

  resetForm(form: NgForm) {
    form.form.reset();
    this.recordService.formData = new BirthdayRecord();
  }
}
