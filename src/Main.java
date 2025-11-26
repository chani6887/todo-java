import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskRepository repository = new TaskRepository();
        TaskService service = new TaskService(repository);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Task Manager ===");
            System.out.println("1. Add new task ⭐");
            System.out.println("2. Mark task as DONE ✔");
            System.out.println("3. Search tasks by text 🔍");
            System.out.println("4. Show all tasks sorted by status 🧷");
            System.out.println("5. Delete task 🗑");
            System.out.println("6. Exit ❌");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();

                    Task newTask = new Task(title);
                    newTask.setDescription(description);
                    newTask.setStatus(ProgressStage.NEW);

                    repository.add(newTask);
                    System.out.println("Task added!");
                    break;

                case "2":
                    System.out.print("Enter task ID to mark as DONE: ");
                    int idDone = Integer.parseInt(scanner.nextLine());
                    service.markAsDone(idDone);
                    System.out.println("Task marked as DONE!");
                    break;

                case "3":
                    System.out.print("Enter search text: ");
                    String searchText = scanner.nextLine();
                    List<Task> results = service.searchTasks(searchText);
                    if (results.isEmpty()) {
                        System.out.println("No tasks found matching your search.");
                    } else {
                        System.out.println("Search results:");
                        for (Task t : results) {
                            System.out.println(t);
                        }
                    }
                    break;

                case "4":
                    List<Task> sortedTasks = service.getTasksSortedByStatus();
                    System.out.println("Tasks sorted by status:");
                    for (Task t : sortedTasks) {
                        System.out.println(t);
                    }
                    break;

                case "5":  // delete task
                    System.out.print("Enter task ID to delete: ");
                    int idDelete = Integer.parseInt(scanner.nextLine());
                    repository.delete(idDelete);
                    System.out.println("Task deleted!");
                    break;

                case "6":
                    System.out.println("Exiting...🖐");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
