# Sample Defect Report — HRMS Platform (ESS)

> Template + worked examples using dummy data. Reflects defect themes found in ESS/MyInfo
> testing: field-access-control errors and file upload validation gaps — see
> [`docs/business-overview.md`](./docs/business-overview.md) section 3 for why, and
> [`docs/README.md`](./docs/README.md) for the full documentation map.

## Defect Theme Taxonomy

- Field access-control errors (HR-only field editable, or employee field wrongly disabled)
- GUI control-type misbehavior (combo box allowing multi-select, etc.)
- File upload validation gaps (format/size)
- Save/persistence issues (change not actually saved despite confirmation)
- Login error-message inconsistency

**Severity categories used:** Minor, Major, Critical, Blocker.

---

## Defect #1

| Field | Value |
|---|---|
| **ID** | BUG-HRM-7021 (sample) |
| **Title** | Date of Birth field is editable by the ESS employee, contrary to the field-access-control design |
| **Severity** | Critical |
| **Module** | ESS → Personal Details |
| **Environment** | UAT (dummy data) |

**Steps to Reproduce**
1. Login as a dummy ESS user
2. Navigate to the Personal/Contact Details form
3. Attempt to edit the Date of Birth field

**Expected Result**
Date of Birth should be **disabled** for the ESS employee — it's an HR-controlled,
system-of-record field (per TC_MYINFO_PERSDETAILS_01).

**Actual Result**
The field is editable, and a change to it is saved successfully without any HR approval step.

**Impact**
An employee could alter their own official date of birth, which has downstream implications for
payroll, compliance, and any age-dependent policy logic — a data-integrity issue, not just a
cosmetic one.

**Suggested Fix**
Enforce the field-access-control list at the form-rendering layer (and ideally also at the
save/API layer as defense-in-depth), consistent with the enabled/disabled matrix documented in
`docs/business-overview.md`.

---

## Defect #2

| Field | Value |
|---|---|
| **ID** | BUG-HRM-7038 (sample) |
| **Title** | Profile picture upload accepts a file over the 1 MB limit |
| **Severity** | Major |
| **Module** | ESS → Personal Details → Attachment |
| **Environment** | UAT (dummy data) |

**Steps to Reproduce**
1. Login as a dummy ESS user
2. Go to Contact Details → attachment section
3. Upload a dummy image file just over 1 MB (per TC_MYINFO_PERSDETAILS_06)

**Expected Result**
The upload should be rejected with a clear file-size error.

**Actual Result**
The file uploads successfully — the size check only validates the file extension, not the actual
file size.

**Impact**
Unchecked large file uploads can degrade page load performance across the ESS module and
increase storage costs at scale, especially across a large employee base.

**Suggested Fix**
Add an explicit file-size check (both client-side for fast feedback and server-side as the
authoritative check) before accepting the upload.

---

## Defect Reporting Template (blank)

| Field | Value |
|---|---|
| **ID** | |
| **Title** | |
| **Severity** | Minor / Major / Critical / Blocker |
| **Module** | |
| **Environment** | |

**Steps to Reproduce**
1.
2.
3.

**Expected Result**


**Actual Result**


**Impact**


**Suggested Fix**

