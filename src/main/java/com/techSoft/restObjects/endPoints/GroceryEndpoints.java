package com.techSoft.restObjects.endPoints;


public class GroceryEndpoints {
    // User-related endpoints
    public static final String USERS = "/api/users/";
    public static final String REGISTER = "/api/register/";

    // API client endpoint
    public static final String API_CLIENTS = "/api-clients";

    // Status endpoint
    public static final String STATUS = "/status";
    // Product-related endpoints
    public static final String PRODUCTS = "/products/";
    public static final String PRODUCTS_BY_ID = "/products/{id}";

    // Cart-related endpoints
    public static final String CARTS = "/carts/";
    public static final String CART_BY_ID = "/carts/{cartId}";
    public static final String ADD_ITEM_TO_CART = "/carts/{cartId}/items";
    public static final String UPDATE_CART_ITEM = "/carts/{cartId}/items/{itemId}";

    // Order-related endpoints
    public static final String ORDERS = "/orders/";
    public static final String UPDATE_ORDER = "/orders/{orderId}";
    public static final String DELETE_ORDER = "/orders/{orderId}";


}
