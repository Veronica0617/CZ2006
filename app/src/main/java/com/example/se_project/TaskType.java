package com.example.se_project;

public enum TaskType {
    GetPM25(1),GetPSI(2),GetUVI(3);
    int value;
    private  TaskType(int value){
        this.value = value;
    }
}
