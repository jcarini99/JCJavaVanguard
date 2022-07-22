import { Component, OnInit } from '@angular/core';
import { WarehouseApiService } from '../warehouse-api.service';

@Component({
  selector: 'app-update-warehouse',
  templateUrl: './update-warehouse.component.html',
  styleUrls: ['./update-warehouse.component.css']
})
export class UpdateWarehouseComponent implements OnInit {

  service :WarehouseApiService
  warehouse :any = {}

  constructor(service :WarehouseApiService) {
    this.service = service;
   }

  ngOnInit(): void {
  }

  submit(warehouse :any) :void {
    this.service.updateWarehouse(warehouse).subscribe(resp => {
      console.log(resp)
    });
  }

}
