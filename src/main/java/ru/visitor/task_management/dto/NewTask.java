package ru.visitor.task_management.dto;

public class NewTask extends Dto {

    public NewTask(String name, String description) {
        setName(name);
        setDescription(description);
    }
}
