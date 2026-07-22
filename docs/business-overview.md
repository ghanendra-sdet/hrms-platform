# HRMS Platform — Business Overview

> **Start here if you're new to QA, in HR, or from a non-QA technical role.** This document
> explains the HRMS/ESS module and its field-access model before you look at any test case.

## 1. What problem does it solve?

Companies need a system where employees can view and manage some of their own information (a
nickname, marital status, contact preferences) without needing HR to make every small update —
while other, more sensitive or system-of-record fields (Employee ID, Date of Birth) stay
HR-controlled. The ESS/MyInfo module is that self-service layer.

## 2. Core Modules

- **Login / Authentication** — ESS user credential validation
- **Personal / Contact Details (MyInfo)** — the employee's own editable and view-only profile
  fields, including a profile picture upload

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

See [`test-cases/regression-checklist.md`](../test-cases/regression-checklist.md) for the full
test suite covering login and every field/control type described above.
