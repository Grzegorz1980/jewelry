import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SourceFilesTableComponent } from './source-files-table.component';

describe('SourceFilesTableComponent', () => {
  let component: SourceFilesTableComponent;
  let fixture: ComponentFixture<SourceFilesTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SourceFilesTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SourceFilesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
