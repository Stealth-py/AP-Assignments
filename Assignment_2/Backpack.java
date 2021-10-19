package Assignment_2;

import java.io.*;
import java.util.*;

interface lectureMats{
    public String get_title();
    public Date get_date();
    public instructor get_prof();
    public void set_title(String title);
    public void set_prof(instructor prof);
    public void view_lecture_material();
}

interface assessments{
    // public void get_assessment();
    // public void set_assessment();
}

interface users{
    public String getname();
    public void login();
    // public void logout();
    // public void view_lecture_material();
    // public void view_comments();
    // public void add_comments();
}


public class Backpack {
    private static ArrayList<instructor> instructors = new ArrayList<instructor>();
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
            }else if(choice==1){
                int c = 0;
                flag = true;
                instructor i0 = new instructor("i0");
                instructor i1 = new instructor("i1");
                instructors.add(i0);
                instructors.add(i1);
                System.out.println("Instructors:");
                System.out.println("0 - i0");
                System.out.println("1 - i1");
                System.out.println("Choose id: ");
                c = Integer.parseInt(br.readLine());
                instructor curr = instructors.get(c);
                System.out.println("Welcome " + curr.getname());
                curr.login();
                c = Integer.parseInt(br.readLine());
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
    public void view_lecture_material(){
        System.out.println("Title: " + title);
        int x = 1;
        for(String slide: slides){
            System.out.println("Slide " + x + ": " + slide);
        }
        System.out.println("Number of slides: " + x);
        System.out.println("Date of upload: " + date);
        System.out.println("Uploaded by: " + prof.getname());
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
    public void set_video(String video){
        this.video = video;
    }
    public void view_lecture_material(){
        System.out.println("Title of video: " + title);
        System.out.println("Video file: " + video);
        System.out.println("Date of upload: " + date);
        System.out.println("Uploaded by: " + prof.getname());
    }
}

class instructor implements users{
    private String name;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    instructor(String name){
        this.name = name;
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
    public void add_lecture_material(instructor prof, lectureSlides lecSlide) throws IOException{
        System.out.println("1. Add Lecture Slide" + "\n2. Add Lecture Video");
        int choice = 0;
        choice = Integer.parseInt(br.readLine());
        if(choice==1){
            String title;
            int num, x = 1;
            ArrayList<String> content = new ArrayList<String>();
            System.out.println("Enter topic of slides: ");
            title = br.readLine();
            System.out.println("Enter number of slides: ");
            num = Integer.parseInt(br.readLine());
            System.out.println("Enter content of slides");
            while(num>0){
                System.out.println("Content of slide " + x + ": ");
                String temp = br.readLine();
                content.add(temp);
                num--;
                x++;
            }
            lecSlide.set_prof(prof);
            lecSlide.set_slides(content);
            lecSlide.set_title(title);
        }else{
            String title, content;
            lectureVids lecVid = new lectureVids();
            System.out.println("Enter topic of video: ");
            title = br.readLine();
            System.out.println("Enter filename of video: ");
            content = br.readLine();
            if(content.contains(".mp4")){
                lecVid.set_prof(prof);
                lecVid.set_video(content);
                lecVid.set_title(title);
            }
        }
    }
}