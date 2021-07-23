package ru.netology.repository;

import ru.netology.domain.Issue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();
    public Issue findById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public List<Issue> findAll() {
        return issues;
    }

    public void removeById(int id) {
        issues.removeIf(issue -> issue.getId() == id);
    }

    public void removeAll(Collection<Issue> issues) {
        this.issues.removeAll(issues);

    }

    public void addAll(Collection<Issue> issues) {
        this.issues.addAll(issues);

    }


}