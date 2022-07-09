package org.vso.views;

import org.vso.models.entity.Task;
import org.vso.presenters.HomePresenter;
import org.vso.utils.DateParser;

import java.util.List;
import java.util.Scanner;

public class HomeView {
    private final HomePresenter homePresenter;
    private final Scanner scanner;

    private final DateParser dateParser;

    public HomeView() {
        this.homePresenter = new HomePresenter(this);
        this.scanner = new Scanner(System.in);
        this.dateParser = DateParser.getInstance();

        homePresenter.onViewCreated();
    }

    public void showInstructions() {
        System.out.println("""
                1. Show all tasks, ordered by priority
                2. Create task
                3. Mark task as 'Completed'
                4. Delete task""");
    }

    public void showTasks(List<Task> initialTasks) {
        initialTasks.forEach(task -> System.out.println(task.toString()));
    }

    public int getUserOption() {
        return scanner.nextInt();
    }

    public String getUserDate() {
        String userText = scanner.nextLine();
        while (!dateParser.isCorrectDate(userText)){
            userText = scanner.nextLine();
        }
        return userText;
    }
}
