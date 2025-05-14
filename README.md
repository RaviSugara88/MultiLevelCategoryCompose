# 📦 Multi-Level Category Navigation App (Jetpack Compose)

A modern Android app built using **Jetpack Compose** that demonstrates dynamic multi-level category navigation. The app displays categories and items in a grid layout, supports nested hierarchies, and provides smooth back navigation using a path-based state model.

---

## ✨ Features

- 🌿 Built entirely with **Jetpack Compose**
- 🗂️ Supports nested categories and items
- 🔁 Dynamic grid layout using `LazyVerticalGrid`
- 🔙 Smart back navigation using a stack-based path
- 🧠 MVVM architecture with `ViewModel` and `Kotlin Flow`
- 🎨 Responsive UI with adaptive layout
- 🍭 Snackbar support for user feedback

---

## 📸 Screenshots

| Category View | Nested Items | Snackbar |
|---------------|--------------|----------|
| ![Screenshot1](assets/screen1.png) | ![Screenshot2](assets/screen2.png) | ![Screenshot3](assets/screen3.png) |

---

## 🧱 Project Structure

composedemo25/
│
├── ui/
│ ├── view/
│ │ └── CategoryScreen.kt # Main UI screen for displaying the category grid
│ └── viewmodel/
│ └── MainViewModel.kt # Holds navigation logic and current list state
│
├── models/
│ ├── Category.kt # Data model for Category
│ ├── Item.kt # Data model for Item
│ └── Extensions.kt # Utility extensions (e.g. hasChildren)
│
├── utils/
│ └── JsonLoader.kt # Utility to parse static JSON from assets
│
└── MainActivity.kt # Hosts the Compose UI
