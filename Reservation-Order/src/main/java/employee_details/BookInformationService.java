package employee_details;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookInformationService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(
            fallbackMethod = "getUserBooksFallback",
            threadPoolKey = "getUserBooks",
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize", value="100"),
                    @HystrixProperty(name="maximumSize", value="120"),
                    @HystrixProperty(name="maxQueueSize", value="50"),
                    @HystrixProperty(name="allowMaximumSizeToDivergeFromCoreSize", value="true"),
            }
    )
    public UserMenu getUserBooks(String userId) {
        return restTemplate.getForObject(
                "http://localhost:8080/orderlist/" + userId,
                UserMenu.class);
    }

    public UserMenu getUserBooksFallback(String userId) {
        UserMenu userBook = new UserMenu();
        List<Employee> list = new ArrayList<>();
        list.add(new Book("-1", "Not available", "Not available", "Not available"));
        userBook.setUserMenu(list);

        return userBook;
    }
}