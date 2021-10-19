package Assignment_2;

import java.io.*;
import java.util.*;

interface lectureMats{
    public String get_title();
    public Date get_date();
    public instructor get_prof();
    public void set_title(String title);
    public void set_prof(instructor prof);
}

interface assessments{
    // public void get_assessment();
    // public void set_assessment();
}

interface users{
    // public String getname();
    // public void login();
    // public void logout();
    // public void view_lecture_material();
    // public void view_comments();
    // public void add_comments();
}


public class Backpack {
    private ArrayList<instructor> instructors = new ArrayList<instructor>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        int choice = 0;
        boolean flag = true;
        while(flag){
            System.out.println("Welcome to Backpack");
            System.out.println("1. Enter as instructor");
            System.out.println("2. Enter as student");
            System.out.println("3. Exit");
            choice = Integer.parseInt(br.readLine());
            if(choice==3){
                flag = false;
            }
        }
    }
}

class lectureSlides implements lectureMats{
    private String title;
    private ArrayList<String> slides;
    private instructor prof;
    Date date;

    lectureSlides(){
        date = new Date();
    }

    public String get_title(){
        return this.title;
    }
    public Date get_date(){
        return this.date;
    }
    public ArrayList<String> get_slides(){
        return this.slides;
    }
    public instructor get_prof(){
        return this.prof;
    }
    public void set_title(String title){
        this.title = title;
    }
    public void set_prof(instructor prof){
        this.prof = prof;
    }
    public void set_slides(ArrayList<String> slides){
        this.slides = slides;
    }
}

class lectureVids implements lectureMats{
    private String title;
    private String video;
    private instructor prof;
    Date date;

    lectureVids(){
        date = new Date();
    }

    public String get_title(){
        return this.title;
    }
    public Date get_date(){
        return this.date;
    }
    public String get_video(){
        return this.video;
    }
    public instructor get_prof(){
        return this.prof;
    }
    public void set_title(String title){
        this.title = title;
    }
    public void set_prof(instructor prof){
        this.prof = prof;
    }
    public void set_slides(String video){
        this.video = video;
    }
}

class instructor implements users{
    private String name;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    instructor(){
        name = " ";
    }
    
    public String getname(){
        return this.name;
    }
    public void login(){
        System.out.println("INSTRUCTOR MENU");
        System.out.println("1. Add class material");
        System.out.println("2. Add assessments");
        System.out.println("3. View lecture materials");
        System.out.println("4. View assessments");
        System.out.println("5. Grade assessments");
        System.out.println("6. Close assessment");
        System.out.println("7. View comments");
        System.out.println("8. Add comments");
        System.out.println("9. Logout");
    }
    public void add_lecture_material() throws IOException{
        System.out.println("1. Add Lecture Slide" + "\n2. Add Lecture Video");
        int choice = 0;
        choice = Integer.parseInt(br.readLine());
    }
}