import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class TagRepository {
    private SessionFactory sessionFactory;
    private Tag tag;

    public TagRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    public List<Tag> showTags(int postID){
        List<Tag> postsTags = null;
        Session session = sessionFactory.openSession();
        postsTags = session.createQuery("select t from Tag t join fetch t.posts p where p.id = :id", Tag.class)
                .setParameter("id", postID)
                .getResultList();
        return postsTags;
    }
}
