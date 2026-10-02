# HRMS Platform — Business Overview

> **Start here if you're new to QA, in HR, or from a non-QA technical role.** This document
> explains the HRMS/ESS module and its field-access model before you look at any test case.

## 1. What problem does it solve?

Companies need a system where employees can view and manage some of their own information (a
nickname, marital status, contact preferences) without needing HR to make every small update —
while other, more sensitive or system-of-record fields (Employee ID, Date of Birth) stay
HR-controlled. The ESS/MyInfo module is that self-service layer.

## 2. Core Modules and Their Submodules

| Module | Submodules / Key Components | Responsible For | Primarily Tested Via |
|---|---|---|---|
| **Login / Authentication** | Credential Validation · Negative-Case Error Messaging | ESS user credential validation — see [`architecture-and-flow.md`](./architecture-and-flow.md) for how a session leads into the rest of the flow | UI Automation + Functional Testing |
| **Personal / Contact Details (MyInfo)** | Field Rendering (per-role editable/disabled state) · Save/Persistence · Field-Level Write Authorization | The employee's own editable and view-only profile fields — split into *rendering* and *write authorization* deliberately, since section 4 below shows they're different checks that can fail independently | GUI Testing + API Testing |
| **File Upload (Profile Picture)** | Format Validation · Size Validation | Accepting a profile picture only within accepted formats and size limit — see [`architecture-and-flow.md`](./architecture-and-flow.md) section 4 for why format and size need to be validated as two genuinely separate checks | Functional Testing |

**Why "Field-Level Write Authorization" is called out as its own submodule, not folded into
"Save/Persistence":** per [`sample-defect-report.md`](../sample-defect-report.md) Defect #1, a
save endpoint can correctly persist data (the save genuinely works) while still authorizing the
*wrong set of fields* to be written — those are two different correctness properties, and
treating them as one blurs exactly the distinction that defect depended on.

## 3. The Field-Access-Control Model

Not every field on the Personal Details form behaves the same way:

| Field | Access |
|---|---|
| Full Name, Middle Name, Last Name | Employee-editable (text) |
| Employee ID | HR-controlled (disabled to employee) |
| Other ID | Employee-editable (text) |
| Driver's License Number | HR-controlled (disabled to employee) |
| License Expiry Date | Employee-editable (date picker) |
| Gender | Employee-editable (radio button) |
| Nationality | Employee-editable (combo box) |
| Marital Status | Employee-editable (combo box) |
| Date of Birth | HR-controlled (disabled to employee) |
| Nick Name | Employee-editable (text) |
| Smoker | Employee-editable |
| Military Service | Employee-editable (text) |

**Why this matters for testing:** a field that's supposed to be HR-only but is accidentally left
editable is a data-integrity risk (an employee could alter their own official DOB or ID), while
a field that's supposed to be employee-editable but is stuck disabled is a usability regression
that generates HR support tickets. Both directions need explicit test coverage — not just "does
the form load."

## 4. GUI Control Types on This Form

| Control Type | Fields | Correct Behavior |
|---|---|---|
| Text Box | Full Name, Middle Name, Last Name, Other ID, Nick Name, Military Service | Accepts free text input |
| Combo Box | Marital Status, Nationality | Displays a list of items; allows exactly one selection at a time |
| Radio Button | Gender | Allows exactly one option selected at a time |
| Date Picker | License Expiry Date | Selected date populates the associated text field exactly |
| File Upload | Profile Picture | Accepts jpg/png/gif; enforces a size limit (e.g. under 1 MB) |

## 5. Glossary

| Term | Meaning |
|---|---|
| **ESS** | Employee Self-Service — the employee-facing portal |
| **MyInfo** | Common name for the ESS personal-details module |
| **PIM** | Personnel Information Management — the underlying employee data model |
| **Disabled field** | A form field the current user cannot edit, typically HR/Admin-managed |
| **BOPLA (Broken Object Property Level Authorization)** | OWASP API3:2023's name for an API that authorizes access to an *object* but not to specific *properties* on it — consolidates what used to be called "Mass Assignment." This is the exact vulnerability class behind [`sample-defect-report.md`](../sample-defect-report.md) Defect #1 |
| **PF / ESI / TDS** | Provident Fund / Employee State Insurance / Tax Deducted at Source — the statutory payroll-compliance calculations that consume HR-controlled system-of-record fields (DOB, Employee ID) from PIM, which is why those fields can't be employee-editable |

## 6. Stakeholders / Involved Parties

| Stakeholder | Role in this module |
|---|---|
| **Employee (ESS user)** | Logs in, views and updates their own permitted personal/contact details |
| **HR Admin** | Creates ESS user accounts, manages HR-controlled fields employees cannot self-edit |
| **Payroll Team** | Consumes system-of-record fields (DOB, Employee ID) for payroll and compliance processing — the reason those fields must stay HR-controlled |
| **IT/Platform Admin** | Manages ESS access provisioning and system configuration |
| **QA Team** | Maintains field-by-field GUI validation coverage as the core quality bar for this form |

## 7. Dependencies

### Internal Platform Dependencies

- **PIM (Personnel Information Management)** — the underlying employee data store ESS reads from
  and writes to; the field-access-control model (section 3) is ultimately enforced against this
  system of record
- **Authentication Service** — ESS login credential validation
- **File Storage Service** — profile picture uploads, with format/size validation (section 4)

**Testing implication:** because HR-controlled fields (Employee ID, Date of Birth, Driver's
License Number) feed directly into payroll and compliance processing, an access-control defect
here (see [`sample-defect-report.md`](../sample-defect-report.md) Defect #1) isn't just a UI bug —
it's a data-integrity risk with downstream payroll/compliance impact, which is why field-access
enforcement is tested at both the form-rendering and save/API layers rather than the UI alone.

See [`regression-checklist.md`](../regression-checklist.md) for the full test suite covering
login and every field/control type described above.
