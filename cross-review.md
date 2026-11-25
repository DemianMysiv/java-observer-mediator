# Cross-Review / рос-рев'ю (абораторна робота 14: Observer, Mediator)

**оманда / Team:**
- **ем'ян исів (Demian Mysiv)** — авдання 1 (`Mediator`) / ецензент авдання 2
- **дуард алоба (Eduard Zhaloba)** — авдання 2 (`Observer`) / ецензент авдання 1

---

## астина 1: удит авдання 1 — оординація наземних служб (`Mediator`)
## Part 1: Audit of Task 1 — Ground Services Coordination (`Mediator`)

**втор / Author:** ем'ян исів (Demian Mysiv)  
**ецензент / Reviewer:** дуард алоба (Eduard Zhaloba)

### 1. ідповідність діаграми класів коду / Class Diagram vs. Code Compliance
- **UA:** UML-діаграма (`docs/diagrams/task1_mediator.puml` та `task1_mediator.png`) повністю відповідає написаному коду. оректно відображено інтерфейс `GroundServiceMediator`, абстрактний базовий клас `GroundService`, конкретний посередник `GroundControlCoordinator`, п'ять конкретних служб (`CleaningService`, `CateringService`, `BaggageService`, `TowingService`, `RefuelingService`) та клієнта `Task1Demo`. одифікатори доступу (`+`, `-`, `#`), стереотипи ролей, успадкування, реалізація, асоціації та агрегація з кратністю вказані точно.
- **EN:** The UML diagram (`docs/diagrams/task1_mediator.puml` and `task1_mediator.png`) fully matches the implemented code. The `GroundServiceMediator` interface, abstract `GroundService` base class, `GroundControlCoordinator` concrete mediator, five concrete services (`CleaningService`, `CateringService`, `BaggageService`, `TowingService`, `RefuelingService`), and `Task1Demo` client are accurately depicted with proper access modifiers (`+`, `-`, `#`), role stereotypes, inheritance, realization, associations, and aggregations with multiplicities.

### 2. сунення проблеми з опису ситуації / Problem Resolution
- **UA:** роблему прямих зв'язків «багато-до-багатьох» між наземними службами повністю усунуто. одна служба не має посилань на інші служби — усі повідомляють про свої події лише через інтерфейс `GroundServiceMediator`. сі правила послідовності та безпеки (запуск кейтерингу після прибирання, дозвіл на буксирування після завантаження багажу, заборона заправки під час посадки пасажирів) зосереджені в `GroundControlCoordinator`. одавання нової служби (наприклад, протиобмерзальної обробки) не потребує змін в існуючих службах.
- **EN:** The problem of direct many-to-many coupling between ground services is completely resolved. No service holds references to other services — all communicate exclusively via the `GroundServiceMediator` interface. All sequencing and safety rules (starting catering after cleaning, allowing towing after baggage loading, postponing refueling during passenger boarding) are centralized in `GroundControlCoordinator`. Adding a new service (e.g., de-icing) requires no modifications to existing service classes.

### 3. еревірка раннера та демонстраційного сценарію / Runner & Demo Scenario Verification
- **UA:** `Task1Demo.run()` коректно викликається з `Main.java` і повністю відтворює всі 3 сценарії з дотриманням формату журналу `[час] [джерело] подія`:
  1. авершення прибирання (`CleaningService.finishCleaning()`) → координатор фіксує ініціатора та запускає `CateringService`.
  2. авершення завантаження багажу (`BaggageService.finishLoading()`) → координатор надає дозвіл `TowingService`.
  3. Спроба заправки (`RefuelingService.requestRefueling()`) при активній посадці пасажирів (`boardingInProgress = true`) → координатор відкладає заправку із зазначенням причини.
- **EN:** `Task1Demo.run()` is properly invoked from `Main.java` and accurately reproduces all 3 scenarios following the `[time] [source] event` log format:
  1. Cleaning completion (`CleaningService.finishCleaning()`) → coordinator logs the initiator and triggers `CateringService`.
  2. Baggage loading completion (`BaggageService.finishLoading()`) → coordinator grants permission to `TowingService`.
  3. Refueling request (`RefuelingService.requestRefueling()`) during active passenger boarding (`boardingInProgress = true`) → coordinator postpones refueling with a clear reason.

