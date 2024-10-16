import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name="Comments")
public class Comment {

    @Getter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    @Column(name="author")
    private String author;

    @Getter
    @Setter
    @Column(name="content")
    private String content;

    @Getter
    @Setter
    @ManyToOne(fetch = LAZY)
    private Post post;

    public Comment() {}

    @Override
    public String toString(){
        return getAuthor() + ": " + getContent();
    }
}
