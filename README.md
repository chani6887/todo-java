Task Manager Project
Overview

A simple Task Manager written in Java (standard libraries only).
Allows users to add, update, delete, mark as done, search, and list tasks sorted by status.
Tasks are stored manually in a JSON-like file (tasks.json).

Features

Add new tasks (title + description).

Mark tasks as DONE.

Delete tasks by ID.

Search tasks by text in title or description.

List all tasks sorted by status (NEW, IN_PROGRESS, DONE).

Stores tasks persistently in a JSON-like file.

Uses Map internally for fast access by task ID.

File Structure
todo-app/
│
├─ src/
│  ├─ Task.java
│  ├─ ProgressStage.java
│  ├─ TaskRepository.java
│  ├─ TaskService.java
│  └─ Main.java
│
└─ tasks.json

How to Run

Compile all Java files:

javac src/*.java


Run the program:

java -cp src Main


Follow menu options:

1: Add new task
2: Mark task as DONE
3: Search tasks by text
4: Show all tasks sorted by status
5: Delete task
6: Exit

Task JSON Format
[
  {"id":"0","title":"Submit claim","description":"Submit claim","status":"NEW"},
  {"id":"1","title":"Review policy","description":"Check apps","status":"IN_PROGRESS"}
]


Handled manually (no external libraries).

Adding, updating, deleting tasks automatically saves the file.

Notes

JSON is managed manually, no external libraries.

Code focuses on functionality, not UI design.

All changes are automatically saved.

How to Run
javac src/*.java
java -cp src Main

Additional Notes

All JSON handling is done manually, without using any external libraries.

The code focuses on functionality, not on UI design.

Actions such as adding, updating, and deleting tasks automatically save the changes to the file.
