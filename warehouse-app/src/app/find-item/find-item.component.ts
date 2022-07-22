import { Component, OnInit } from '@angular/core';
import { ItemApiService } from '../item-api.service';

@Component({
  selector: 'app-find-item',
  templateUrl: './find-item.component.html',
  styleUrls: ['./find-item.component.css']
})
export class FindItemComponent implements OnInit {

  service :ItemApiService;
  searchId :any;
  items :Array<any>=[];
  item :any;

  constructor(service :ItemApiService) {
    this.service = service;
    this.searchId=0;

   }

  ngOnInit(): void {
  }

  onChange() :void {
    this.service.findById(this.searchId).subscribe(data => {
      this.items[0] = data;
      this.item=this.items[0];
    });
  }
}
