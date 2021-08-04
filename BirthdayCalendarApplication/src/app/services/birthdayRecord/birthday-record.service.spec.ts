import { TestBed } from '@angular/core/testing';

import { BirthdayRecordService } from './birthday-record.service';

describe('BirthdayRecordService', () => {
  let service: BirthdayRecordService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BirthdayRecordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
