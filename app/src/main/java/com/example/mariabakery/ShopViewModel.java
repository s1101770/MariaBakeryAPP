package com.example.mariabakery;

public class ShopViewModel {


    String Name;
    String Detail;

    int Image;

    public ShopViewModel(){

    }

    public ShopViewModel(String Name, String Detail, int Image){
        this.Detail = Detail;
        this.Name = Name;
        this.Image = Image;
    }



    public String getName() {
        return Name;
    }

    public String getDetail() {
        return Detail;
    }

    public int getImage() {
        return Image;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setName(String name) {
        Name = name;
    }


}
