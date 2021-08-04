import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { BirthdayRecord } from 'src/app/models/birthday-record';
import { AuthService } from 'src/app/services/auth/auth.service';
import { BirthdayRecordService } from 'src/app/services/birthdayRecord/birthday-record.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(private authService: AuthService, public birthdayRecordService: BirthdayRecordService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.birthdayRecordService.refreshList(this.authService.getLoggedInUsername());
  }

  populateForm(selectedRecord: BirthdayRecord) {
    this.birthdayRecordService.formData = Object.assign({}, selectedRecord);
  }

  onDelete(id: number) {
    if (confirm("Are you sure you want to delete this record?")) {
      this.birthdayRecordService.deleteBirthdayRecord(id)
        .subscribe(res => {
          this.birthdayRecordService.refreshList(this.authService.getLoggedInUsername());
          this.toastr.success("Record deleted successfully", "Sucess")
        },
          err => {
            this.toastr.error("Error while deleting record", "Error")
          }
        )
    }
  }
}
