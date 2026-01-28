# ItunesList

This project is an Android application developed in Kotlin using Jetpack Compose, which displays a list of the most popular iTunes albums. The app follows a modular architecture, separating responsibilities into different modules (core, feature, navigation, ui, resources).

## Technologies and Libraries Used

- **Kotlin**
- **Jetpack Compose** (declarative UI)
- **Kotlinx Serialization** (data serialization)
- **Koin** (dependency injection)
- **Coroutines & Flow** (concurrency and reactivity)
- **Immutable Collections** (Kotlinx Collections Immutable)
- **Retrofit** (HTTP requests)
- **Navigation Compose** (screen navigation)
- **JUnit & Turbine** (unit and flow testing)

## Project Structure

- `core/` — Project base structure and utilities
- `feature/albumslist/` — Albums list screen
- `feature/albumdetail/` — Album detail screen
- `navigation/` — Centralized navigation management
- `ui/` — Reusable visual components
- `resources/` — Strings, themes, images, etc.

## How to Run the Project

1. **Prerequisites:**
   - Android Studio Meerkat (2024.3.1 Patch 1) or newer
   - JDK 17+
   - Android emulator or device

2. **Clone the repository:**
   ```bash
   git clone https://github.com/dpessoto/ItunesList.git
   cd ItunesList
   ```

3. **Open the project in Android Studio:**
   - Select the `ItunesList` folder.

4. **Sync dependencies:**
   - Android Studio will automatically download all dependencies.

5. **Run the project:**
   - Choose an emulator or physical device.
   - Click "Run".

6. **Running tests:**
   - To run unit tests, right-click the `test` folder in each module and select "Run Tests".

## Features

- List of the most popular iTunes albums
- Complete album details (name, artist, price, release date, genre, image)
- Navigation between screens
- Error handling and loading states
- Modular and scalable architecture

## Notes

- The project uses Clean Architecture and layer separation.
- Date loading and formatting is efficient, with no perceptible performance impact.
- The code is ready for easy expansion of new features.

---
