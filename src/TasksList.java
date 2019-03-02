import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TasksList {
    List<Task> tasksList = new ArrayList<>();

    public void add(Task task) {
        tasksList.add(task);
    }

    public void createNewTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task name: ");
        String taskName = scanner.nextLine();
        System.out.println("Enter task description: ");
        String taskDesc = scanner.nextLine();
        System.out.println("Enter task due date and time on the using one of given formats:");
        System.out.println("yyyy-MM-dd HH:mm, dd.MM.yyyy HH:mm");
        String dueDatePattern1 = "yyyy-MM-dd HH:mm";
        String dueDatePattern2 = "dd.MM.yyyy HH:mm";

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(dueDatePattern1);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(dueDatePattern2);
        LocalDateTime localDateTime = null;
        while(localDateTime == null) {
            String taskDueDateTime = scanner.nextLine();
            try {
               localDateTime = LocalDateTime.parse(taskDueDateTime, dateTimeFormatter1);
            } catch (DateTimeParseException e){
                try {
                localDateTime = LocalDateTime.parse(taskDueDateTime, dateTimeFormatter2);
                } catch (DateTimeParseException e2) {
                    System.out.println("Invalid format for the date or time. Try again.");
                }
            }
        }
            add(new Task(taskName, taskDesc, localDateTime));
            System.out.println("New task was created and added into the list: " + "Name: " + taskName + " Description: " + taskDesc + " Due date: " + localDateTime);

    }

    public List<Task> getAllTasks() {
        return this.tasksList;
    }

    public List<Task> getTasksListFromThePast() {
        LocalDateTime localDateTime = LocalDateTime.now();
        List<Task> filteredTasksList = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.getDueDate().compareTo(localDateTime) < 0) {
                filteredTasksList.add(task);
            }
        }
        return filteredTasksList;
    }

    public List<Task> getTasksListWhichArePlaned(String option) {
        LocalDateTime fromLocalDateTime = LocalDateTime.now();
        LocalDateTime toLocalDateTime;

        switch (option) {
            case "day":
                toLocalDateTime = LocalDateTime.now()
                        .plusDays(1);
                break;
            case "week":
                toLocalDateTime = LocalDateTime.now().with(LocalTime.of(23, 59))
                        .with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            case "month":
                toLocalDateTime = LocalDateTime.now()
                        .plusDays(30);
                break;
            default:
                toLocalDateTime = LocalDateTime.now()
                        .plusMonths(1000);
        }

        List<Task> filteredTasksList = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.getDueDate().compareTo(fromLocalDateTime) > 0 && task.getDueDate().compareTo(toLocalDateTime) < 0) {
                filteredTasksList.add(task);
            }
        }
        return filteredTasksList;
    }
}
