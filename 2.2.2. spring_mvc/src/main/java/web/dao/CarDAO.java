package web.dao;

import org.springframework.stereotype.Component;
import web.models.Car;

import java.util.ArrayList;
import java.util.List;

@Component
public class CarDAO {
    private List<Car> cars;

    {
        cars = new ArrayList<>();

        cars.add(new Car("yellow",3584,"Lada"));
        cars.add(new Car("black",1001,"Mercedes"));
        cars.add(new Car("green",6988,"Jaguar"));
        cars.add(new Car("red",2389,"Kia"));
        cars.add(new Car("blue",6702,"Subaru"));
    }

    public List<Car> index() {return cars;}

    public List<Car> showThisCars(int count){
    return cars.subList(0,count);
    }

}
