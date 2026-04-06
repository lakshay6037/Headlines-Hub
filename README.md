# 📰 Headline – Modern News App (Jetpack Compose)

Headline is a modern Android news application built using **Jetpack Compose** and **Clean Architecture** principles.  
The app delivers the latest news with a smooth user experience, offline bookmarking, and full dark/light theme support.

---

## ✨ Features

- 🚀 **Jetpack Compose UI** (Material 3)
- 🧭 **Onboarding Flow** with high-quality illustrations
- 🌙 **Dark & Light Theme** (System aware)
- 📰 **Latest News Feed** using Paging 3
- 🔍 **Search News** in real time
- 🔖 **Bookmark Articles** (Offline support using Room)
- 🧾 **Article Details Screen**
- 🌐 **Open in Browser & Share**
- ⚡ **Smooth Navigation** using Compose Navigation
- 🧠 **MVVM + Clean Architecture**
- 💉 **Hilt Dependency Injection**

---

## 🛠 Tech Stack

### 🔹 UI & Framework
- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Compose Navigation**

### 🔹 Architecture
- **MVVM**
- **Clean Architecture**
- **State Hoisting**
- **Unidirectional Data Flow**

```text
┌────────────────────────────┐
│        Composable UI       │
│  (HomeScreen, Details...)  │
└────────────┬───────────────┘
             │
             │ UI Event
             │ (onClick, onSearch, onBookmark)
             ▼
┌────────────────────────────┐
│         ViewModel          │
│  - handles events          │
│  - launches coroutines     │
│  - updates state           │
└────────────┬───────────────┘
             │
             │ Calls
             ▼
┌────────────────────────────┐
│          UseCases          │
│   (GetNews, SaveArticle)   │
└────────────┬───────────────┘
             │
             ▼
┌────────────────────────────┐
│        Repository          │
│   (Single source of truth) │
└────────────┬───────────────┘
             │
     ┌───────┴────────┐
     ▼                ▼
┌───────────┐   ┌───────────┐
│   API     │   │   Room    │
│ (Remote)  │   │ (Local)   │
└───────────┘   └───────────┘

             ▲
             │  Flow / State
             │
┌────────────┴───────────────┐
│         ViewModel          │
└────────────┬───────────────┘
             │
             ▼
┌────────────────────────────┐
│        Composable UI       │
└────────────────────────────┘

```


### 🔹 Async & Data
- **Kotlin Coroutines**
- **Flow**
- **Paging 3**

### 🔹 Persistence
- **Room Database**
- **Offline-first bookmarks**
- **Session-Persistance** using **DataStore**

### 🔹 Dependency Injection
- **Hilt**

### 🔹 Image Loading
- **Coil**

---

## 📂 Project Structure

```text

com.example.headlinejetpackcompose
│
├── data
│ ├── local (Room DB, DAO)
│ ├── remote (API, DTOs)
│ └── repository
│
├── domain
│ ├── model
│ ├── repository
│ └── usecases
│
├── presentation
│ ├── onboarding
│ ├── splash
│ ├── home
│ ├── search
│ ├── bookmark
│ ├── details
│ ├── navigation
│ └── common (Reusable composables)
│
├── di (Hilt modules)
└── theme

```
---

## 🧭 Navigation Flow (Nested Navigation Graph)

The app uses **nested navigation graphs** to separate app entry flows from in-app navigation, ensuring clean back stack management and scalability.

```text
Root NavGraph
│
├── Splash Screen
│     ↓
│   Onboarding Screen (First Launch Only)
│     ↓
│   News Navigator (Main App)
│
└── News NavGraph (Nested)
      │
      ├── Home Screen
      │
      ├── Search Screen
      │
      ├── Bookmark Screen
      │
      └── Article Details Screen
```
- Splash and Onboarding screens act as one-time entry points and are removed from the back stack.

- Article details are opened on top of the current tab and support proper back navigation.
- Back navigation exits the app from the Home screen, preventing unintended returns to Splash or Onboarding.
---

## 🌙 Dark Theme Support

- Fully Material 3 compliant
- No hardcoded colors
- Uses `MaterialTheme.colorScheme`
- Automatically adapts to system theme
- Pagination shimmer loading

---

## 📦 Local Storage (Bookmarks)

- Articles can be bookmarked/unbookmarked
- Stored using **Room**
- Automatically updates Bookmark screen
- Handles duplicate entries using unique article URL


## 🧪 Future Improvements

- Category-wise news
- Offline caching for feed
- Manual theme toggle
- Animations for bookmark actions

---

## 🧠 Key Learnings

- Building scalable UI with Jetpack Compose
- Handling side-effects safely in Compose
- Managing back stack properly in Navigation
- Designing dark-theme-safe UI
- Pagination using Paging3
- Implementing offline-first features

---

## 🚀 Getting Started

1. Clone the repository
2. Open in **Android Studio (Hedgehog or later)**
3. Sync Gradle
4. Run on emulator or physical device

---

## 👤 Author

**Aditya Gupta**  
Android Developer  
Kotlin • Jetpack Compose • MVVM • Clean Architecture

---

## 📄 License

This project is for learning and demonstration purposes.
