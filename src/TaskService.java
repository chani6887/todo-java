import java.util.*;
import java.util.stream.Collectors;

public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /** Mark a task as DONE by its ID **/
    public void markAsDone(int id) {
        Task task = repository.getById(id);
        if (task != null) {
            task.setStatus(ProgressStage.DONE);
            repository.update(task);
        }
    }

    /** Search tasks by text appearing in title or description **/
    public List<Task> searchTasks(String text) {
        String lowerText = text.toLowerCase();
        return repository.listAll().stream()
                .filter(t -> t.getTitle().toLowerCase().contains(lowerText) ||
                        t.getDescription().toLowerCase().contains(lowerText))
                .collect(Collectors.toList());
    }

    /** Return all tasks sorted by status (NEW, IN_PROGRESS, DONE) **/
    public List<Task> getTasksSortedByStatus() {
        return repository.listAll().stream()
                .sorted(Comparator.comparing(Task::getStatus))
                .collect(Collectors.toList());
    }
}
