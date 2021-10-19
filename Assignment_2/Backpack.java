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
    public String get_statement();
    public instructor get_inst();
    public Integer get_max();
    public void view_assessments();
    public void close_assessment();
    public Integer get_id();
    public boolean is_closed();
    public String get_type();
    public void submit(students s, String sub);
    public ArrayList<HashMap<students, String>> get_submissions();
}

interface users{
    public String getname();
    public void login();
    public void view_lecture_materials(ArrayList<lectureMats> lec);
    // public void logout();
    // public void view_comments();
    // public void add_comments();
}


public class Backpack {
    private static ArrayList<instructor> instructors = new ArrayList<instructor>();
    private static ArrayList<lectureMats> lecmats = new ArrayList<lectureMats>();
    private static ArrayList<assessments> assessment = new ArrayList<assessments>();
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
                boolean logged = true;
                while(logged){
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
                    curr.login();
                    c = Integer.parseInt(br.readLine());
                    if(c==1){
                        System.out.println("1. Add Lecture Slide" + "\n2. Add Lecture Video");
                        int x = Integer.parseInt(br.readLine());
                        if(x==1){
                            lectureSlides lecSlide = new lectureSlides();
                            curr.add_lecture_slides(curr, lecSlide);
                            lecmats.add(lecSlide);
                        }else if(x==2){
                            lectureVids lecVid = new lectureVids();
                            boolean f = curr.add_lecture_videos(curr, lecVid);
                            if(!(f)){
                                System.out.println("Please upload the video file with the correct format, i.e., .mp4");
                            }else{
                                lecmats.add(lecVid);
                            }
                        }
                    }else if(c==2){
                        System.out.println("1. Add Assignment");
                        System.err.println("2. Add Quiz");
                        int x = Integer.parseInt(br.readLine());
                        if(x==1){
                            assignments y = new assignments();
                            System.out.println("Enter problem statement: ");
                            String state = br.readLine();
                            System.out.println("Enter max marks: ");
                            int max = Integer.parseInt(br.readLine());
                            curr.add_assignment(state, max, assessment.size(), y);
                        }
                    }else if(c==3){
                        curr.view_lecture_materials(lecmats);
                    }else if(c==4){
                        
                    }
                }
            }
        }
    }
}

class assignments implements assessments{
    private String statement, type = "assign";
    private int max_marks, id;
    boolean closed = false;
    private instructor prof;
    ArrayList<HashMap<students, String>> submissions = new ArrayList<HashMap<students, String>>();

