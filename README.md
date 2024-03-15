# Vlsm & CIDER Calculator

![](demo.gif)


## Requirements

**Vlsm & CIDER Calculator** is an Android library.

* min SDK is 24.


## Installation
Add this to your dependencies in gradle app file
```kotlin
implementation("com.ayoubelhoucine:vlsmcalculator:1.0.0-alpha")
```

## Usage
1. Calculate VLSM: provide IP address with host numbers, ex:
```kotlin
Networking.getInstance().calculateVlsm(
    "192.198.10.16", 
    hashMapOf("Host number 1" to 4, "Host number 2" to 6, "Host number 3" to 10)
)
```
   **you will get the result as list of Subnet object ```List<Subnet>```**

2. Or you Use a predefined UI of VLSM calculator: (it is a jetpack compose view)
```kotlin
VlsmCalculatorView()
```

   **In case you need the result from the predefined UI then you can call the ```onResult(List<Subnet>)``` from the view:**
```kotlin
VlsmCalculatorView { resutl ->
    // make your own login with the result
}
```



## Contributions
Feel free to contribute via fork/pull request to main branch. If you want new feature or report a bug then start a new issue.


## Author

__ELHOUCINE AYOUB__

