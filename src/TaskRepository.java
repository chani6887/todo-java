import java.io.*;
import java.util.*;

public class TaskRepository {

    private final File file = new File("tasks.json");
    private List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        load();
    }

    /** Loading tasks from the file */
    private void load() {
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // JSON-like: id|title|description|phase
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

    /** שמירה לקובץ */
    private void save() {
        try (PrintWriter pw = new PrintWriter(file)) {
            for (Task t : tasks) {
                pw.println(t.getId() + "|" + t.getTitle() + "|" + t.getDescription() + "|" + t.getStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- CRUD ---

    public void add(Task task) {
        tasks.add(task);
        save();
    }

    public void update(Task task) {
        delete(task.getId());
        tasks.add(task);
        save();
    }

    public void delete(int id) {
        tasks.removeIf(t -> t.getId() == id);
        save();
    }

    public Task getById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Task> listAll() {
        return new ArrayList<>(tasks);
    }
}
