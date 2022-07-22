import { Component, OnInit } from '@angular/core';
import { ItemApiService } from '../item-api.service';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {

  service :ItemApiService
  item :any ={}

  constructor(service :ItemApiService) { 
    this.service = service;


  }

  ngOnInit(): void {
  }

  submit(item :any) :void {
    this.service.createItem(item).subscribe(resp => {
      console.log(resp)
    });
  }

}
