package org.vso.presenters;

import org.vso.models.domain.DailyTaskService;
import org.vso.views.HomeView;

public class HomePresenter {

    private final HomeView homeView;
    private final DailyTaskService dailyTaskService;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        this.dailyTaskService = new DailyTaskService();
    }

    public void onViewCreated() {
        while (true) {
            homeView.showTasks(dailyTaskService.getInitTasks());
            homeView.showInstructions();
            int userOption = homeView.getUserOption();
            onUserOptionSelected(userOption);
        }
    }

    private void onUserOptionSelected(int userOption) {
        switch (userOption) {
            case 1 -> showTasksForSelectedDayByPriority(homeView.getUserDate());
        }
    }

    private void showTasksForSelectedDayByPriority(String userDate) {
        homeView.showTasks(dailyTaskService.getTasksForSelectedDayByPriority(userDate));
    }
}
