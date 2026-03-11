# 🏗️ Test Automation Framework for [Qase.io](https://app.qase.io/)


# [![Tests](https://github.com/f0stery/QASE_QA/actions/workflows/QASE.yml/badge.svg)](https://github.com/f0stery/QASE_QA/actions/workflows/QASE.yml)

## Project Goal
A demonstration project for test automation of key scenarios for the Qase.io web application using modern Java tools. The project is structured for easy maintenance and scalability.

## Technology Stack
| Category              | Technologies Used                     |
|-----------------------|---------------------------------------|
| Test Framework        | TestNG, Selenide, REST Assured        |
| Programming Language  | Java 17                               |
| Build Tool            | Maven                                 |
| CI/CD                 | GitHub Actions                        |
| Reporting             | Allure Reports                        |
| Utilities             | Lombok, Log4j2                        |

##  Key Features
*   **Page Object & Page Factory:** UI tests are built using the Page Object pattern to minimize code duplication and simplify maintenance when the UI changes.
*   **Data-Driven Approach:** Test data is externalized into property files, separating it from test logic for better flexibility.
*   **Logging & Reporting:** Detailed logging with Log4j2. Comprehensive Allure reports with attachments (screenshots of failures, request logs) are generated after each test run.

##  Project Structure

```
QASE_QA/
├── .github/
│   └── workflows/                          # Конфигурация GitHub Actions для CI/CD
├── src/
│   ├── test/
│   │   └── java/
│   │       └── [your-base-package]/
│   │           ├── adapters/               # Слой для взаимодействия с API (клиенты, адаптеры)
│   │           ├── models/                 # POJO-классы для данных (тест-кейсы, дефекты и т.д.)
│   │           ├── pages/                  # Page Object'ы для UI страниц
│   │           ├── steps/                  # Бизнес-шаги (логин, создание проекта и т.д.)
│   │           ├── tests/                  # Тестовые классы, разбитые по логике (api, ui)
│   │           │   └── api/
│   │           │   └── ui/
│   │           ├── utils/                  # Утилиты (парсеры, хелперы, PropertyReader)
│   │           └── wrappers/               # Обёртки над Selenide-элементами (кастомные компоненты)
│   │
│   └── resources/                          # Ресурсы для тестов
│           ├── allure.properties           # Настройки Allure Reports
│           ├── api-suite.xml               # TestNG сьют для API тестов
│           ├── config.properties           # Конфигурационные данные (URL, логины)
│           ├── full-suite.xml              # TestNG сьют для всех тестов
│           ├── log4j2-test.yaml            # Конфигурация логирования Log4j2
│           └── ui-suite.xml                # TestNG сьют для UI тестов
├── pom.xml                                 # Файл сборки Maven и управления зависимостями
└── README.md                               # Документация проекта
```
##  Getting Started

### Prerequisites
*   Java 17 (or higher)
*   Maven
*   Git

### Installation & Test Execution
1.  **Clone the repository:**
    ```bash
    git clone https://github.com/f0stery/QASE_QA.git
2.  **Navigate to the project directory:**
    ```bash
    cd QASE_QA
3. **Run all tests:**
    ```bash
    cd mvn clean test
4. **Run UI tests in a specific browser (e.g., Chrome)**
    ```bash
   mvn clean test -Dbrowser=chrome
5. **Generate and open the Allure report:**
    ```bash
    mvn allure:serve
   
# Test Coverage

## 🖥 **UI Tests**

### 1. **Projects Management**
- Create new project
- Search project by name/filters
- Edit project
- Remove project

---

### 2. **Project Repository**
#### Test Suites
- Create new suite
- Search suites
- Edit suite
- Remove suite

#### Test Cases
- Create new test case
- Edit test case steps
- Delete test case
- Assign tags to test cases

---

### 3. **Shared Steps**
- Create template steps
- Search shared steps
- Edit template steps
- Delete templates

---

### 4. **Test Plans**
- Create test plan
- Add cases to plan
- Configure execution parameters
- Delete test plan

---

### 5. **Test Runs**
- Start new test run
- Assign executors
- Execute test cases:
    - Pass/Fail statuses
    - Add comments
- Report defects
- Complete test run
- Export results

---

## ⚙️ **API Tests**
### **Defect Management**
- `GET` — Get all defects
- `POST` — Create a new defect
- `DELETE` — Delete defect
- `UPDATE` — Update defect

### **Milestones Management**
- `GET` — Get all milestones
- `POST` — Create a new milestone
- `DELETE` — Delete milestone
- `UPDATE` — Update milestone  