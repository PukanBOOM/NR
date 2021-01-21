package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.CarDAO;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarDAO carDAO;

    @Autowired
    public CarController(CarDAO carDAO) {this.carDAO = carDAO;}

    @GetMapping
    public String index(@RequestParam(value = "count",required = false,defaultValue = "5") int count,
            Model model){
        if (count >= 5)
            model.addAttribute("cars",carDAO.index());
        else
            model.addAttribute("cars", carDAO.showThisCars(count));
        return "/cars";
    }


}
