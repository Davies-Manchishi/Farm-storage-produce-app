# Farm-storage-produce-app
# Farm Produce Storage Manager

An Android app that helps small-scale farmers record, organize, and monitor harvested agricultural produce to reduce losses from poor storage and spoilage.

---

## The Problem

Small-scale farmers often rely on handwritten notes, memory, or no records at all to track harvested produce. Without proper records, they cannot know how much produce is available, when it was harvested, how long it has been stored, or whether some has spoiled. This leads to inaccurate quantities, missed inspections, and avoidable losses.

## The Users

Small-scale farmers and agricultural produce storage managers in Zambia who need a simple way to record and monitor stored crops without internet access.

## One-Sentence Solution

Our app helps small-scale farmers record, organize, and monitor their harvested agricultural produce to improve storage management, track stock quantities, and reduce losses caused by poor record-keeping.

---

## Screens

| Screen | Purpose | Owner |
|--------|---------|-------|
| **Dashboard** | Shows total produce records, available stock summary, and inspection alerts. Navigation hub to all screens. | Member 1 |
| **Add Produce** | Form for registering new harvest: crop name, quantity, unit, harvest date, storage location, notes. Validates all inputs. | Member 2 |
| **Storage Records** | Scrollable list of all produce records. Tap any item to view details. Shows empty state when no records exist. | Member 3 |
| **Produce Details** | Full details of a selected record. Allows editing, updating remaining quantity, and recording sold/used/spoiled produce. | Member 2 |
| **Storage Monitoring** | Shows produce due for inspection, time spent in storage, and allows marking inspections complete. Includes "Check now" button for manual Worker trigger. | Member 4 |

### User Journey

1. Farmer opens app → **Dashboard**
2. Taps "Add Produce" → enters crop name, quantity, unit, harvest date, storage location → taps **Save**
3. App validates input → saves to Room database → returns to Dashboard (record count updates)
4. Farmer taps "View Records" → **Storage Records** list shows the new record
5. Taps the record → **Produce Details** → can update remaining quantity when produce is sold/used/spoiled
6. When inspection is due, farmer opens **Storage Monitoring** → sees due records → can mark inspection complete
7. WorkManager schedules periodic background checks → posts notification when inspection is due

---

## How to Build and Run

### Prerequisites

- Android Studio (latest stable)
- Android SDK (minSdk 24, targetSdk 35)
- An emulator (AVD) or physical Android device with USB debugging enabled

### Steps

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ## Project Structure
app/src/main/java/com/zut/farmstorage/
│
├── MainActivity.kt ← Member 1 (entry point, hosts NavHost)
│
├── navigation/ ← MEMBER 1
│ ├── Routes.kt (route constants)
│ └── AppNavHost.kt (NavHost graph)
│
├── data/ ← MEMBER 3 (Room) + MEMBER 1 (Fake repo)
│ ├── Produce.kt (Room @Entity — frozen contract)
│ ├── ProduceDao.kt (@Dao — frozen contract)
│ ├── AppDatabase.kt (@Database class)
│ ├── ProduceRepository.kt (interface — frozen contract)
│ ├── FakeProduceRepository.kt ← Member 1 (in-memory, for building before Room)
│ └── RoomProduceRepository.kt ← Member 3 (Room implementation)
│
├── ui/
│ ├── dashboard/
│ │ └── DashboardScreen.kt ← Member 1
│ ├── addproduce/
│ │ └── AddProduceScreen.kt ← Member 2
│ ├── records/
│ │ └── RecordsScreen.kt ← Member 3
│ ├── details/
│ │ └── ProduceDetailsScreen.kt ← Member 2
│ ├── monitoring/
│ │ └── MonitoringScreen.kt ← Member 4
│ ├── components/ ← Member 1 (reusable UI widgets)
│ │ └── (shared composables go here)
│ └── theme/ ← Member 1
│ ├── Color.kt
│ ├── Theme.kt
│ └── Type.kt
│
└── worker/ ← MEMBER 4
└── InspectionWorker.kt (WorkManager Worker + notification logic)


### Who Creates What

| File / Folder | Owner | Notes |
|---|---|---|
| `MainActivity.kt` | Member 1 | Hosts `AppNavHost`, wires the repository (Fake → Room swap happens here) |
| `navigation/Routes.kt` | Member 1 | Frozen contract (Block B). Do not change without group agreement. |
| `navigation/AppNavHost.kt` | Member 1 | All screen destinations and navigation callbacks |
| `data/Produce.kt` | Member 3 | Frozen contract (Block A) |
| `data/ProduceDao.kt` | Member 3 | DAO queries — must match Block A types |
| `data/AppDatabase.kt` | Member 3 | Room database class |
| `data/ProduceRepository.kt` | Member 1 + Member 3 | Frozen contract (Block C). Interface only. |
| `data/FakeProduceRepository.kt` | Member 1 | In-memory implementation — lets UI build before Room is ready |
| `data/RoomProduceRepository.kt` | Member 3 | Real implementation backed by Room |
| `ui/dashboard/DashboardScreen.kt` | Member 1 | Observes repository, shows counts + nav buttons |
| `ui/addproduce/AddProduceScreen.kt` | Member 2 | Form + validation + `rememberSaveable` + DatePicker |
| `ui/records/RecordsScreen.kt` | Member 3 | LazyColumn list + empty state |
| `ui/details/ProduceDetailsScreen.kt` | Member 2 | Detail view + edit/update sold/used/spoiled |
| `ui/monitoring/MonitoringScreen.kt` | Member 4 | Inspection list + "Check now" button |
| `ui/components/` | Member 1 | Shared widgets (`LabeledTextField`, etc.) |
| `ui/theme/` | Member 1 | Material 3 theme, colors, typography |
| `worker/InspectionWorker.kt` | Member 4 | WorkManager Worker + notification channel logic |

### Rules Everyone Must Follow

1. **Stay in your folder.** Don't create files outside your assigned folder without telling the group — two people editing one file causes merge conflicts.
2. **Never change the frozen contract files** (`Produce.kt`, `ProduceRepository.kt`, `Routes.kt`) without posting in the group first.
3. **No `startActivity()` for internal navigation.** All navigation goes through `navController` callbacks from `AppNavHost`.
4. **Don't commit** `local.properties`, `.idea/`, `.gradle/`, `build/`, or `*.iml`.
5. **Pull before you push.** If you hit a merge conflict, ask in the group — never force-push.
6. **Commit from your own GitHub account.** The lecturer checks commit history.
7. 
