import { Component, OnInit } from '@angular/core';
import { WarehouseApiService } from '../warehouse-api.service';

@Component({
  selector: 'app-warehouse-list',
  templateUrl: './warehouse-list.component.html',
  styleUrls: ['./warehouse-list.component.css']
})
export class WarehouseListComponent implements OnInit {

  service :WarehouseApiService;
  warehouses :Array<any>=[];

  constructor(service :WarehouseApiService) {
    this.service = service;
   }

  ngOnInit(): void {
    this.service.findAll().subscribe(data => {
      this.warehouses = data;
    });
  }

}
