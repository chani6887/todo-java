import java.io.*;
import java.util.*;

public class TaskRepository {

    private final File file = new File("tasks.json");

    // Map to hold tasks in memory: ID -> Task
    private Map<Integer, Task> taskMap = new HashMap<>();

    public TaskRepository() {
        load();
    }

    /** Loading tasks from JSON file manually */
    private void load() {
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }
            String content = sb.toString();

            if (content.startsWith("[") && content.endsWith("]")) {
                content = content.substring(1, content.length() - 1);
            }

            if (content.isEmpty()) return;

            String[] objects = content.split("\\},\\{");

            for (String obj : objects) {
                obj = obj.replace("{", "").replace("}", "");
                Map<String, String> fields = new HashMap<>();

                String[] pairs = obj.split(",");
                for (String pair : pairs) {
                    String[] kv = pair.split(":", 2);
                    if (kv.length == 2) {
                        String key = kv[0].trim().replace("\"", "");
                        String value = kv[1].trim().replace("\"", "");
                        fields.put(key, value);
                    }
                }

                Task task = new Task(fields.get("title"));
                task.setDescription(fields.get("description"));
                task.setStatus(ProgressStage.valueOf(fields.get("status")));
                task.setId(Integer.parseInt(fields.get("id")));

                taskMap.put(task.getId(), task);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Saving tasks to JSON file manually */
    private void save() {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println("[");
            List<Task> tasks = new ArrayList<>(taskMap.values());
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                pw.print("  {");
                pw.print("\"id\":\"" + t.getId() + "\",");
                pw.print("\"title\":\"" + t.getTitle() + "\",");
                pw.print("\"description\":\"" + t.getDescription() + "\",");
                pw.print("\"status\":\"" + t.getStatus() + "\"");
                pw.print("}");
                if (i < tasks.size() - 1) pw.println(",");
                else pw.println();
            }
            pw.println("]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- CRUD operations using Map ---

    public void add(Task task) {
        taskMap.put(task.getId(), task);
        save();
    }

    public void update(Task task) {
        taskMap.put(task.getId(), task);
        save();
    }

    public void delete(int id) {
        taskMap.remove(id);
        save();
    }

    public Task getById(int id) {
        return taskMap.get(id);
    }

    public List<Task> listAll() {
        return new ArrayList<>(taskMap.values());
    }
}
