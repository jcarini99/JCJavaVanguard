import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'warehouse-app';
  findWarehouseDiv :boolean = true;
  findItemDiv :boolean = true;
  createWarehouseDiv :boolean = true;
  createItemDiv :boolean = true;
  updateWarehouseDiv :boolean = true;
  updateItemDiv :boolean = true;
  deleteWarehouseDiv :boolean = true;
  deleteItemDiv :boolean = true;

  toggleFWCollapse() {
    this.findWarehouseDiv !=this.findWarehouseDiv;
  }
  toggleFICollapse() {
    this.findItemDiv !=this.findItemDiv;
  }
  toggleCWCollapse() {
    this.createWarehouseDiv !=this.createWarehouseDiv;
  }
  toggleCICollapse() {
    this.createItemDiv !=this.createItemDiv;
  }
  toggleUWCollapse() {
    this.updateWarehouseDiv !=this.updateWarehouseDiv;
  }
  toggleUICollapse() {
    this.updateItemDiv !=this.updateItemDiv;
  }
  toggleDWCollapse() {
    this.deleteWarehouseDiv !=this.deleteWarehouseDiv;
  }
  toggleDICollapse() {
    this.deleteItemDiv !=this.deleteItemDiv;
  }

}
