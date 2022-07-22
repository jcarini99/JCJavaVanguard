import { Component, OnInit } from '@angular/core';
import { WarehouseApiService } from '../warehouse-api.service';

@Component({
  selector: 'app-find-warehouse',
  templateUrl: './find-warehouse.component.html',
  styleUrls: ['./find-warehouse.component.css']
})
export class FindWarehouseComponent implements OnInit {
    service :WarehouseApiService;
    searchId :any;
    warehouses :Array<any>=[];

  constructor(service :WarehouseApiService) {
    this.service = service;
    this.searchId=0;
   }

  ngOnInit(): void {
  }

  onChange() :void {
    this.service.findByIdName(this.searchId).subscribe(data => {
      this.warehouses = data;
    });
  }
}
