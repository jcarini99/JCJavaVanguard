import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ItemApiService {

  http :HttpClient;

  constructor(http :HttpClient) {
    this.http=http;

   }

   createItem(item :any) :Observable<any> {
    return this.http.post(environment.apiUrlItemAdd, item);
   }

   findAll() :Observable<any> {
    return this.http.get(environment.apiUrlItemGetAll);
   }

   findById(id :number):Observable<any> {
    return this.http.get(environment.apiUrlItemGet + id);
  }

  findByName(name :String) :Observable<any> {
    return this.http.get(environment.apiUrlItemGet+name);
  }

  updateItem(item :any) :Observable<any> {
    return this.http.put(environment.apiUrlItemUpdate, item);
  }

  deleteItem(search :any) :Observable<any> {
    return this.http.delete(environment.apiUrlItemDelete, search);
  }
}
