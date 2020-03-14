package pl.javastart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.dao.ProductDao;
import pl.javastart.model.Product;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringJpaQueriesDynamicApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SpringJpaQueriesDynamicApplication.class, args);

        List<Product> products = new ArrayList<>();
        products.add(new Product("Telewizor", "Samsung", 4500.0));
        products.add(new Product("Opiekacz", "Opiex", 120.0));
        products.add(new Product("Laptop", "Samsung", 3599.0));
        products.add(new Product("Kino domowe", "Yamaha", 2600.0));
        products.add(new Product("Smartfon", "Sony", 2100.0));

        ProductDao productDao = ctx.getBean(ProductDao.class);
        products.forEach(productDao::save);

        System.out.println();
        productDao.deleteByProducer("Samsung");
        System.out.println("Products without Samsung:");
        print(productDao.getAll());

        System.out.println();
        List<Product> productByName = productDao.getByName("Opiekacz");
        print(productByName);

        System.out.println();

        productDao.deleteAll();
        ctx.close();

    }


    public static void print(List<Product> products) {
        products.forEach(System.out::println);
    }

}
