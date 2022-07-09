package org.vso.models.domain;

import org.vso.models.entity.Task;
import org.vso.models.entity.TaskRepository;
import org.vso.utils.DateParser;

import java.time.LocalDate;
import java.util.List;

public class DailyTaskService {

    private final TaskRepository taskRepository;
    private final DateParser dateParser;

    public DailyTaskService() {
        this.taskRepository = new TaskRepository();
        this.dateParser = DateParser.getInstance();
//        taskDAO.saveTask(new Task(LocalDate.now(), "This is my fifth task!", Priority.MEDIUM));
    }

    public List<Task> getInitTasks() {
        LocalDate today = LocalDate.now();
        return taskRepository.getTasksForSelectedDay(today);
    }

    public List<Task> getTasksForSelectedDayByPriority(String date) {
        LocalDate selectedDay = dateParser.parse(date);
        return taskRepository.getTasksForSelectedDayByPriority(selectedDay);
    }
}
