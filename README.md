PROJECT CONCEPT NOTE

FARM PRODUCE STORAGE MANAGER

Project Type: Android Mobile Application
Institution: Zambia University College of Technology
Programme: Bachelor of Software Enginnering
Course: Mobile App Development
Project Team: FarmStorage
Date: November 2026

---

1. Introduction

Agriculture plays an important role in Zambia's economy and provides a source of income and food for many households. However, small-scale farmers and produce storage managers often experience difficulties managing harvested produce after harvesting.

Poor record-keeping, inadequate stock monitoring and failure to conduct regular inspections can contribute to post-harvest losses. These challenges may result in produce being forgotten, quantities being incorrectly recorded, and spoilage going unnoticed.

The Farm Produce Storage Manager is an Android mobile application designed to help small-scale farmers and produce storage managers maintain accurate records of harvested produce, monitor available stock and keep track of produce inspections.

The application is designed to work offline, making it useful in environments where internet connectivity may be unreliable.

2. Problem Statement

Many small-scale farmers and produce storage managers rely on notebooks or manual methods to record harvested and stored agricultural produce.

These methods can make it difficult to:

- Maintain accurate records of harvested quantities.
- Monitor the remaining quantity of stored produce.
- Track produce that has been sold, used or spoiled.
- Remember when produce was last inspected.
- Identify produce that requires attention.

As a result, farmers and storage managers may experience poor stock management, inaccurate records and avoidable post-harvest losses.

There is therefore a need for a simple digital solution that makes produce record-keeping and stock monitoring easier and more reliable.

3. Aim of the Project

The aim of this project is to develop an offline Android application that helps small-scale farmers and produce storage managers record harvested produce, monitor stock levels and manage produce inspections to improve storage record-keeping and reduce avoidable post-harvest losses.

4. Project Objectives

The specific objectives are to:

1. Develop an Android application that allows users to record harvested agricultural produce.
2. Provide a dashboard that displays an overview of stored produce.
3. Enable users to view detailed information about individual produce records.
4. Allow users to update remaining quantities when produce is sold, used or spoiled.
5. Provide a records section for viewing previously stored produce.
6. Implement monitoring and notification features to remind users about produce inspections.
7. Store application data locally so that the system can operate without an internet connection.

5. Proposed Solution

The proposed solution is the Farm Produce Storage Manager, an offline Android application developed using Kotlin and Jetpack Compose.

The application will allow users to enter information about harvested produce, including the crop name, quantity, unit of measurement, harvest date, storage location and additional notes.

The information will be stored locally using Room Database. Users will be able to access their records, view produce details and update remaining quantities as produce is sold, used or spoiled.

The monitoring component will use scheduled background tasks through WorkManager to support inspection reminders and notifications.

The application aims to provide a simple and accessible way of managing stored agricultural produce without depending on an internet connection.

6. Key Features of the Application

6.1 Dashboard

Displays an overview of the user's stored produce and provides access to the main application sections.

6.2 Add Produce

Allows users to enter new produce records, including:

- Crop name.
- Harvested quantity.
- Unit of measurement (kilograms, bags or tonnes).
- Harvest date.
- Storage location.
- Additional notes.

6.3 Produce Records

Displays saved produce records, allowing users to view and access previously entered information.

6.4 Produce Details

Displays detailed information about a selected produce record, including its original quantity, remaining quantity, harvest date and storage location.

6.5 Stock Management

Allows users to update remaining quantities when produce is:

- Sold.
- Used.
- Spoiled.

6.6 Monitoring and Notifications

Supports regular produce inspections and provides reminders through background tasks and notifications.

6.7 Offline Data Storage

Uses a local database to store produce information, allowing users to access their records without an internet connection.

7. Scope of the Project

The project focuses on developing an Android application for recording and managing harvested agricultural produce.

The application covers:

- Recording harvested produce.
- Viewing stored produce records.
- Monitoring remaining stock.
- Updating quantities after sales, usage or spoilage.
- Tracking inspection information.
- Providing inspection reminders.
- Local data storage and offline access.

The current project does not include online synchronisation, financial accounting, direct market sales integration or automated prediction of crop spoilage.

8. Technologies and Tools

The following technologies and tools will be used in the development of the application:

Technology / Tool| Purpose
Kotlin| Main programming language
Android Studio| Application development environment
Jetpack Compose| Building the user interface
Room Database| Local data storage
WorkManager| Background tasks and inspection reminders
Material 3| User interface components and styling
Git and GitHub| Version control and team collaboration

9. Methodology

The project will follow an iterative software development approach. Development will be divided into stages to allow the team to build, test and improve the application.

Stage 1: Requirements Gathering

Identify the challenges associated with manual produce record-keeping and establish the application's functional requirements.

Stage 2: System Design

Design the application structure, user interface, navigation flow and database structure.

Stage 3: Implementation

Develop the application using Kotlin, Jetpack Compose, Room Database and WorkManager.

Stage 4: Integration

Combine the different components developed by team members into one working application.

Stage 5: Testing

Test the application to ensure that produce records can be added, viewed, updated and stored correctly. Also test navigation, database operations and notifications.

Stage 6: Documentation and Presentation

Prepare the project documentation, screenshots and demonstration video for submission.

10. Expected Outcomes

At the end of the project, the team expects to deliver:

1. A functional Android application for managing stored agricultural produce.
2. A simple interface for recording harvested produce.
3. A local database for storing and retrieving produce records.
4. Functionality for tracking remaining quantities.
5. Monitoring and inspection reminder functionality.
6. An application that can operate without an internet connection.
7. Project documentation and a demonstration of the completed application.

11. Significance of the Project

The Farm Produce Storage Manager is expected to benefit small-scale farmers and produce storage managers by:

- Improving produce record-keeping.
- Making stock information easier to access.
- Helping users monitor stored produce.
- Making it easier to track sold, used and spoiled quantities.
- Encouraging regular produce inspections.
- Reducing dependence on handwritten records.

The project also provides the development team with practical experience in Android application development, database management, software testing and collaborative software engineering.

12. Conclusion

The Farm Produce Storage Manager aims to address common challenges associated with manual agricultural produce record-keeping and storage management.

By providing an offline digital platform for recording harvested produce, monitoring stock and supporting regular inspections, the application seeks to improve record accuracy and help reduce avoidable post-harvest losses.

The project demonstrates how mobile technology can be applied to address practical challenges in the agricultural sector in Zambia.

---

13. Project Team Members

No.| Full Name| Student ID
1| Tatenda Mungandaire| 2420986
2| Tafadzwa Mungandaire| 2420974
3| Davis Manchishi| 2410093
4| David Chisenga| 2410384
5| [Insert fifth member]| [Insert student ID]

End of Concept Note
