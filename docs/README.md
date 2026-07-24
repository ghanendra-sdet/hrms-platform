# HRMS Platform — Documentation Map

> New to this repo? Start here. This page answers the questions a tech-curious QA/SDET would
> actually ask, and points to exactly the doc that answers each one.

| Question | Answer |
|---|---|
| What is this, in plain terms? | [`business-overview.md`](./business-overview.md) sections 1–2 |
| What's the field-access-control model? | [`business-overview.md`](./business-overview.md) section 3 |
| What GUI control types are involved? | [`business-overview.md`](./business-overview.md) section 4 |
| Who's involved / stakeholders? | [`business-overview.md`](./business-overview.md) section 6 |
| What does it depend on? | [`business-overview.md`](./business-overview.md) section 7 |
| What does the UI need to get right, consistently? | [`ui-consistency.md`](./ui-consistency.md) |
| What's tested? | [`../regression-checklist.md`](../regression-checklist.md) |
| What's automated? | [`../automation/README.md`](../automation/README.md) |
| What does a real-looking defect report look like? | [`../sample-defect-report.md`](../sample-defect-report.md) |
| What does a regression execution report look like? | [`../regression-execution-summary.md`](../regression-execution-summary.md) |

## Business Flow vs. Tech Flow vs. User Flow

- **Business Flow** — why some fields are HR-controlled and others employee-editable: system-of-
  record fields (DOB, Employee ID) feed payroll/compliance and must stay accurate and
  HR-gated, while low-risk personal fields (nickname, marital status) can be safely
  self-service. See [`business-overview.md`](./business-overview.md) sections 1 and 3.
- **Tech Flow** — how the field-access-control list is (and should be) enforced at both the
  form-rendering layer and the save/API layer, per the PIM system of record. See
  [`business-overview.md`](./business-overview.md) section 7.
- **User Flow** — what an employee actually clicks through: Login → Personal Details form loads
  with the correct per-field enabled/disabled state → edit permitted fields → Save → confirmation
  message. See [`regression-checklist.md`](../regression-checklist.md) sections 1–4.

## Reading Order

```
README.md (repo root)
      │
      ▼
docs/business-overview.md   ← ESS/MyInfo overview, field-access-control model, stakeholders, dependencies
      │
      ▼
docs/ui-consistency.md      ← field-state, messaging, and control-type UI consistency
      │
      ▼
regression-checklist.md     ← full field-by-field test suite (login, GUI state, behavior, upload)
      │
      ▼
sample-defect-report.md → regression-execution-summary.md → automation/README.md
```
