import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.comparator.Sorter;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.manager.IssueManager;
import ru.netology.repository.IssueRepository;

import java.awt.*;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IssuesTest {

    IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager();
    Sorter sorter = new Sorter();

    private Label label1 = new Label(1, "type", "bug");
    private Label label2 = new Label(2, "component", "Platform");
    private Label label3 = new Label(3, "theme", "concurrency");
    private Label label4 = new Label(4, "status", "waiting-for-feedback");
    private Label label5 = new Label(5, "type", "question");

    private Set<Label> labelSet1 = new HashSet<>(List.of(label1, label4));
    private Set<Label> labelSet2 = new HashSet<>(List.of(label2, label4));
    private Set<Label> labelSet3 = new HashSet<>(List.of(label3, label1));
    private Set<Label> labelSet4 = new HashSet<>(List.of(label4, label5));


    Issue issue1 = new Issue(1, "Неправилььная раскладка", labelSet4, "Roman", "ru.netology", "2.0", "Vasya", false);
    Issue issue2 = new Issue(2, "Ошибка доступа ", labelSet2, "Roman", "ru.netology", "2.0", "Sergey", true);
    Issue issue3 = new Issue(3, "Не отправляется форма", labelSet3, "Vasya", "ru.netology", "2.0", "Petya", false);
    Issue issue4 = new Issue(4, "Цвет кнопки неверный", labelSet1, "Vasya", "ru.netology", "2.0", "Petya", true);
    Issue issue5 = new Issue(5, "Нет сообщения об ошибке", labelSet4, "Petya", "ru.netology", "2.0", "Vasya", false);
    Issue issue6 = new Issue(6, "Нет ответа сервера при отправке запроса", labelSet2, "Petya", "ru.netology", "3.0", "Vasya", true);
    Issue issue7 = new Issue(7, "Неверный шрифт", labelSet3, "Vasya", "ru.netology", "2.0", "Petya", true);
    Issue issue8 = new Issue(8, "Имя уже используется", labelSet2, "Petya", "ru.netology", "3.0", "Vasya", true);
    Issue issue9 = new Issue(9, "Пустая форма отправляется", labelSet3, "Roman", "ru.netology", "4.0", "Vasya", false);
    Issue issue10 = new Issue(10, "Поле имени не принимает больше 7 символов", labelSet1, "Petya", "ru.netology", "2.0", "Sergey", false);

    @BeforeEach
    void shouldAdd() {
        manager.add(issue5);
        manager.add(issue3);
        manager.add(issue1);
        manager.add(issue4);
        manager.add(issue2);
        manager.add(issue9);
        manager.add(issue6);
        manager.add(issue10);
        manager.add(issue8);
        manager.add(issue7);
    }

    @Test
    void shouldRemoveById() {
        repository.removeById(3);
        assertNull(repository.findById(3));
    }

    @Test
    void shouldFindById() {
        assertEquals(issue4, manager.getById(4));
    }

    @Test
    void shouldShowOpenIssues() {
        assertEquals(List.of(issue2, issue4, issue6, issue7, issue8), manager.showOpenIssues(sorter));
    }

    @Test
    void shouldShowClosedIssues() {
        assertEquals(List.of(issue1, issue3, issue5, issue9, issue10), manager.showClosedIssues(sorter));
    }

    @Test
    void shouldCloseIssues() {
        manager.closeIssue(2);
        assertFalse(issue2.getOpen());
    }

    @Test
    void shouldOpenIssues() {
        manager.openIssue(3);
        assertTrue(issue3.getOpen());
    }

    @Test
    void shouldFilterByAuthor() {
        assertEquals(List.of(issue1, issue2, issue9), manager.showFilterByAuthor(("Roman"), sorter));
    }

    @Test
    void shouldFilterByLabel() {
        assertEquals(List.of(issue1, issue3, issue4, issue5, issue7, issue9, issue10), manager.showFilterByLabel(name -> name.equals("type"), sorter));
    }

    @Test
    void shouldFilterByAssignee() {
        assertEquals(List.of(issue3, issue4, issue7), manager.showFilterByAssignee(("Petya"), sorter));
    }

}
