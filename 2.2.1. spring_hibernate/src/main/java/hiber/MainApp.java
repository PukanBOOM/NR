package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User phil = new User("Phil", "Spencer", "Spencer@gmail.com");
      User hideo = new User("Hideo", "Kojima", "Kojima@gmail.com");
      User bobby = new User("Bobby", "Kotik", "Kotik@gmail.com");
      Car ferrari = new Car("Ferrari",1);
      Car toyota = new Car("Toyota",77);
      Car ford = new Car("Ford",999);
      phil.setCar(ferrari);
      hideo.setCar(toyota);
      bobby.setCar(ford);
      userService.add(phil);
      userService.add(hideo);
      userService.add(bobby);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println(userService.search("Ford",999).getFirstName());

      context.close();
   }
}
