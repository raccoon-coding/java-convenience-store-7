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
import java.util.List;
import java.util.Objects;

public class FileLeader {
    public FileLeader() {
    }

    public List<Promotion> loadPromotionsFromFile(String filePath) {
        List<Promotion> promotionList = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                Integer buy = Integer.parseInt(fields[1]);
                Integer get = Integer.parseInt(fields[2]);
                LocalDateTime startDate = LocalDateTime.parse(fields[3] + " 00:00:00", dateFormatter);
                LocalDateTime endDate = LocalDateTime.parse(fields[4] + " 23:59:59", dateFormatter);

                Promotion promotion = new Promotion(name, buy, get, startDate, endDate);
                promotionList.add(promotion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return promotionList;
    }

    public List<Product> loadProducts(String filePath, Promotions promotions) {
        List<Product> productList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[0];
                isDefaultProduct(productList, name);
                Integer price = Integer.parseInt(fields[1]);
                Integer stock = Integer.parseInt(fields[2]);

                // 프로모션 필드가 "null"이 아닌 경우에만 프로모션 이름을 설정
                String promotionName = fields[3];

                Product product = new Product(name, price, stock, promotions.findByName(promotionName));
                productList.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productList;
    }

    private void isDefaultProduct(List<Product> productList, String name) {
        if(!productList.isEmpty()) {
            if(!Objects.equals(productList.getLast().getName(), name) && !Objects.equals(productList.getLast().getPromotion(), null)){
                Product last = productList.getLast();
                productList.add(new Product(last.getName(), last.getPrice(), 0, null));
            }
        }
    }
}
