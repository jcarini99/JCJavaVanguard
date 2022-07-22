import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'warehouse-app';
  findWarehouseDiv :boolean = false;
  findItemDiv :boolean = false;
  createWarehouseDiv :boolean = false;
  createItemDiv :boolean = false;
  updateWarehouseDiv :boolean = false;
  updateItemDiv :boolean = false;
  deleteWarehouseDiv :boolean = false;
  deleteItemDiv :boolean = false;

  toggleFWCollapse() {
    this.findWarehouseDiv = !this.findWarehouseDiv;
  }
  toggleFICollapse() {
    this.findItemDiv = !this.findItemDiv;
  }
  toggleCWCollapse() {
    this.createWarehouseDiv = !this.createWarehouseDiv;
  }
  toggleCICollapse() {
    this.createItemDiv = !this.createItemDiv;
  }
  toggleUWCollapse() {
    this.updateWarehouseDiv = !this.updateWarehouseDiv;
  }
  toggleUICollapse() {
    this.updateItemDiv = !this.updateItemDiv;
  }
  toggleDWCollapse() {
    this.deleteWarehouseDiv = !this.deleteWarehouseDiv;
  }
  toggleDICollapse() {
    this.deleteItemDiv = !this.deleteItemDiv;
  }

}
