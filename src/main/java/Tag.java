import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Tags")
public class Tag {
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @Column(name="name")
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="Posts_Tags",
            joinColumns = {@JoinColumn(name="tag_id")},
            inverseJoinColumns = {@JoinColumn(name="post_id")}
    )
    Set <Post> posts = new HashSet<>();

    public Tag() {
    }
}
