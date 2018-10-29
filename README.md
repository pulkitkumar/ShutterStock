# Shutter stock search.

### The app architecture is based on `clean code` by Uncle Bob Martin
#### There are 5 packages app is divided into.

* *domain* 
`this package contains the business logic, interfaces to fetch data, and data classes`
* *data* 
`this package depends on domain package, this implements the interfaces present in domain layer to fetch data from network or database`
* *presentation* 
`this package depends on domain package interfaces to fetch data and has the logic to orchestrate UI based on data fetching`
* *ui* 
`this package depends on presentation package by passing events to` ```ViewModel```` `and observing`  ```LiveData```. 
* *app* 
`this package contains dependency injection and has the logic to provide the correct dependencies based on build flavour, build type, device size etc.`
   
All packages are further subdivided based on features.  