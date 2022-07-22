import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WarehouseApiService {

  http :HttpClient;
  
  constructor(http :HttpClient) {
    this.http=http;

   }
   
  createWarehouse(warehouse :any) :Observable<any> {
    return this.http.post(environment.apiUrlWarehouseAdd, warehouse);
   }

  findAll() :Observable<any> {
    return this.http.get(environment.apiUrlWarehouseGetAll);
   }

   findByIdName(search :any):Observable<any> {
    return this.http.get(environment.apiUrlWarehouseGet + search);
  }

  updateWarehouse(warehouse :any) :Observable<any> {
    return this.http.put(environment.apiUrlWarehouseUpdate, warehouse);
  }

  deleteWarehouse(warehouse :any) :Observable<any> {
    return this.http.delete(environment.apiUrlWarehouseDeleteW, warehouse);
  }

  deleteWarehouseItem(warehouse :any) :Observable<any> {
    return this.http.delete(environment.apiUrlWarehouseDeleteWI, warehouse);
  }
}
