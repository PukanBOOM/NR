import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld helloWorld1 =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld helloWorld2 =
                (HelloWorld) applicationContext.getBean("helloworld");
        Cat cat1 =(Cat) applicationContext.getBean("cat");
        Cat cat2 =(Cat) applicationContext.getBean("cat");
        boolean eq1 = helloWorld1==helloWorld2;
        boolean eq2 = cat1==cat2;
        System.out.println(eq1);
        System.out.println(eq2);
    }
}