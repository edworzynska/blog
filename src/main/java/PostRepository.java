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
// display is not a concern of a repo, should not be here
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

// repositiories have convetion of naming for GET queries to start with find... or get...
//Find could have a list being returned for plural naming 
//Get a single result or an exception if not found
//good name would be for example findCommentsToPost(int postId)

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
    //this potentially opens you up to SQL Injection type of attack, have a read about it https://en.wikipedia.org/wiki/SQL_injection
    public List<Post> findByTag(String tagName){
        List<Post> posts = null;
        Session session = sessionFactory.openSession();
        posts = session.createQuery("select p from Post p join fetch p.tags t where t.name = :name", Post.class)
                .setParameter("name", tagName)
                .getResultList();
        return posts;
    }
}