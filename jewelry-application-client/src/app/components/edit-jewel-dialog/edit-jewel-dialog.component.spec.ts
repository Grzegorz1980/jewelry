import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditJewelDialogComponent } from './edit-jewel-dialog.component';

describe('EditJewelDialogComponent', () => {
  let component: EditJewelDialogComponent;
  let fixture: ComponentFixture<EditJewelDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditJewelDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditJewelDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