    public Integer get_id(){
        return this.id;
    }
    public String get_type(){
        return this.type;
    }
    public void set_id(Integer id){
        this.id = id;
    }
    public String get_statement(){
        return this.statement;
    }
    public void set_statement(String state){
        this.statement = state;
    }
    public Integer get_max(){
        return this.max_marks;
    }
    public void set_max(int max){
        this.max_marks = max;
    }
    public instructor get_inst(){
        return this.prof;
    }
    public void set_prof(instructor prof){
        this.prof = prof;
    }
    public void view_assessments(){
        System.err.println("Assignment: " + this.statement + " || Max Marks: " + this.max_marks);
    }
    public void close_assessment(){
        this.closed = true;
    }
    public boolean is_closed(){
        return this.closed;
    }
    public void submit(students s, String sub){
        HashMap<students, String> hm = new HashMap<students, String>();
        hm.put(s, sub);
        submissions.add(hm);
    }
    public ArrayList<HashMap<students, String>> get_submissions(){
        return this.submissions;
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
    private String title, video;
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
    ArrayList<assessments> added = new ArrayList<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    instructor(String name){
        this.name = name;
    }

    public String getname(){
        return this.name;
    }
    public void login(){
        System.out.println("Welcome " + this.name);
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
    public void add_lecture_slides(instructor prof, lectureSlides lecSlide) throws IOException{
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
    }
    public boolean add_lecture_videos(instructor prof, lectureVids lecVid) throws IOException{
        String title, content;
        System.out.println("Enter topic of video: ");
        title = br.readLine();
        System.out.println("Enter filename of video: ");
        content = br.readLine();
        if(content.contains(".mp4")){
            lecVid.set_prof(prof);
            lecVid.set_video(content);
            lecVid.set_title(title);
            return true;
        }
        return false;
    }
    public void view_lecture_materials(ArrayList<lectureMats> lecmat){
        for(lectureMats each: lecmat){
            each.view_lecture_material();
        }
    }
    public void add_assignment(String state, Integer max, Integer id, assignments a){
        a.set_id(id);
        a.set_max(max);
        a.set_statement(state);
        this.added.add(a);
    }
    public void grade_assessments(ArrayList<assignments> arr) throws IOException{
        System.out.println("List of assessments");
        if(arr.size()>0){
            for(assignments each: arr){
                if(each.get_type().equals("assign")){
                    System.out.println("ID: " + each.get_id() + " || Assignment: " + each.get_statement() + " || Max Marks: " + each.get_max());
                }else{
                    System.out.println("ID: " + each.get_id() + " || Quiz: " + each.get_statement() + " || Max Marks: " + each.get_max());
                }
                System.out.println("------------------------------------------------------------");
            }
            System.out.println("Enter ID of assessment to view submissions: ");
            int id = Integer.parseInt(br.readLine());
            assessments ass = arr.get(id);
            System.out.println("Choose ID from these ungraded submissions");
            ArrayList<HashMap<students, String>> subs = ass.get_submissions();
            if(subs.size()==0){
                System.out.println("No submission found");
            }else{
                int b = 0, ch;
                for(HashMap<students, String> hm: subs){
                    for(students key: hm.keySet()){
                        String s = hm.get(key);
                        System.out.println(b + ". " + s);
                    }
                    b++;
                }
                ch = Integer.parseInt(br.readLine());
                HashMap<students, String> selected = subs.get(ch);
                students tmp = new students("x");
                for(students key: selected.keySet()){
                    tmp = key;
                }
                System.out.println("Submission: " + selected.get(tmp));
                System.out.println("------------------------------------------------------------");
                System.out.println("Max Marks: " + ass.get_max());
                System.out.println("Marks scored: ");
                int scored = Integer.parseInt(br.readLine());
            }
        }else{
            System.out.println("No pending assessments");
        }
    }
}

class students implements users{
    private String name;
    ArrayList<Integer> submitted = new ArrayList<Integer>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    students(String name){
        this.name = name;
    }
    
    public String getname(){
        return this.name;
    }
    public void login(){
        System.out.println("Welcome " + this.name);
        System.out.println("STUDENT MENU");
        System.out.println("1. View lecture materials");
        System.out.println("2. View assessments");
        System.out.println("3. Submit assessment");
        System.out.println("4. View grades");
        System.out.println("5. View comments");
        System.out.println("6. Add comments");
        System.out.println("7. Logout");
    }
    public void view_lecture_materials(ArrayList<lectureMats> lecmat){
        for(lectureMats each: lecmat){
            each.view_lecture_material();
        }
    }
    public void view_assessments(ArrayList<assessments> assessment){
        for(assessments each: assessment){
            if(!(each.is_closed())){
                each.view_assessments();
            }
        }
    }
    public boolean submit_assessments(ArrayList<assessments> ass) throws IOException{
        int c = -1;
        for(assessments each: ass){
            if(!(submitted.contains(each.get_id()))){
                if(!(each.is_closed())){
                    System.out.println("ID: " + each.get_id() + " || Question: " + each.get_statement());
                    c = 1;
                }
            }
        }
        if(c==-1){
            System.out.println("No pending assignments");
            return false;
        }else{
            System.out.println("Enter ID of assessment: ");
            int id = Integer.parseInt(br.readLine());
            assessments a = ass.get(id);
            if(a.get_type().equals("assign")){
                System.out.println("Enter filename of assignment: ");
                String filename = br.readLine();
                if(filename.contains(".zip")){
                    submitted.add(id);
                    return true;
                }else{
                    return false;
                }
            }else{
                //quiz stuff
                return true;
            }
        }
    }
}