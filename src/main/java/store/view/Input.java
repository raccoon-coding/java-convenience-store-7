package store.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Input {
    public Input() {
    }

    public String getPurchaseProducts(String products) {
        System.out.println(products);
        return getLine();
    }

    public String getMembershipDiscount() {
        System.out.println(ViewConstants.멤버십_할인.getMessage());
        return getLine();
    }

    public String getPromotionProducts(String getPromotionProduct) {
        System.out.println(getPromotionProduct);
        return getLine();
    }

    public String haveNotPromotionProducts(String notPromotionProduct) {
        System.out.println(notPromotionProduct);
        return getLine();
    }

    public String purchaseMoreProduct() {
        System.out.println(ViewConstants.추가_이용.getMessage());
        return getLine();
    }

    private String getLine() {
        return readLine();
    }
}
