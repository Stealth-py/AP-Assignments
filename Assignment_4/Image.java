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
            System.out.println("1) Input an image\n 2) Create an image(rgb/grayscale of given dimensions)\n 3) Update the image\n ");
            int ch = Integer.parseInt(br.readLine());
            if(ch==1){
                int type;
                System.out.println("Enter the type of image:\n 1) RGB\n 2) Grayscale");
                type = Integer.parseInt(br.readLine());
                System.out.println("Enter the dimensions of the image (Format:- <height> <width>): ");
                int h, w;
                String[] s = br.readLine().split(" ");
                h = Integer.parseInt(s[0]);
                w = Integer.parseInt(s[1]);
                img = new Images(h, w, type);
                if(type == 1){
                    int[][][] im = new int[h][w][3];
                    System.out.println("Enter the image color values for each pixel(Format: <R> <G> <B>)");
                    for(int i = 0; i<h; i++){
                        for(int j = 0; j<w; j++){
                            String[] s1 = br.readLine().split(" ");
                            im[i][j][0] = Integer.parseInt(s1[0]);
                            im[i][j][1] = Integer.parseInt(s1[1]);
                            im[i][j][2] = Integer.parseInt(s1[2]);
                        }
                    }
                }else{
                    
                }
            }
        }
    }
}

class Images{
    private int h;
    private int w;
    private int type;
    private int[][] grayscale;
    private int[][][] rgb;
    
    Images(int h, int w, int type){
        this.h = h;
        this.w = w;
        this.type = type;
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
    public int[] get_dims(){
        return {this.h, this.w};
    }
}
