import { Component, OnInit } from '@angular/core';
import { ItemApiService } from '../item-api.service';

@Component({
  selector: 'app-delete-item',
  templateUrl: './delete-item.component.html',
  styleUrls: ['./delete-item.component.css']
})
export class DeleteItemComponent implements OnInit {

  service :ItemApiService;
  searchId :any;
  item :any = {};

  constructor(service :ItemApiService) { 
    this.service = service;
    this.searchId=0;


  }

  ngOnInit(): void {
  }

  onDelete() :any {
    this.service.deleteItem(this.searchId).subscribe(resp => {
      console.log(resp)

    });
  }

}
