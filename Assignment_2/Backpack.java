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
    public void view_comments(ArrayList<comments> com);
    public comments add_comments() throws IOException;
    public boolean logout();
}


public class Backpack {
    private static ArrayList<students> student = new ArrayList<students>();
    private static ArrayList<instructor> instructors = new ArrayList<instructor>();
    private static ArrayList<lectureMats> lecmats = new ArrayList<lectureMats>();
    private static ArrayList<assessments> assessment = new ArrayList<assessments>();
    private static ArrayList<comments> comment = new ArrayList<comments>();
    private static HashMap<Integer, HashMap<students, Integer>> graded = new HashMap<>();
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
                int cf = 0;
                boolean logged = true;
                System.out.println("Instructors:");
                System.out.println("0 - i0");
                System.out.println("1 - i1");
                System.out.println("Choose id: ");
                cf = Integer.parseInt(br.readLine());
                while(logged){
                    flag = true;
                    instructor i0 = new instructor("i0");
                    instructor i1 = new instructor("i1");
                    instructors.add(i0);
                    instructors.add(i1);

                    instructor curr = instructors.get(cf);
                    curr.login();
                    int c = Integer.parseInt(br.readLine());
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
                            assessment.add(y);
                        }else if(x==2){
                            quizzes y = new quizzes();
                            System.out.println("Enter quiz question: ");
                            String state = br.readLine();
                            curr.add_quiz(state, assessment.size(), y);
                            assessment.add(y);
                        }
                    }else if(c==3){
                        curr.view_lecture_materials(lecmats);
                    }else if(c==4){
                        curr.view_assessments(assessment);
                    }else if(c==5){
                        graded = curr.grade_assessments(assessment, graded);
                    }else if(c==6){
                        assessment = curr.close_assessment(assessment);
                    }else if(c==7){
                        curr.view_comments(comment);
                    }else if(c==8){
                        comments tmp = curr.add_comments();
                        comment.add(tmp);
                    }else{
                        logged = curr.logout();
                    }
                }
            }else if(choice==2){
                boolean logged = true;
                int cf = 0;
                System.out.println("Students:");
                System.out.println("0 - s0");
                System.out.println("1 - s1");
                System.out.println("2 - s2");
                System.out.println("Choose id: ");
                cf = Integer.parseInt(br.readLine());
                while(logged){
                    flag = true;
                    students s0 = new students("s0");
                    students s1 = new students("s1");
                    students s2 = new students("s2");
                    student.add(s0);
                    student.add(s1);
                    student.add(s2);

                    students curr = student.get(cf);
                    curr.login();
                    int c = Integer.parseInt(br.readLine());
                    if(c==1){
                        curr.view_lecture_materials(lecmats);
                    }else if(c==2){
                        curr.view_assessments(assessment);
                    }else if(c==3){
                        curr.submit_assessments(assessment);
                    }else if(c==4){
                        System.out.println("Graded submissions");
                        ArrayList<Integer> sub_graded = curr.get_sub_graded();
                        if(sub_graded.size()==0){
                            System.err.println("No graded submissions!");
                        }else{
                            HashMap<Integer, String> hm = curr.get_submission_hm();
                            for(Integer id: sub_graded){
                                System.out.println("Submission: " + hm.get(id));
                                HashMap<students, Integer> hmm = graded.get(id);
                                System.out.println("Marks scored: " + hmm.get(curr));
                                HashMap<Integer, String> whograded = curr.get_whograded();
                                System.out.println("Graded by: " + whograded.get(id));
                            }
                        }
                        System.out.println("Ungraded submissions");
                        HashMap<Integer, String> hm = curr.get_submission_hm();
                        for(Integer id: curr.get_submission()){
                            if(!(sub_graded.contains(id))){
                                System.out.println("Submission: " + hm.get(id));
                            }
                        }
                    }else if(c==5){
                        curr.view_comments(comment);
                    }else if(c==6){
                        comments tmp = curr.add_comments();
                        comment.add(tmp);
                    }else{
                        logged = curr.logout();
                    }
                }
            }
        }
    }
}

class comments{
    private String cmnt, name;
    Date date;

