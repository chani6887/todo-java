Task Manager Project
A simple Task Manager application in Java to add, update, delete, search, and list tasks.

UI:
Add a new task (title + description)

Mark task as DONE

Delete task by ID

Search tasks by text

List tasks sorted by status

File Structure
src/
  Task.java
  ProgressStage.java
  TaskRepository.java
  TaskService.java
  Main.java
tasks.json

How to Run
javac src/*.java
java -cp src Main

Additional Notes

All JSON handling is done manually, without using any external libraries.

The code focuses on functionality, not on UI design.

Actions such as adding, updating, and deleting tasks automatically save the changes to the file.
