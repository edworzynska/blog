import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Posts")
public class Post {
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "post")
    Set<Comment> comments;

    @ManyToMany(mappedBy = "posts")
    private Set<Tag> tags = new HashSet<>();

    @Getter
    @Setter
    @Column(name="title")
    private String title;

    @Getter
    @Setter
    @Column(name="content")
    private String content;

    public Post() {}

    @Override
    public String toString(){
        return getTitle() + "\n" + getContent();
    }
}
