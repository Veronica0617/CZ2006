package com.example.se_project;

public enum TaskType {
    GetPM25(1),GetPSI(2);
    int value;
    private  TaskType(int value){
        this.value = value;
    }
}
