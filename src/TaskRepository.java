import java.io.*;
import java.util.*;

public class TaskRepository {

    // File where tasks are stored
    private final File file = new File("tasks.json");

    // List to hold tasks in memory
    private List<Task> tasks = new ArrayList<>();

    // Constructor: load tasks when repository is created
    public TaskRepository() {
        load();
    }

    /** Loading tasks from the file */
    private void load() {
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Simple JSON-like format: id|title|description|phase/status
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Task task = new Task(parts[1]); // title
                    task.setDescription(parts[2]);
                    task.setStatus(ProgressStage.valueOf(parts[3]));
                    task.setId(Integer.parseInt(parts[0]));
                    tasks.add(task);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Saving tasks to the file */
    private void save() {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Task t : tasks) {
                pw.println(t.getId() + "|" + t.getTitle() + "|" + t.getDescription() + "|" + t.getStatus());
                // NOTE: same as above – make sure the getter name matches the enum field
                // In previous code: t.getPhase() instead of t.getStatus()
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- CRUD operations ---

    // Add a task
    public void add(Task task) {
        tasks.add(task);
        save();
    }

    // Update a task: remove old one by ID, add the new one
    public void update(Task task) {
        delete(task.getId());
        tasks.add(task);
        save();
    }

    // Delete a task by ID
    public void delete(int id) {
        tasks.removeIf(t -> t.getId() == id);
        save();
    }

    // Retrieve a task by ID
    public Task getById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Get a copy of all tasks
    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }
}
