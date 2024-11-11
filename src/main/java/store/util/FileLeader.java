package store.util;

import store.domain.Product;
import store.domain.Promotion;
import store.domain.Promotions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileLeader {
    public FileLeader() {
    }

    public List<Promotion> loadPromotionsFromFile(String filePath) {
        List<Promotion> promotions = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        retryPromotion(promotions, filePath, dateFormatter);
        return promotions;
    }

    public List<Product> loadProducts(String filePath, Promotions promotions) {
        List<Product> products = new ArrayList<>();
        retryProduct(filePath, products, promotions);
        return products;
    }

    private void retryPromotion(List<Promotion> promotions, String filePath, DateTimeFormatter dateFormatter) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Promotion promotion = splitPromotionLogic(line, dateFormatter);
                promotions.add(promotion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Promotion splitPromotionLogic(String line, DateTimeFormatter dateFormatter) {
        List<String> fields = Arrays.stream(line.split(",")).toList();
        String name = fields.getFirst();
        Integer buy = Integer.parseInt(fields.get(1));
        Integer get = Integer.parseInt(fields.get(2));
        LocalDateTime startDate = LocalDateTime.parse(fields.get(3) + " 00:00:00", dateFormatter);
        LocalDateTime endDate = LocalDateTime.parse(fields.get(4) + " 23:59:59", dateFormatter);
        return new Promotion(name, buy, get, startDate, endDate);
    }

    private void retryProduct(String filePath, List<Product> products, Promotions promotions) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Product product = splitProductLogic(line, products, promotions);
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Product splitProductLogic(String line, List<Product> products, Promotions promotions) {
        List<String> fields = Arrays.stream(line.split(",")).toList();
        String name = fields.getFirst();
        isDefaultProduct(products, name);
        Integer price = Integer.parseInt(fields.get(1));
        Integer stock = Integer.parseInt(fields.get(2));

        String promotionName = fields.get(3);
        return new Product(name, price, stock, promotions.findByName(promotionName));
    }

    private void isDefaultProduct(List<Product> products, String name) {
        if(!products.isEmpty()) {
            if(!Objects.equals(products.getLast().getName(), name) && !Objects.equals(products.getLast().getPromotion(), null)){
                Product last = products.getLast();
                products.add(new Product(last.getName(), last.getPrice(), 0, null));
            }
        }
    }
}
