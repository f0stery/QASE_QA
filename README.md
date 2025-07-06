
# 🏗️ Test Automation Check-List [Qase.io](https://app.qase.io/)

## 🛠️ Technology Stack
| Category              | Technologies Used                     |
|-----------------------|---------------------------------------|
| Test Framework        | TestNG, Selenide, REST Assured        |
| Programming Language  | Java 17+                             |
| Build Tool            | Maven                                |
| CI/CD                 | GitHub Actions                       |
| Reporting             | Allure Reports                       |
| Utilities             | Lombok, Log4j2          |
---

## 🖥 **UI Tests**

### 1. 📂 **Projects Management**
- Create new project
- Search project by name/filters
- Edit project
- Remove project

---

### 2. 🗄 **Project Repository**
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

### 3. 🔄 **Shared Steps**
- Create template steps
- Search shared steps
- Edit template steps
- Delete templates

---

### 4. 📅 **Test Plans**
- Create test plan
- Add cases to plan
- Configure execution parameters
- Delete test plan

---

### 5. 🏃 **Test Runs**
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
### 🐞 **Defect Management**
- `GET` — Get all defects
- `POST` — Create a new defect
- `DELETE` — Delete defect
- `UPDATE` — Update defect

### 🎯 **Milestones Management**
- `GET` — Get all milestones
- `POST` — Create a new milestone
- `DELETE` — Delete milestone
- `UPDATE` — Update milestone  