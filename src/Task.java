public class Task {
    private int id;
    private String title;
    private String description;
    private ProgressStage status;

    // Constructor
    public Task(){

    }
    public Task(String title) {
        this.title = title;
        this.status = ProgressStage.NEW;
    }
    public Task(int id, String title, String description, ProgressStage status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProgressStage getStatus() {
        return status;
    }

    public void setStatus(ProgressStage status) {
        this.status = status;
    }

    //
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}