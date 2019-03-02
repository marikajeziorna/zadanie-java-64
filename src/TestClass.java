import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        TasksList tasksList = new TasksList();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        tasksList.add(new Task("Task1", "Change order", LocalDateTime.parse("2019-02-11 10:00", dateTimeFormatter)));
        tasksList.add(new Task("Task2", "Change address", LocalDateTime.parse("2019-01-22 12:00", dateTimeFormatter)));
        tasksList.add(new Task("Task3", "Update description", LocalDateTime.parse("2019-03-01 11:00", dateTimeFormatter)));
        tasksList.add(new Task("Task4", "Remove order form the list", LocalDateTime.parse("2019-03-24 09:00", dateTimeFormatter)));
        tasksList.add(new Task("Task5", "Delete recipient from the list", LocalDateTime.parse("2019-04-05 07:00", dateTimeFormatter)));
        tasksList.add(new Task("Task6", "Update tasks list", LocalDateTime.parse("2019-04-05 07:00", dateTimeFormatter)));

        tasksList.createNewTask();
        System.out.println("All tasks list:");
        printClass(tasksList.getAllTasks());
        System.out.println("All tasks list from the past:");
        printClass(tasksList.getTasksListFromThePast());
        System.out.println("All tasks list which are planed for next 24h:");
        printClass(tasksList.getTasksListWhichArePlaned("day"));
        System.out.println("All tasks list which are planed for current weekend:");
        printClass(tasksList.getTasksListWhichArePlaned( "week"));
        System.out.println("All tasks list which are planed for next 30 days:");
        printClass(tasksList.getTasksListWhichArePlaned("month"));
    }

    public static void printClass(List<Task> tasksList) {
        for (Task task : tasksList) {
            System.out.println("Name: " + task.getName() + ", Description: " + task.getDescription() + ", Due date: " + task.getDueDate());
        }
    }
}
