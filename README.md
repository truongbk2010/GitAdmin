# **Git Admin App**
As an administrator, it is possible to browse all users who are the members of
GitHub site then we can see more detailed information about them.
- The administrator can look through fetched users’ information.
- The administrator can scroll down to see more users’ information with 20
items per fetch.
- Users’ information must be shown immediately when the administrator
launches the application for the second time.
- Clicking on an item will navigate to the page of user details.


In addition, the Unit Test and Android UI test are also included in project. To reference, please go to folder test/ android test in **feature-home**

**TECHSTACKS**

- Language: Kotlin
- Architecture: MVVM
- Libraries & Dependences: 
    - UI: Jetpack Compose, Navigation, Material 3
    - Coroutine, Kotlin Flow: Executes asynchronously
    - Interact Restful API: Retrofit, OkHttp, Moshi
    - Local database: Room database
    - Dependency Injection: Dagger-Hilt
    - Support MVVM Architecture: ViewModel, Kotlin Flow
    - Testing: JUnitTest (mockk), Android UI Test

**PROJECT STRUCTURE**

The project structure is inspired by multiple modules and follows the MVVM architecture:

- **core**: contain all data source, utils and mapping. It don't contain any UI component, it follow single source of truth
- **component**: Define common, reused UI component and theme, styleing
- **feature-home**: include Composable screen, navigation route, viewmodel for list users feature
- **feature-user**: include Composable screen, navigation route, viewmodel for user detail feature
- **shared-test**: define common, utils class for unit test and UI test
- **app**: entrypoint of app, define navigation graph

Sample UT/ UI test
- Unit test case study: There is a case study for Unit test of UserListViewModel in feature-home module
- Android test case study: There is a case study for UI Android test of UserListUIScreen in feature-home module
    
**RECORDING**
- https://drive.google.com/file/d/1hJyh2-bgPAdc2BUIst4C8Jq55x5cLgQp/view

**TEST RESULT**

- UserListViewModelTest

<img width="720" src="https://github.com/user-attachments/assets/ed2aa404-02ff-43cc-82fa-79b12355ae53">

- UserListUIScreenTest

<img width="720" src="https://github.com/user-attachments/assets/139f60e5-501e-409c-a701-0edbece92757">

