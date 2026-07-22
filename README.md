# 👥 HRMS Platform

**A Human Resource Management System with Employee Self-Service (ESS) — QA & Automation Portfolio Project**

> This repository documents the QA strategy, manual and automated test coverage, and testing
> approach applied to an **HRMS platform**, with a focus on the **Employee Self-Service (ESS /
> MyInfo)** module — the portal employees use to log in and manage their own personal details.
>
> All content here uses **generic/sample data only**. No client names, company names, or
> confidential/production information are included. Dates and timelines are placeholders —
> update `[Timeline]` before publishing.

---

## 📖 Table of Contents

1. [What is an HRMS / ESS Module?](#-what-is-an-hrms--ess-module)
2. [My Role](#-my-role)
3. [Tech Stack & Tools Used](#-tech-stack--tools-used)
4. [Types of Testing Performed](#-types-of-testing-performed)
5. [Key Achievements](#-key-achievements)
6. [Automation Approach](#-automation-approach)
7. [Regression Checklist](#-regression-checklist)
8. [Screenshots & Reports](#-screenshots--reports)
9. [Repository Structure](#-repository-structure)

---

## 💡 What is an HRMS / ESS Module?

A **Human Resource Management System (HRMS)** streamlines core HR operations — payroll
compliance, attendance, leave, recruitment, performance management, onboarding, and biometric
integration — under one platform with role-based access control.

The **Employee Self-Service (ESS)** module — sometimes called "MyInfo" — is the employee-facing
slice of that system: it's where an individual employee logs in and manages their own personal
and contact details, separate from what HR/Admin manages on their behalf. Because ESS is
employee-facing and touches personally identifiable information, it has a distinct QA emphasis
compared to admin-side HRMS modules:

- **Strict field-level access control** — some fields (e.g. Employee ID, Date of Birth, Driver's
  License Number) are typically HR-managed and shown as read-only/disabled to the employee, while
  others (e.g. Nickname, Marital Status) are employee-editable
- **GUI element behavior correctness** — text boxes, combo boxes, radio buttons, and date pickers
  must each behave exactly as their control type implies, since this form is filled out
  repeatedly by every employee in the company
- **File upload validation** — profile picture uploads need both format and size validation

### Who typically interacts with it?

| Role | What they do |
|---|---|
| **Employee (ESS user)** | Logs in, views and updates their own permitted personal/contact details |
| **HR Admin** | Creates ESS user accounts, manages the fields employees cannot self-edit |

---

## 👤 My Role

QA Engineer responsible for manual functional, GUI, and data-validation testing of the HRMS ESS
module, with API-level validation support.

- Designed and executed **manual test cases** covering functional, regression, smoke, and sanity
  testing for HRMS modules, achieving a 90%+ test case pass rate before UAT
- Conducted **manual API testing** using Postman and SQL database validation, identifying 30%+
  of critical defects pre-release
- Validated **field-level GUI behavior** — enabled/disabled state, text input, combo box
  single-select behavior, radio button exclusivity, and date-picker correctness — for every
  field on the ESS Personal/Contact Details form
- Validated **file upload constraints** (accepted formats, size limits) for profile picture
  uploads
- Raised and managed defects in JIRA, ensuring **zero critical defects escaped to production**
  across payroll and compliance-adjacent modules

**Timeline:** `[Add Duration]`

---

## 🛠 Tech Stack & Tools Used

| Category | Tools |
|---|---|
| **Manual Testing** | Functional, GUI, Database/Data-validation testing |
| **API Testing** | Postman, SQL (direct database validation) |
| **UI Automation** | Selenium WebDriver, Java, TestNG |
| **Build Tool** | Maven |
| **Bug Tracking** | JIRA |
| **Version Control** | Git, GitHub |

---

## 🧪 Types of Testing Performed

- **Functional Testing** — login, personal/contact details save and update flows
- **GUI Testing** — field enabled/disabled state, control-type behavior (text box, combo box,
  radio button, date picker)
- **Database/Data-Level Testing** — validating that saved changes are correctly persisted
- **File Upload Validation** — format and size constraint testing
- **API Testing** — via Postman with SQL-backed validation
- **Regression Testing** / **Smoke & Sanity Testing**

---

## 🏆 Key Achievements

- Achieved a **90%+ test case pass rate** before UAT across HRMS modules
- Identified **30%+ of critical defects pre-release** through manual API testing combined with
  direct SQL database validation
- Ensured **zero critical defects escaped to production** across payroll and compliance-adjacent
  modules
- Built a detailed, field-by-field GUI validation suite for the ESS Personal/Contact Details
  form — covering every control type (text, combo, radio, date, file upload) rather than
  spot-checking a subset

---

## 🤖 Automation Approach

Automation is built with **Selenium WebDriver + Java + TestNG**, targeting the highest-priority
ESS flows (login and personal details save) as a complement to the broader manual regression
suite.

### Priority Automated Scenarios

1. Login — valid credentials
2. Login — invalid credentials (negative cases)
3. Personal Details — field enabled/disabled state verification
4. Personal Details — save/update confirmation

See [`automation/`](./automation) for the framework README and a sample spec file using dummy
data.

---

## ✅ Regression Checklist

- [ ] Login — valid credentials
- [ ] Login — invalid username / invalid password / both invalid
- [ ] Personal Details — GUI element enabled/disabled state
- [ ] Personal Details — GUI element behavior (text box / combo box / radio button / date picker)
- [ ] Personal Details — save/update persistence
- [ ] Profile Picture Upload — valid format (jpg/png/gif)
- [ ] Profile Picture Upload — size under limit
- [ ] Profile Picture Upload — size over limit (rejection)

Full checklist with edge cases available in [`test-cases/`](./test-cases).

---

## 📸 Screenshots & Reports

Sample test execution reports and defect report templates are available under
[`test-reports/`](./test-reports) and [`bug-reports/`](./bug-reports).

---

## 📁 Repository Structure

```
hrms-platform/
├── README.md
├── docs/
│   └── business-overview.md      → What HRMS/ESS is, glossary, field-access-control model
├── test-cases/
│   └── regression-checklist.md   → Full ESS Login + Personal Details test suite
├── automation/
│   ├── README.md                 → Framework setup & structure
│   └── SampleEssLoginTest.java   → Sample Selenium + TestNG test (dummy data)
├── bug-reports/
│   └── sample-defect-report.md   → Defect report template with dummy example
└── test-reports/
    └── regression-execution-summary.md → Sample regression test execution report
```
