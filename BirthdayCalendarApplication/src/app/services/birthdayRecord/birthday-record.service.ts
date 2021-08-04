import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BirthdayRecord } from 'src/app/models/birthday-record';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class BirthdayRecordService {

  constructor(private http: HttpClient, private authService: AuthService) { }

  readonly baseUrl = "http://localhost:8080/api/records"
  recordList: BirthdayRecord[] = [];
  formData: BirthdayRecord = new BirthdayRecord();

  postBirthdayRecord(){
    this.formData.userName = this.authService.getLoggedInUsername();
   return this.http.post(this.baseUrl, this.formData);
  }

  putBirthdayRecord(){
    return this.http.put(`${this.baseUrl}/${this.formData.id}`, this.formData);
  }

  deleteBirthdayRecord(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  refreshList(userName: string) {
    this.http.get(`${this.baseUrl}/${userName}`)
      .toPromise()
      .then(res => this.recordList = res as BirthdayRecord[]);
  }
}