    comments(){
        date = new Date();
    }

    public String get_comment(){
        return this.cmnt;
    }
    public void set_comment(String cmnt){
        this.cmnt = cmnt;
    }
    public String get_author(){
        return this.name;
    }
    public void set_author(String name){
        this.name = name;
    }
    public Date get_date(){
        return this.date;
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

class quizzes implements assessments{
    private String statement, type = "quiz";
    private int max_marks = 1, id;
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
        return 1;
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
    public void add_quiz(String state, Integer id, quizzes q){
        q.set_id(id);
        q.set_statement(state);
        this.added.add(q);
    }
    public void view_assessments(ArrayList<assessments> assessment){
        for(assessments each: assessment){
            if(!(each.is_closed())){
                each.view_assessments();
            }
        }
    }
    public HashMap<Integer, HashMap<students, Integer>> grade_assessments(ArrayList<assessments> arr, HashMap<Integer, HashMap<students, Integer>> graded) throws IOException{
        System.out.println("List of assessments");
        if(arr.size()>0){
            for(assessments each: arr){
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
                return graded;
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
                HashMap<students, Integer> hMap = new HashMap<>();
                hMap.put(tmp, scored);
                graded.put(ch, hMap);
                tmp.add_sub_graded(ch);
                tmp.set_whograded(ch, this.name);
                return graded;
            }
        }else{
            System.out.println("No pending assessments");
            return graded;
        }
    }
    public ArrayList<assessments> close_assessment(ArrayList<assessments> arr) throws IOException{
        System.out.println("List of open assignments:");
        for(assessments each: arr){
            if(!(each.is_closed())){
                each.view_assessments();
            }
        }
        System.out.println("Enter id of assessment to close: ");
        int id = Integer.parseInt(br.readLine());
        assessments cur = arr.get(id);
        cur.close_assessment();
        
        ArrayList<assessments> temp = new ArrayList<assessments>();
        int x = 0;
        for(assessments each: arr){
            if(x==id){
                temp.add(cur);
            }else{
                temp.add(each);
            }
        }
        return temp;
    }
    public void view_comments(ArrayList<comments> com){
        for(comments each: com){
            System.out.println(each.get_comment() + " - " + each.get_author());
            System.out.println(each.get_date());
        }
    }
    public comments add_comments() throws IOException{
        System.out.println("Enter comment: ");
        String txt = br.readLine();
        comments com = new comments();
        com.set_comment(txt);
        com.set_author(this.name);
        return com;
    }
    public boolean logout(){
        return false;
    }
}

class students implements users{
    private String name;
    ArrayList<Integer> submitted = new ArrayList<Integer>();
    ArrayList<Integer> sub_grade = new ArrayList<Integer>();
    HashMap<Integer, String> submission = new HashMap<>();
    HashMap<Integer, String> who_graded = new HashMap<>();
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    students(String name){
        this.name = name;
    }
    
    public HashMap<Integer, String> get_whograded(){
        return this.who_graded;
    }
    public void set_whograded(Integer id, String name){
        this.who_graded.put(id, name);
    }
    public ArrayList<Integer> get_sub_graded(){
        return sub_grade;
    }
    public void add_sub_graded(Integer id){
        this.sub_grade.add(id);
    }
    public HashMap<Integer, String> get_submission_hm(){
        return submission;
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
                    submission.put(id, filename);
                    return true;
                }else{
                    return false;
                }
            }else{
                System.out.println(a.get_statement());
                String ans = br.readLine();
                submitted.add(id);
                submission.put(id, ans);
                return true;
            }
        }
    }
    public void view_comments(ArrayList<comments> com){
        for(comments each: com){
            System.out.println(each.get_comment() + " - " + each.get_author());
            System.out.println(each.get_date());
        }
    }
    public comments add_comments() throws IOException{
        System.out.println("Enter comment: ");
        String txt = br.readLine();
        comments com = new comments();
        com.set_comment(txt);
        com.set_author(this.name);
        return com;
    }
    public boolean logout(){
        return false;
    }
    public ArrayList<Integer> get_submission(){
        return submitted;
    }
}