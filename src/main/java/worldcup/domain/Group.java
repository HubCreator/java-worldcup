package worldcup.domain;


import java.util.Objects;

public class Group implements Comparable<Group> {
    private final String title;

    public Group(String title) {
        this.title = title;
    }

    public boolean match(String groupName) {
        return this.title.equals(groupName);
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Group o) {
        return Objects.compare(this.title, o.title, (a, b) -> a.charAt(0) - b.charAt(0));
    }
}
