import org.hibernate.SessionFactory;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Post post;
        HibernateUtil hibernateUtil = new HibernateUtil();
        SessionFactory sessionFactory = hibernateUtil.startSession();
        PostRepository postRepository = new PostRepository(sessionFactory);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the post:\n" + postRepository.displayAll());
        int choice = scanner.nextInt();

        var lst = postRepository.commentsToPost(choice);

        for (Comment comment : lst){
            System.out.println(comment.toString());
        }

    }
}