### 4. ауваження та рекомендації / Remarks & Recommendations
- **UA:**
  1. **ядкові літерали подій:** азви подій (`"CLEANING_COMPLETED"`, `"BAGGAGE_LOADED"`, `"REFUELING_REQUESTED"`) передаються як звичайні `String`, що створює ризик друкарських помилок. арто винести їх в `enum GroundEvent`.
  2. **евикористані прапорці стану:** оля `cleaningCompleted` та `baggageLoaded` у `GroundControlCoordinator` змінюються на `true`, але далі ніде не читаються. х доцільно використати для додаткових перевірок або видалити, якщо вони не потрібні.
  3. **птимізація логування:** `DateTimeFormatter.ofPattern("HH:mm:ss")` створюється заново при кожному виклику `log()` у `GroundService` та `GroundControlCoordinator`. раще винести його в `private static final` константу.
- **EN:**
  1. **String event literals:** Event names (`"CLEANING_COMPLETED"`, `"BAGGAGE_LOADED"`, `"REFUELING_REQUESTED"`) are passed as raw `String` values, which is prone to typos. Extracting them into a `GroundEvent` enum is recommended.
  2. **Unused state flags:** `cleaningCompleted` and `baggageLoaded` in `GroundControlCoordinator` are set to `true` but never read. They should either be used in validation checks or removed.
  3. **Logging optimization:** `DateTimeFormatter.ofPattern("HH:mm:ss")` is recreated on every `log()` call in both `GroundService` and `GroundControlCoordinator`. Defining it as a `private static final` constant would be cleaner.

---

## астина 2: удит авдання 2 — Сповіщення про зміну статусу рейсу (`Observer`)
## Part 2: Audit of Task 2 — Flight Status Change Notification (`Observer`)

**втор / Author:** дуард алоба (Eduard Zhaloba)  
**ецензент / Reviewer:** ем'ян исів (Demian Mysiv)

### 1. ідповідність діаграми класів коду / Class Diagram vs. Code Compliance
- **UA:** а момент проведення рев'ю UML-діаграма для авдання 2 (`task2_observer.puml` / `.png`) ще не додана до теки `docs/diagrams/`. еобхідно додати діаграму, яка відображає суб'єкт `Flight` (Subject), інтерфейс `FlightObserver` (Observer), чотирьох конкретних спостерігачів (`InformationBoard`, `Passenger`, `CateringService`, `TaxiService`) та клієнта `Task2Demo` з відповідними модифікаторами доступу та кратністю зв'язків.
- **EN:** At the time of the review, the UML diagram for Task 2 (`task2_observer.puml` / `.png`) has not yet been added to `docs/diagrams/`. A diagram must be added depicting the `Flight` subject, the `FlightObserver` interface, the four concrete observers (`InformationBoard`, `Passenger`, `CateringService`, `TaxiService`), and the `Task2Demo` client with proper access modifiers and multiplicities.

### 2. сунення проблеми з опису ситуації / Problem Resolution
- **UA:** роблему жорсткого зв'язування класу рейсу з конкретними службами повністю усунуто. лас `Flight` працює виключно через інтерфейс `FlightObserver` і не залежить від конкретних отримувачів. ідписка та відписка відбуваються динамічно під час виконання (`addObserver()`, `removeObserver()`), а кожен спостерігач самостійно визначає реакцію на подію в методі `update()` (зокрема `TaxiService` реагує лише на затримку та зміну виходу). отримано принцип Open/Closed: підключення нового отримувача не потребує змін у `Flight`.
- **EN:** Tight coupling between the flight class and concrete notification recipients is fully resolved. `Flight` interacts exclusively through the `FlightObserver` interface without knowing concrete subscriber types. Subscription and unsubscription occur dynamically at runtime (`addObserver()`, `removeObserver()`), and each observer independently defines its reaction in `update()` (for example, `TaxiService` filters for delays and gate changes). The Open/Closed Principle is respected: adding a new subscriber requires no modifications to `Flight`.

### 3. еревірка раннера та демонстраційного сценарію / Runner & Demo Scenario Verification
- **UA:** `Task2Demo` повністю відтворює всі 3 кроки демонстраційного сценарію для рейсу `PS-101` із дотриманням формату консольного журналу `[час] [джерело] подія`:
  1. ідписано табло (`InformationBoard`), пасажира (`Passenger("ван")`) та кейтеринг (`CateringService`), виконано `delayFlight("18:45")` — у журналі зафіксовано реакцію кожного.
  2. ідписано `TaxiService` і виконано `changeGate("F3")` — відреагували всі четверо підписників.
  3. ідписано `CateringService` і виконано `startBoarding()` — кейтеринг більше не отримує сповіщень.
