
package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Label {
    int id;
    String name;
    String condition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id && name.equals(label.name) && condition.equals(label.condition);
    }


    public boolean matches(String prefix) {
        return (prefix.equalsIgnoreCase(this.name));
    }

    public String getLabel(){
        return Label.this.name;
    }

}