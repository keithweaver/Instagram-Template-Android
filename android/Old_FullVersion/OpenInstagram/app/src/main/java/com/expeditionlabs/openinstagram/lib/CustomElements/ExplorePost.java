package com.expeditionlabs.openinstagram.lib.CustomElements;

import android.graphics.Bitmap;

/**
 * Created by Keith on 2015-07-30.
 */
public class ExplorePost {
    String postCode1;
    String imgLink1;
    Bitmap img1;

    String postCode2;
    String imgLink2;
    Bitmap img2;

    String postCode3;
    String imgLink3;
    Bitmap img3;

    public ExplorePost(String postCode1, String imgLink1, Bitmap img1,
                       String postCode2, String imgLink2, Bitmap img2,
                       String postCode3, String imgLink3, Bitmap img3){
        super();
        this.postCode1 = postCode1;
        this.imgLink1 = imgLink1;
        this.img1 = img1;

        this.postCode2 = postCode2;
        this.imgLink2 = imgLink2;
        this.img2 = img2;

        this.postCode3 = postCode3;
        this.imgLink3 = imgLink3;
        this.img3 = img3;
    }
    public String getPostCode1(){
        return this.postCode1;
    }
    public String getImgLink1(){
        return this.imgLink1;
    }
    public Bitmap getImg1(){
        return this.img1;
    }
    public String getPostCode2(){
        return this.postCode2;
    }
    public String getImgLink2(){
        return this.imgLink2;
    }
    public Bitmap getImg2(){
        return this.img2;
    }
    public String getPostCode3(){
        return this.postCode3;
    }
    public String getImgLink3(){
        return this.imgLink3;
    }
    public Bitmap getImg3(){
        return this.img3;
    }
    public void setImg1(Bitmap b){
        this.img1 = b;
    }
    public void setImg2(Bitmap b){
        this.img2 = b;
    }
    public void setImg3(Bitmap b){
        this.img3 = b;
    }
    public void setImgLink1(String l){
        this.imgLink1 = l;
    }
    public void setImgLink2(String l){
        this.imgLink2 = l;
    }
    public void setImgLink3(String l){
        this.imgLink3 = l;
    }
    public void setPostCode1(String c){
        this.postCode1 = c;
    }
    public void setPostCode2(String c){
        this.postCode2 = c;
    }
    public void setPostCode3(String c){
        this.postCode3 = c;
    }
}