- **EN:** `Task2Demo` accurately reproduces all 3 steps of the demonstration scenario for flight `PS-101` following the `[time] [source] event` log format:
  1. Subscribed `InformationBoard`, `Passenger("ван")`, and `CateringService`, then called `delayFlight("18:45")` — each observer's reaction is logged.
  2. Subscribed `TaxiService` and called `changeGate("F3")` — all four subscribers react.
  3. Unsubscribed `CateringService` and called `startBoarding()` — catering no longer receives notifications.

### 4. ауваження та рекомендації / Remarks & Recommendations
- **UA:**
  1. **нтеграція з `Main.java`:**  `Task2Demo` реалізовано власний метод `main(String[] args)` замість статичного `run()`. ля запуску обох завдань з єдиної точки входу (`Main.runSituation2()`) варто додати метод `public static void run()` та підключити його в `Main.java`.
  2. **ільтрація подій у `TaxiService`:** еревірка типу події через `eventDescription.contains("затримано")` та `contains("мінено вихід")` чутлива до змін формулювань. екомендується ввести `enum FlightEventType` (наприклад, `DELAYED`, `GATE_CHANGED`, `BOARDING_STARTED`) і передавати його в `update()`.
  3. **ммутабельність та дублікати:** оля `flightNumber` і `observers` у `Flight` та `name` у `Passenger` доцільно позначити як `final`. Також у `addObserver()` варто перевіряти наявність підписника (`!observers.contains(observer)`), щоб уникнути повторних сповіщень.
  4. **іаграма:** одати вихідний файл і зображення UML-діаграми авдання 2 у `docs/diagrams/`.
- **EN:**
  1. **Integration with `Main.java`:** `Task2Demo` defines its own `main(String[] args)` instead of a static `run()` method. Adding `public static void run()` and calling it from `Main.runSituation2()` will unify execution under a single entry point.
  2. **Event filtering in `TaxiService`:** Detecting event types via `eventDescription.contains("затримано")` and `contains("мінено вихід")` is fragile against wording changes. Introducing a `FlightEventType` enum (e.g., `DELAYED`, `GATE_CHANGED`, `BOARDING_STARTED`) and passing it to `update()` is recommended.
  3. **Immutability & duplicate prevention:** Marking `flightNumber` and `observers` in `Flight` and `name` in `Passenger` as `final` improves safety. Checking `!observers.contains(observer)` in `addObserver()` would prevent duplicate notifications.
  4. **Diagram:** Add the Task 2 UML diagram source and image files to `docs/diagrams/`.

---

## астина 3: ргументація вибору шаблонів для захисту / Pattern Selection Argumentation

### авдання 1 — `Mediator` (ем'ян / Demian)
- **UA:** Шаблон **Mediator** обрано тому, що п'ять наземних служб (`CleaningService`, `CateringService`, `BaggageService`, `TowingService`, `RefuelingService`) мають узгоджувати послідовність робіт і правила безпеки без прямих викликів одна одної. еренесення всієї логіки координації в єдиний клас `GroundControlCoordinator` (через інтерфейс `GroundServiceMediator`) замінює зв'язки «багато-до-багатьох» на топологію «зірка», повністю ізолює служби та дозволяє додавати нові служби (наприклад, протиобмерзальну обробку) без модифікації існуючих класів.
- **EN:** The **Mediator** pattern was chosen because five ground services (`CleaningService`, `CateringService`, `BaggageService`, `TowingService`, `RefuelingService`) must coordinate their workflow and safety constraints without directly calling one another. Centralizing all coordination rules in `GroundControlCoordinator` (via the `GroundServiceMediator` interface) replaces many-to-many dependencies with a star topology, decouples the services, and allows adding new services (such as de-icing) without modifying existing classes.

### авдання 2 — `Observer` (дуард / Eduard)
- **UA:** Шаблон **Observer** обрано, оскільки зміна стану одного об'єкта (`Flight`) має автоматично сповіщати довільну кількість незалежних отримувачів (`InformationBoard`, `Passenger`, `CateringService`, `TaxiService`) за принципом «один-до-багатьох». икористання інтерфейсу `FlightObserver` дозволяє динамічно підписувати й відписувати отримувачів під час виконання програми, дає змогу кожному з них самостійно визначати власну реакцію на подію та забезпечує дотримання принципу Open/Closed при підключенні нових сервісів без змін у класі `Flight`.
- **EN:** The **Observer** pattern was chosen because state changes in a single subject (`Flight`) must automatically notify an arbitrary number of independent subscribers (`InformationBoard`, `Passenger`, `CateringService`, `TaxiService`) in a one-to-many relationship. Using the `FlightObserver` interface enables dynamic runtime subscription and unsubscription, allows each observer to define its own reaction independently, and upholds the Open/Closed Principle when adding new services without modifying `Flight`.