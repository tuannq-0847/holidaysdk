# 🎉 HolidayHelper

Effortlessly check holidays for any date and country using Kotlin coroutines!

# 🌟 Features

- Check Holidays: Verify if a specific date is a holiday in any supported country.
- Multi-Country Support: Use country codes for global holiday checks.
- Asynchronous & Smooth: Built with coroutines for non-blocking operations.

## 📌 Requirement

- Kotlin coroutines
- Minimum SDK Version: 21

## ⚙️ Installation

- Download or clone the **holidaylibrary** module from the repository.
- Copy the entire **holidaylibrary** folder into your project’s root directory.
- Edit your **settings.gradle** file to include the module

```
include ':app', ': holidaylibrary'
```

- Add the module as a dependency in your **app/build.gradle** file:

```
dependencies {
    implementation project(':holidaylibrary')
}
```

- Sync Project and Done you have it!!!

## 🚀 Usage

- Basic sample

```kotlin
    lifecycleScope.launch {
        HolidayHelper.checkHoliday(countryCode = "VN", day = 1, month = 1, year = 2025)
            .collect {
                if (it.isSuccess) {
                    //handle response as boolean
                } else {
                    //receive failure message
            }
        }
    }
```

- The ```HolidayHelper``` provide an enum with 3 states that can make the api more customizable

```kotlin
    enum class HolidayState {
        ANY, //return true if any of the sources return that a given date is a holiday 
        ALL, //return true if all of the sources return that a given date is a holiday 
        CONSENSUS //return true if majority of the sources return that a given date is a holiday
    }
```

- Setting State with ```HolidayState.setState()```

```kotlin
    lifecycleScope.launch {
    HolidayHelper.setState(HolidayState.CONSENSUS)
        .checkHoliday(countryCode = "VN", day = 1, month = 1, year = 2025)
        .collect {
            if (it.isSuccess) {
                //handle response as boolean
            } else {
                //receive failure message
            }
        }
}
```

## Demo


## 📲 Download Sample App
- [APK](https://github.com/tuannq-0847/holidaysdk/releases/download/v1.0.0/app-debug.apk)
- [Demo](https://github.com/user-attachments/assets/55775735-5b45-4f09-a824-5d92a3648cbe)

## 🤝 Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## 📄 License

N/A