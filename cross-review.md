# Cross-Review / Крос-рев'ю (Лабораторна робота №14: Observer, Mediator)

**Команда / Team:**
- **Дем'ян Мисів (Demian Mysiv)** — Завдання 1 (`Mediator`) / Рецензент Завдання 2
- **Едуард Жалоба (Eduard Zhaloba)** — Завдання 2 (`Observer`) / Рецензент Завдання 1

---

## Частина 1: Аудит Завдання 1 — Координація наземних служб (`Mediator`)
## Part 1: Audit of Task 1 — Ground Services Coordination (`Mediator`)

**Автор / Author:** Дем'ян Мисів (Demian Mysiv)  
**Рецензент / Reviewer:** Едуард Жалоба (Eduard Zhaloba)

### 1. Відповідність діаграми класів коду / Class Diagram vs. Code Compliance
- **UA:** *(Заповнює Едуард)*
- **EN:** *(To be filled by Eduard)*

### 2. Усунення проблеми з опису ситуації / Problem Resolution
- **UA:** *(Заповнює Едуард)*
- **EN:** *(To be filled by Eduard)*

### 3. Перевірка раннера та демонстраційного сценарію / Runner & Demo Scenario Verification
- **UA:** *(Заповнює Едуард)*
- **EN:** *(To be filled by Eduard)*

### 4. Зауваження та рекомендації / Remarks & Recommendations
- **UA:** *(Заповнює Едуард)*
- **EN:** *(To be filled by Eduard)*

---

## Частина 2: Аудит Завдання 2 — Сповіщення про зміну статусу рейсу (`Observer`)
## Part 2: Audit of Task 2 — Flight Status Change Notification (`Observer`)

**Автор / Author:** Едуард Жалоба (Eduard Zhaloba)  
**Рецензент / Reviewer:** Дем'ян Мисів (Demian Mysiv)

### 1. Відповідність діаграми класів коду / Class Diagram vs. Code Compliance
- **UA:** На момент проведення рев'ю UML-діаграма для Завдання 2 (`task2_observer.puml` / `.png`) ще не додана до теки `docs/diagrams/`. Необхідно додати діаграму, яка відображає суб'єкт `Flight` (Subject), інтерфейс `FlightObserver` (Observer), чотирьох конкретних спостерігачів (`InformationBoard`, `Passenger`, `CateringService`, `TaxiService`) та клієнта `Task2Demo` з відповідними модифікаторами доступу та кратністю зв'язків.
- **EN:** At the time of the review, the UML diagram for Task 2 (`task2_observer.puml` / `.png`) has not yet been added to `docs/diagrams/`. A diagram must be added depicting the `Flight` subject, the `FlightObserver` interface, the four concrete observers (`InformationBoard`, `Passenger`, `CateringService`, `TaxiService`), and the `Task2Demo` client with proper access modifiers and multiplicities.

### 2. Усунення проблеми з опису ситуації / Problem Resolution
- **UA:** Проблему жорсткого зв'язування класу рейсу з конкретними службами повністю усунуто. Клас `Flight` працює виключно через інтерфейс `FlightObserver` і не залежить від конкретних отримувачів. Підписка та відписка відбуваються динамічно під час виконання (`addObserver()`, `removeObserver()`), а кожен спостерігач самостійно визначає реакцію на подію в методі `update()` (зокрема `TaxiService` реагує лише на затримку та зміну виходу). Дотримано принцип Open/Closed: підключення нового отримувача не потребує змін у `Flight`.
- **EN:** Tight coupling between the flight class and concrete notification recipients is fully resolved. `Flight` interacts exclusively through the `FlightObserver` interface without knowing concrete subscriber types. Subscription and unsubscription occur dynamically at runtime (`addObserver()`, `removeObserver()`), and each observer independently defines its reaction in `update()` (for example, `TaxiService` filters for delays and gate changes). The Open/Closed Principle is respected: adding a new subscriber requires no modifications to `Flight`.

### 3. Перевірка раннера та демонстраційного сценарію / Runner & Demo Scenario Verification
- **UA:** `Task2Demo` повністю відтворює всі 3 кроки демонстраційного сценарію для рейсу `PS-101` із дотриманням формату консольного журналу `[час] [джерело] подія`:
  1. Підписано табло (`InformationBoard`), пасажира (`Passenger("Іван")`) та кейтеринг (`CateringService`), виконано `delayFlight("18:45")` — у журналі зафіксовано реакцію кожного.
  2. Підписано `TaxiService` і виконано `changeGate("F3")` — відреагували всі четверо підписників.
  3. Відписано `CateringService` і виконано `startBoarding()` — кейтеринг більше не отримує сповіщень.
