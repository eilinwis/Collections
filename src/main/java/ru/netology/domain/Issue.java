package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable<Issue> {
    private int id;
    private String name;
    private String label;
    private String author;
    private String project;
    private String milestone;
    private String assignee;
    private Boolean open = true;


    @Override
    public int compareTo(Issue o) {
        return 0;
    }

    public boolean getOpen(int id) {
        return open;
    }
}
