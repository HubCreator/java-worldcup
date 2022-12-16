package worldcup.domain;


import java.util.Objects;

public class Group implements Comparable<Group> {
    private final String name;

    public Group(String name) {
        this.name = name;
    }

    public boolean matchName(String groupName) {
        return this.name.startsWith(groupName);
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Group o) {
        return Objects.compare(this.name, o.name, (a, b) -> a.charAt(0) - b.charAt(0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
