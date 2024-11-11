package store;

import store.controller.ConvenienceController;
import store.domain.Products;
import store.domain.Promotions;
import store.handler.ApiHandler;
import store.handler.Validator;
import store.handler.ViewHandler;
import store.util.CallBackTemplate;
import store.util.FileLeader;
import store.view.Input;
import store.view.Output;

public class Config {
    private final FileLeader leader;
    private final UserRequest userRequest;

    public Config() {
        this.leader = new FileLeader();
        this.userRequest = createUserRequest();
    }

    public UserRequest getUserRequest() {
        return userRequest;
    }

    private UserRequest createUserRequest() {
        return new UserRequest(new CallBackTemplate(), createController(), createViewHandler());
    }

    private ConvenienceController createController() {
        Promotions promotions = new Promotions(leader.loadPromotionsFromFile("./src/main/resources/promotions.md"));
        Products products = new Products(leader.loadProducts("./src/main/resources/products.md", promotions));
        return new ConvenienceController(products, promotions);
    }

    private ViewHandler createViewHandler() {
        return new ViewHandler(new CallBackTemplate(), new ApiHandler(new Validator()), new Input(), new Output());
    }
}
