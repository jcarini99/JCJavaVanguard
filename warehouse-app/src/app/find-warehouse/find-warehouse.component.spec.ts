import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindWarehouseComponent } from './find-warehouse.component';

describe('FindWarehouseComponent', () => {
  let component: FindWarehouseComponent;
  let fixture: ComponentFixture<FindWarehouseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindWarehouseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FindWarehouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
