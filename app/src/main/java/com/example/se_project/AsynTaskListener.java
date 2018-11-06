package com.example.se_project;

import java.util.ArrayList;

public interface AsynTaskListener {
    public void onTaskCompleted(ArrayList<PM25> result, TaskType taskType);
}
