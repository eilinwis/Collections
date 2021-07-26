package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {

    IssueRepository repository = new IssueRepository();
    List<Issue> issues = new ArrayList<>();

    public void add(Issue issue) {
        issues.add(issue);
    }

    public List<Issue> getAll() {
        return repository.findAll();
    }

    public List<Issue> showOpenIssues(Comparator<Issue> comparator) {
        List<Issue> openIssue = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getOpen()) {
                openIssue.add(issue);
            }
        }
        openIssue.sort(comparator);
        return openIssue;
    }

    public List<Issue> showClosedIssues(Comparator<Issue> comparator) {
        List<Issue> closedIssue = new ArrayList<>();
        for (Issue issue : issues) {
            if (!issue.getOpen()) {
                closedIssue.add(issue);
            }
        }
        closedIssue.sort(comparator);
        return closedIssue;
    }

    public List<Issue> showFilterByAuthor(String author, Comparator<Issue> comparator) {
        List<Issue> filtredByAuthor = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAuthor().equals(author)) {
                filtredByAuthor.add(issue);
            }
        }
        filtredByAuthor.sort(comparator);
        return filtredByAuthor;
    }

    public List<Issue> showFilterByLabel(Predicate<String> name, Comparator<Issue> comparator) {
        List<Issue> filtredByLabel = new ArrayList<>();
        for (Issue issue : issues) {
            for (Label label : issue.getLabel()) {
                if (name.test(label.getLabel())) {
                    filtredByLabel.add(issue);
                }
            }
            filtredByLabel.sort(comparator);

        } return filtredByLabel;
    }

    public List<Issue> showFilterByAssignee(String assignee, Comparator<Issue> comparator) {
        List<Issue> filtredByAssignee = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getAssignee().equals(assignee)) {
                filtredByAssignee.add(issue);
            }
        }
        filtredByAssignee.sort(comparator);
        return filtredByAssignee;
    }

    public Issue getById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public void closeIssue(int id) {
        Issue issue = getById(id);
        issue.setOpen(false);
    }

    public void openIssue(int id) {
        Issue issue = getById(id);
        issue.setOpen(true);
    }

}
