import { Component, OnInit } from '@angular/core';
import { WarehouseApiService } from '../warehouse-api.service';

@Component({
  selector: 'app-create-warehouse',
  templateUrl: './create-warehouse.component.html',
  styleUrls: ['./create-warehouse.component.css']
})
export class CreateWarehouseComponent implements OnInit {

  service :WarehouseApiService
  warehouse :any = {}

  constructor(service :WarehouseApiService) {
    this.service = service;
   }

  ngOnInit(): void {
  }

  submit(warehouse :any) :void {
    this.service.createWarehouse(warehouse).subscribe(resp => {
      console.log(resp)
    });
  }
}
