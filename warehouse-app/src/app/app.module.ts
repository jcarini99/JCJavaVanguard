import { Input, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ItemListComponent } from './item-list/item-list.component';
import {HttpClientModule} from '@angular/common/http'
import { FormsModule } from '@angular/forms';
import { FindItemComponent } from './find-item/find-item.component';
import { WarehouseListComponent } from './warehouse-list/warehouse-list.component';
import { FindWarehouseComponent } from './find-warehouse/find-warehouse.component';
import { CreateWarehouseComponent } from './create-warehouse/create-warehouse.component';
import { CreateItemComponent } from './create-item/create-item.component';
import { UpdateWarehouseComponent } from './update-warehouse/update-warehouse.component';
import { UpdateItemComponent } from './update-item/update-item.component';
import { DeleteItemComponent } from './delete-item/delete-item.component';
import { DeleteWarehouseComponent } from './delete-warehouse/delete-warehouse.component';
import {ButtonModule} from 'primeng/button';
import {AccordionModule} from 'primeng/accordion';     //accordion and accordion tab
import {MenuItem} from 'primeng/api';                  //api
import {InputTextModule} from 'primeng/inputtext';
import {TableModule} from 'primeng/table';
import {ToolbarModule} from 'primeng/toolbar';
import {SplitButtonModule} from 'primeng/splitbutton';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';

@NgModule({
  declarations: [
    AppComponent,
    ItemListComponent,
    FindItemComponent,
    WarehouseListComponent,
    FindWarehouseComponent,
    CreateWarehouseComponent,
    CreateItemComponent,
    UpdateWarehouseComponent,
    UpdateItemComponent,
    DeleteItemComponent,
    DeleteWarehouseComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ButtonModule,
    AccordionModule,
    InputTextModule,
    TableModule,
    ToolbarModule,
    SplitButtonModule,
    BrowserAnimationsModule,
    MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