- **EN:** `Task2Demo` accurately reproduces all 3 steps of the demonstration scenario for flight `PS-101` following the `[time] [source] event` log format:
  1. Subscribed `InformationBoard`, `Passenger("Іван")`, and `CateringService`, then called `delayFlight("18:45")` — each observer's reaction is logged.
  2. Subscribed `TaxiService` and called `changeGate("F3")` — all four subscribers react.
  3. Unsubscribed `CateringService` and called `startBoarding()` — catering no longer receives notifications.

### 4. Зауваження та рекомендації / Remarks & Recommendations
- **UA:**
  1. **Інтеграція з `Main.java`:** У `Task2Demo` реалізовано власний метод `main(String[] args)` замість статичного `run()`. Для запуску обох завдань з єдиної точки входу (`Main.runSituation2()`) варто додати метод `public static void run()` та підключити його в `Main.java`.
  2. **Фільтрація подій у `TaxiService`:** Перевірка типу події через `eventDescription.contains("затримано")` та `contains("Змінено вихід")` чутлива до змін формулювань. Рекомендується ввести `enum FlightEventType` (наприклад, `DELAYED`, `GATE_CHANGED`, `BOARDING_STARTED`) і передавати його в `update()`.
  3. **Іммутабельність та дублікати:** Поля `flightNumber` і `observers` у `Flight` та `name` у `Passenger` доцільно позначити як `final`. Також у `addObserver()` варто перевіряти наявність підписника (`!observers.contains(observer)`), щоб уникнути повторних сповіщень.
  4. **Діаграма:** Додати вихідний файл і зображення UML-діаграми Завдання 2 у `docs/diagrams/`.
- **EN:**
  1. **Integration with `Main.java`:** `Task2Demo` defines its own `main(String[] args)` instead of a static `run()` method. Adding `public static void run()` and calling it from `Main.runSituation2()` will unify execution under a single entry point.
  2. **Event filtering in `TaxiService`:** Detecting event types via `eventDescription.contains("затримано")` and `contains("Змінено вихід")` is fragile against wording changes. Introducing a `FlightEventType` enum (e.g., `DELAYED`, `GATE_CHANGED`, `BOARDING_STARTED`) and passing it to `update()` is recommended.
  3. **Immutability & duplicate prevention:** Marking `flightNumber` and `observers` in `Flight` and `name` in `Passenger` as `final` improves safety. Checking `!observers.contains(observer)` in `addObserver()` would prevent duplicate notifications.
  4. **Diagram:** Add the Task 2 UML diagram source and image files to `docs/diagrams/`.

---

## Частина 3: Аргументація вибору шаблонів для захисту / Pattern Selection Argumentation

### Завдання 1 — `Mediator` (Дем'ян / Demian)
- **UA:** Шаблон **Mediator** обрано тому, що п'ять наземних служб (`CleaningService`, `CateringService`, `BaggageService`, `TowingService`, `RefuelingService`) мають узгоджувати послідовність робіт і правила безпеки без прямих викликів одна одної. Перенесення всієї логіки координації в єдиний клас `GroundControlCoordinator` (через інтерфейс `GroundServiceMediator`) замінює зв'язки «багато-до-багатьох» на топологію «зірка», повністю ізолює служби та дозволяє додавати нові служби (наприклад, протиобмерзальну обробку) без модифікації існуючих класів.
- **EN:** The **Mediator** pattern was chosen because five ground services (`CleaningService`, `CateringService`, `BaggageService`, `TowingService`, `RefuelingService`) must coordinate their workflow and safety constraints without directly calling one another. Centralizing all coordination rules in `GroundControlCoordinator` (via the `GroundServiceMediator` interface) replaces many-to-many dependencies with a star topology, decouples the services, and allows adding new services (such as de-icing) without modifying existing classes.

### Завдання 2 — `Observer` (Едуард / Eduard)
- **UA:** *(Заповнює Едуард)*
- **EN:** *(To be filled by Eduard)*