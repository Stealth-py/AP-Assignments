package Assignment_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Image {
    private static ArrayList<Images> imgs = new ArrayList<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        Images img;
        while(true){
            System.out.println("1) Input an image\n2) Create an image(rgb/grayscale of given dimensions)\n3) Update the image\n4) Find negative of image\n5)Exit");
            int ch = Integer.parseInt(br.readLine());
            if(ch==1){
                int type;
                System.out.println("Enter the type of image:\n1) RGB\n2) Grayscale");
                type = Integer.parseInt(br.readLine());
                System.out.println("Enter the dimensions of the image (Format:- <height> <width>): ");
                int h, w;
                String[] s = br.readLine().split(" ");
                h = Integer.parseInt(s[0]);
                w = Integer.parseInt(s[1]);
                if(h<=0 || w<=0){
                    System.out.println("Enter valid dimensions");
                    continue;
                }
                img = new Images(h, w, type);
                if(type == 1){
                    int[][][] im = new int[h][w][3];
                    System.out.println("Enter the image color values for each pixel(Format: <R> <G> <B>, bw 0 and 255)");
                    for(int i = 0; i<h; i++){
                        for(int j = 0; j<w; j++){
                            String[] s1 = br.readLine().split(" ");
                            int r = Integer.parseInt(s1[0]);
                            int g = Integer.parseInt(s1[1]);
                            int b = Integer.parseInt(s1[2]);
                            if(r>255 || r<0 || g>255 || g<0 || b>255 || b<0){
                                System.out.println("Enter valid rgb value(between 0 and 255 inclusive)!");
                                j-=1;
                                continue;
                            }
                            im[i][j][0] = r;
                            im[i][j][1] = g;
                            im[i][j][2] = b;
                        }
                    }
                    img.set_rgb(im);
                    System.out.println("New image created");
                    System.out.println("The image: ");
                    for(int i = 0; i<h; i++){
                        for(int j = 0; j<w; j++){
                            System.out.println(im[i][j][0] + ", " + im[i][j][1] + ", " + im[i][j][2]);
                        }
                    }
                    System.out.println(im);
                }else{
                    int[][] im = new int[h][w];
                    System.out.println("Enter the image color values for each pixel(Format: <0 or 255>)");
                    for(int i = 0; i<h; i++){
                        for(int j = 0; j<w; j++){
                            int bw = Integer.parseInt(br.readLine());
                            if(bw!=0 && bw!=255){
                                System.out.println("Enter valid rgb value(between 0 and 255 inclusive)!");
                                j-=1;
                                continue;
                            }
                            im[i][j] = bw;
                        }
                    }
                    img.set_gray(im);
                    System.out.println("New image created");
                    System.out.println("The image: ");
                    for(int i = 0; i<h; i++){
                        for(int j = 0; j<w; j++){
                            System.out.println(im[i][j]);
                        }
                    }
                }
                imgs.add(img);
            }else if(ch==2){
                int type;
                System.out.println("Enter the type of image to be created:\n1) RGB\n2) Grayscale");
                type = Integer.parseInt(br.readLine());
                System.out.println("Enter the dimensions of the image to be created (Format:- <height> <width>): ");
                int h, w;
                String[] s = br.readLine().split(" ");
                h = Integer.parseInt(s[0]);
                w = Integer.parseInt(s[1]);
                if(h<=0 || w<=0){
                    System.out.println("Enter valid dimensions");
                    continue;
                }
                img = new Images(h, w, type);
                img.create_img();
                imgs.add(img);
            }else if(ch==3){
                System.out.println("Kindly select from the following images, the image to be edited: ");
                if(imgs.size()==0){
                    System.out.println("No existing image found, creating a new image");
                    int type;
                    System.out.println("Enter the type of image:\n1) RGB\n2) Grayscale");
                    type = Integer.parseInt(br.readLine());
                    System.out.println("Enter the dimensions of the image (Format:- <height> <width>): ");
                    int h, w;
                    String[] s = br.readLine().split(" ");
                    h = Integer.parseInt(s[0]);
                    w = Integer.parseInt(s[1]);
                    if(h<=0 || w<=0){
                        System.out.println("Enter valid dimensions");
                        continue;
                    }
                    img = new Images(h, w, type);
                    if(type == 1){
                        int[][][] im = new int[h][w][3];
                        System.out.println("Enter the image color values for each pixel(Format: <R> <G> <B>, bw 0 and 255)");
                        for(int i = 0; i<h; i++){
                            for(int j = 0; j<w; j++){
                                String[] s1 = br.readLine().split(" ");
                                int r = Integer.parseInt(s1[0]);
                                int g = Integer.parseInt(s1[1]);
                                int b = Integer.parseInt(s1[2]);
                                if(r>255 || r<0 || g>255 || g<0 || b>255 || b<0){
                                    System.out.println("Enter valid rgb value(between 0 and 255 inclusive)!");
                                    j-=1;
                                    continue;
                                }
                                im[i][j][0] = r;
                                im[i][j][1] = g;
                                im[i][j][2] = b;
                            }
                        }
                        img.set_rgb(im);
                        System.out.println("New image created");
                        System.out.println("The image: ");
                        for(int i = 0; i<h; i++){
                            for(int j = 0; j<w; j++){
                                System.out.println(im[i][j][0] + ", " + im[i][j][1] + ", " + im[i][j][2]);
                            }
                        }
                    }else{
                        int[][] im = new int[h][w];
                        System.out.println("Enter the image color values for each pixel(Format: <0 or 255>)");
                        for(int i = 0; i<h; i++){
                            for(int j = 0; j<w; j++){
                                int bw = Integer.parseInt(br.readLine());
                                if(bw!=0 && bw!=255){
                                    System.out.println("Enter valid rgb value(between 0 and 255 inclusive)!");
                                    j-=1;
                                    continue;
                                }
                                im[i][j] = bw;
                            }
                        }
                        img.set_gray(im);
                        System.out.println("New image created");
                        System.out.println("The image: ");
                        for(int i = 0; i<h; i++){
                            for(int j = 0; j<w; j++){
                                System.out.println(im[i][j]);
                            }
                        }
                    }
                    imgs.add(img);
                    System.out.println("Image has been edited(i.e., new image created)");
                    continue;
                }
                System.out.println("Choose an integer corresponding to the image number");
                for(int i = 0; i<imgs.size(); i++){
                    System.out.println("Image " + ((int)i+1));
                }
                int c = Integer.parseInt(br.readLine());
                img = imgs.get(c-1);
                int type = img.get_type();
                int[] dim = img.get_dims();
                System.out.println("The image to be edited is " + (type==1?"RGB":"Grayscale"));
                System.out.println("Enter the new pixel values: ");
                if(type==1){
                    int[][][] ig = img.get_rgb();
                    for(int i = 0; i<dim[0]; i++){
                        for(int j = 0; j<dim[1]; j++){
                            System.out.println("Enter RGB of format <R> <G> <B>: ");
                            String[] s1 = br.readLine().split(" ");
                            int r = Integer.parseInt(s1[0]);
                            int g = Integer.parseInt(s1[1]);
                            int b = Integer.parseInt(s1[2]);
                            if(r>255 || r<0 || g>255 || g<0 || b>255 || b<0){
                                System.out.println("Enter valid rgb value(between 0 and 255 inclusive)!");
                                j-=1;
                                continue;
                            }
                            ig[i][j][0] = r;
                            ig[i][j][1] = g;
                            ig[i][j][2] = b;
                        }
                    }
                    img.set_rgb(ig);
                    System.out.println("New image: ");
                    for(int i = 0; i<dim[0]; i++){
                        for(int j = 0; j<dim[1]; j++){
                            System.out.println(ig[i][j][0] + ", " + ig[i][j][1] + ", " + ig[i][j][2]);
                        }
                    }
                }else{
                    int[][] ig = img.get_gray();
                    for(int i = 0; i<dim[0]; i++){
                        for(int j = 0; j<dim[1]; j++){
                            System.out.println("Enter Grayscale value(only 0 or 255)");
                            int bw = Integer.parseInt(br.readLine());
                                if(bw!=0 && bw!=255){
                                    System.out.println("Enter valid rgb value(between 0 and 255 inclusive)!");
                                    j-=1;
                                    continue;
                                }
                                ig[i][j] = bw;
                        }
                    }
                    img.set_gray(ig);
                    System.out.println("New image: ");
                    for(int i = 0; i<dim[0]; i++){
                        for(int j = 0; j<dim[1]; j++){
                            System.out.println(ig[i][j]);
                        }
                    }
                }
                System.out.println("Image updated");
                imgs.set(c-1, img);
            }else if(ch==4){
                System.out.println("Kindly choose which image to find negative of: ");
                if(imgs.size()==0){
                    System.out.println("No image found, kindly create an image first.");
                    continue;
                }
                System.out.println("Choose an integer corresponding to the image number");
                for(int i = 0; i<imgs.size(); i++){
                    System.out.println("Image " + ((int)i+1));
                }
                int c = Integer.parseInt(br.readLine());
                img = imgs.get(c-1);
                img.find_negative();
            }else{
                break;
            }
            System.out.println("-----------------------------------------------------------------------");
        }
    }
}

