import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PostRepository {
    private SessionFactory sessionFactory;
    private Post post;

    public PostRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Post> all() {
        List<Post> allPosts = null;
        try (Session session = sessionFactory.openSession()) {
            allPosts = session.createQuery("from Post", Post.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return allPosts;
    }

    public String displayAll(){
        StringBuilder str = new StringBuilder();
        int counter = 1;
        for (Post post : all()){
            str.append(counter).append(": ");
            str.append(post.toString());
            str.append("\n");
            counter ++;
        }
        return str.toString();
    }

    public List<Comment> commentsToPost(int id){
        List<Comment> commentsToPost = null;
        try (Session session = sessionFactory.openSession()) {
            commentsToPost = session.createQuery("select c from Comment c join fetch c.post p where p.id = :id", Comment.class)
                    .setParameter("id", id)
                    .getResultList();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return commentsToPost;
    }
}