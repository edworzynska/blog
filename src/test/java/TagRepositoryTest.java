import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TagRepositoryTest {
    private static HibernateUtil hibernateUtil;
    private static TagRepository tagRepository;
    private static SessionFactory sessionFactory;

    private static Post post1;
    private static Post post2;
    private static Comment comment1;
    private static Comment comment2;
    private static Comment comment3;
    private static Comment comment4;
    private static Tag tag1;
    private static Tag tag2;
    private static Tag tag3;
    private static Tag tag4;


    @BeforeEach
    void setUp() {
        hibernateUtil = new HibernateUtil();
        sessionFactory = hibernateUtil.startSession();
        tagRepository = new TagRepository(sessionFactory);
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();


        post1 = new Post();
        post1.setTitle("my first post");
        post1.setContent("hello friends");
        session.persist(post1);

        post2 = new Post();
        post2.setTitle("my second post");
        post2.setContent("I had an amazing day! I learnt some java");
        session.persist(post2);


        tag1 = new Tag();
        tag1.setName("life");
        tag1.posts.add(post1);
        tag1.posts.add(post2);
        session.persist(tag1);

        tag2 = new Tag();
        tag2.setName("coding");
        tag2.posts.add(post2);
        session.persist(tag2);

        tag3 = new Tag();
        tag3.setName("third tag");
        tag3.posts.add(post2);
        session.persist(tag3);

        tag4 = new Tag();
        tag4.setName("fourth tag");
        tag4.posts.add(post1);
        session.persist(tag4);

        comment1 = new Comment();
        comment1.setAuthor("anonymous");
        comment1.setContent("hello!!!");
        comment1.setPost(post1);
        session.persist(comment1);

        comment2 = new Comment();
        comment2.setAuthor("gucci");
        comment2.setContent("doobee nice to see you");
        comment2.setPost(post1);
        session.persist(comment2);

        comment3 = new Comment();
        comment3.setAuthor("gucci");
        comment3.setContent("are we gonna walk today");
        comment3.setPost(post2);
        session.persist(comment3);

        comment4 = new Comment();
        comment4.setAuthor("doo");
        comment4.setContent("irrelevant content here");
        comment4.setPost(post2);
        session.persist(comment4);

        session.getTransaction().commit();
    }

    @Test
    void findsPostsTags() {
        var results = tagRepository.showTags(1);
        String[] arrOfResults = new String[results.size()];
        int iterator = 0;
        for (Tag tag : results){
            arrOfResults[iterator] = tag.getName();
            iterator++;
        }
        String result = String.join(", ", arrOfResults);
        assertEquals("life, fourth tag", result);
    }

    @Test
    void findsPostsTags2() {
        var results = tagRepository.showTags(2);
        String[] arrOfResults = new String[results.size()];
        int iterator = 0;
        for (Tag tag : results){
            arrOfResults[iterator] = tag.getName();
            iterator++;
        }
        String result = String.join(", ", arrOfResults);
        assertEquals("life, coding, third tag", result);
    }
}