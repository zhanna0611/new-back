package employee_details;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.awt.*;

public class MenuService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getBookRatingFallback",
            threadPoolKey = "getBookRating",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            })
    public Menu getUserMenu(String menuID) {
        return restTemplate.getForObject(
                "http://localhost:8080/menu/" + menuID,
                Menu.class);
    }
    public Menu getUserMenuFallback(String menuId) {
        return new Menu(menuId, 0);
    }
}}
