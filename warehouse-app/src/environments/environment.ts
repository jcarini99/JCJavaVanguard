// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrlItemGetAll: 'http://localhost:8080/project-1/item/get',
  apiUrlItemGet: 'http://localhost:8080/project-1/item/get/',
  apiUrlItemAdd: 'http://localhost:8080/project-1/item/add/',
  apiUrlItemDelete: 'http://localhost:8080/project-1/item/delete/',
  apiUrlItemUpdate: 'http://localhost:8080/project-1/item/update/',

  apiUrlWarehouseGetAll: 'http://localhost:8080/project-1/warehouse/get',
  apiUrlWarehouseGet: 'http://localhost:8080/project-1/warehouse/get/',
  apiUrlWarehouseAdd: 'http://localhost:8080/project-1/warehouse/add/',
  apiUrlWarehouseDeleteW: 'http://localhost:8080/project-1/warehouse/deleteW/',
  apiUrlWarehouseDeleteWI: 'http://localhost:8080/project-1/warehouse/deleteWI/',
  apiUrlWarehouseUpdate: 'http://localhost:8080/project-1/warehouse/update/'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
