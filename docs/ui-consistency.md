# HRMS Platform — UI Consistency

> Field state (enabled/disabled), control-type behavior, and validation messaging surface across
> the ESS Personal/Contact Details form (see [`business-overview.md`](./business-overview.md)).
> This document covers whether it's represented **consistently** across the form and its
> supporting confirmation/error states.

## Why This Matters More Here Than in Most Modules

Per [`business-overview.md`](./business-overview.md) section 3, this module's central risk is a
field-access-control mismatch — a field that's supposed to be HR-only editable by an employee, or
vice versa. Since every employee in the company fills out this same form, even a small,
inconsistent enabled/disabled treatment scales into a company-wide data-integrity or support-load
problem instantly.

## 1. Field State Representation Consistency

| State | Expected Visual Treatment |
|---|---|
| Employee-editable field | Normal input styling, fully interactive |
| HR-controlled (disabled) field | Visually distinct disabled/greyed styling — never identical in appearance to an editable field |
| Field with a validation error | Clear inline error styling, consistent across all control types (text box, combo box, radio, date picker) |

**Test scenario:** a disabled field must never be styled identically to an enabled one — an
employee should be able to tell at a glance which fields they can and cannot edit, without
attempting to click into each one.

## 2. Save Confirmation & Error Messaging Consistency

- The "details are saved" confirmation message must appear identically worded regardless of
  which field(s) were changed
- Login error messages ("Credentials not valid") must be worded identically across all three
  negative-case combinations (invalid username / invalid password / both) — never a different
  message that inadvertently reveals which part was wrong

## 3. File Upload Validation Messaging Consistency

Per [`business-overview.md`](./business-overview.md) section 4:

- The format-rejection message (unsupported file type) and the size-rejection message (over 1 MB)
  must be distinct and specific — a generic "upload failed" message for both makes it impossible
  for an employee to self-correct
- See [`sample-defect-report.md`](../sample-defect-report.md) Defect #2 for what happens when
  size validation silently fails to enforce the stated limit

## 4. Terminology Consistency

Per the glossary in [`business-overview.md`](./business-overview.md), watch for drift on:

- "ESS" vs. "MyInfo" vs. "Personal Details" used interchangeably for the same module — pick one
  primary term for user-facing copy and use it consistently
- "Disabled" vs. "Read-only" vs. "Locked" as different labels for the same HR-controlled-field
  concept

## 5. Cross-Browser & Responsive Consistency

- Do combo boxes, radio buttons, and the date picker render and behave identically across
  Chrome, Firefox, and Safari/WebKit?
- Does the field-disabled visual treatment remain distinguishable on smaller viewports?

## 6. Accessibility Consistency

- Are enabled/disabled field states distinguishable by more than color/opacity alone (e.g. also
  conveyed via `aria-disabled` and cursor state)?

---

## Coverage Mapping

See [`../regression-checklist.md`](../regression-checklist.md) section 6 for the UI consistency
test cases derived from this document.