class Images{
    private int h;
    private int w;
    private int type;
    private int[][] grayscale;
    private int[][] neg_gray;
    private int[][][] rgb;
    private int[][][] neg_rgb;
    
    Images(int h, int w, int type){
        this.h = h;
        this.w = w;
        this.type = type;
    }

    public void create_img(){
        if(type==1){
            for(int i = 0; i<h; i++){
                for(int j = 0; j<w; j++){
                    rgb[i][j][0] = 128;
                    rgb[i][j][1] = 128;
                    rgb[i][j][2] = 128;
                    System.out.println(128 + ", " + 128 + ", " + 128);
                }
            }
        }else{
            for(int i = 0; i<h; i++){
                for(int j = 0; j<w; j++){
                    grayscale[i][j] = (i+j)%2;
                    System.out.println(grayscale[i][j]);
                }
            }
        }
    }
    public void set_rgb(int[][][] rgb){
        this.rgb = rgb;
    }
    public void set_gray(int[][] grayscale){
        this.grayscale = grayscale;
    }
    public int[][][] get_rgb(){
        return this.rgb;
    }
    public int[][] get_gray(){
        return this.grayscale;
    }
    public int get_type(){
        return this.type;
    }
    public int[] get_dims(){
        int[] dim = new int[2];
        dim[0] = this.h;
        dim[1] = this.w;
        return dim;
    }
    public void find_negative(){
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                if(type==1){
                    neg_rgb[i][j][0] = 255-rgb[i][j][0];
                    neg_rgb[i][j][1] = 255-rgb[i][j][1];
                    neg_rgb[i][j][2] = 255-rgb[i][j][2];
                }else{
                    neg_gray[i][j] = 255 - grayscale[i][j];
                }
            }
        }
        System.out.println("The negative of image is: ");
        for(int i = 0; i<h; i++){
            for(int j = 0; j<w; j++){
                if(type==1){
                    System.out.println(neg_rgb[i][j][0] + ", " + neg_rgb[i][j][1] + ", " + neg_rgb[i][j][2]);
                }else{
                    System.out.println(neg_gray[i][j]);
                }
            }
        }
    }
}
