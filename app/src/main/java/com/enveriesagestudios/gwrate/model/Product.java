package com.enveriesagestudios.gwrate.model;

public class Product {
    private String product_image1, product_image2, product_name, product_category;
    private Integer product_price, product_id;

    //Constructor
    public Product(Integer product_id, String product_name, String product_category, Integer product_price, String product_image1, String product_image2) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_image1 = product_image1;
        this.product_image2 = product_image2;
    }

    public Product(Integer product_id, String product_name, String product_category, Integer product_price, String product_image1) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_price = product_price;
        this.product_image1 = product_image1;
    }

    //Constructor without Picture
    public Product(Integer product_id, String product_name, String product_category, Integer product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_category = product_category;
        this.product_price = product_price;
    }

    //Accessor
    public int getId(){
        return product_id;
    }
    public String getName(){
        return product_name;
    }
    public String getCategory(){
        return product_category;
    }
    public int getPrice(){
        return product_price;
    }
    public String getPicture1(){
        return product_image1;
    }
    public String getPicture2(){
        return product_image2;
    }

    //Setter
    public void setId(int id){
        this.product_id = id;
    }
    public void setName(String name){
        this.product_name = name;
    }
    public void setCategory(String category){
        this.product_category = category;
    }
    public void setPrice(int price){
        this.product_price = price;
    }
    public void setProduct_image1(String link1){
        this.product_image1 = link1;
    }
    public void setProduct_image2(String link2){
        this.product_image1 = link2;
    }

}
