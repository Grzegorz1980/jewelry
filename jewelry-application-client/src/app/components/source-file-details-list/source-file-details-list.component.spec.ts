import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SourceFileDetailsListComponent } from './source-file-details-list.component';

describe('SourceFileDetailsListComponent', () => {
  let component: SourceFileDetailsListComponent;
  let fixture: ComponentFixture<SourceFileDetailsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SourceFileDetailsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SourceFileDetailsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
