import { Component, OnInit } from '@angular/core';
import {ItemApiService} from '../item-api.service'

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  service :ItemApiService;
  items :Array<any>=[];

  constructor(service :ItemApiService) {
    this.service = service;
   }

  ngOnInit(): void {
    this.service.findAll().subscribe(data => {
      this.items = data;
    });
  }

}
