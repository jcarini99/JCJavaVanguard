import { Component, OnInit } from '@angular/core';
import { WarehouseApiService } from '../warehouse-api.service';

@Component({
  selector: 'app-delete-warehouse',
  templateUrl: './delete-warehouse.component.html',
  styleUrls: ['./delete-warehouse.component.css']
})
export class DeleteWarehouseComponent implements OnInit {

  service :WarehouseApiService
  warehouse :any = {}

  constructor(service :WarehouseApiService) {
    this.service = service;
   }

  ngOnInit(): void {
  }

  deleteW(warehouse :any) :void {
    this.service.deleteWarehouse(warehouse).subscribe(resp => {
      console.log(resp)
    });
  }

  deleteWI(warehouse :any) :void {
    this.service.deleteWarehouseItem(warehouse).subscribe(resp => {
      console.log(resp)
    });
  }

}
