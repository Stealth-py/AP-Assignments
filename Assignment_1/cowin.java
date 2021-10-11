package Assignment_1;

import java.io.*;
import java.util.*;

public class Cowin{
    private Integer hospital_unique_id = 100000;
    private ArrayList<Vaccine> vaccines;
    private ArrayList<Hospital> hospitals;
    private ArrayList<Citizen> citizens;
    private HashMap<Integer, Hospital> slots;
    private HashMap<String, Citizen> booked_slot;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Cowin(){
        vaccines = new ArrayList<Vaccine>();
        hospitals = new ArrayList<Hospital>();
        citizens = new ArrayList<Citizen>();
        slots = new HashMap<Integer, Hospital>();
        booked_slot = new HashMap<String, Citizen>();
    }

    public static void main(String[] args) throws IOException{
        Cowin vac = new Cowin();
    }

    public void add_vaccine() throws IOException{
        String name;
        int a, b = 0;
        Vaccine vc = new Vaccine();

        System.out.print("Vaccine Name: ");
        name = br.readLine();
        System.out.print("Number of Doses: ");
        a = Integer.parseInt(br.readLine());
        if(a>1){
            System.out.print("Gap between Doses: ");
            b = Integer.parseInt(br.readLine());
        }

        System.out.println("Vaccine Name: " + name + ", Number of Doses: " + a + ", Gap Between Doses: " + b);
        vc.set_vacname(name);
        vc.set_doses(a);
        vc.set_dosesgap(b);
        vaccines.add(vc);
    }

    public void register_hospital() throws IOException{
        String name;
        int a;
        Hospital hp = new Hospital();

        System.out.print("Hospital Name: ");
        name = br.readLine();
        System.out.print("\nPinCode: ");
        a = Integer.parseInt(br.readLine());

        System.out.print("Hospital Name: " + name + ", PinCode: " + a + ", Unique ID: " + hospital_unique_id);
        hp.set_hospitalname(name);
        hp.set_pincode(a);
        hp.set_uniqueid(hospital_unique_id);
        hospitals.add(hp);
        hospital_unique_id++;
    }

    public void register_citizens() throws IOException{
        String name, b;
        int a;
        Citizen ct = new Citizen();

        System.out.print("Citizen Name: ");
        name = br.readLine();
        System.out.print("Age: ");
        a = Integer.parseInt(br.readLine());
        System.out.print("Unique ID: ");
        b = br.readLine();

        System.out.println("Citizen Name: " + name + ", Age: " + a + ", Unique ID: " + b);
        if(a<18){
            System.out.println("Only above 18 are allowed");
        }else{
            ct.set_age(a);
            ct.set_citizenname(name);
            ct.set_uid(b);
            citizens.add(ct);
        }
    }

    public void create_slots() throws IOException{
        int uid, numslots, day, quant, vac_no;
        ArrayList<Integer> tem = new ArrayList<Integer>();
        Hospital hp = new Hospital();

        System.out.print("Enter Hospital ID: ");
        uid = Integer.parseInt(br.readLine());
        for(Hospital hosp: hospitals){
            if(hosp.get_uniqueid()==uid){
                hp = hosp;
                break;
            }
        }
        System.out.print("Enter number of Slots to be added: ");
        numslots = Integer.parseInt(br.readLine());
        while(numslots>0){
            int i = 0;
            System.out.print("Enter Day Number: ");
            day = Integer.parseInt(br.readLine());
            System.out.print("Enter Quantity: ");
            quant = Integer.parseInt(br.readLine());
            System.out.print("Select Vaccine: ");
            for(Vaccine temp: vaccines){
                System.out.print("\n" + i + ". " + temp.get_vacname());
                i++;
            }
            vac_no = Integer.parseInt(br.readLine());

            tem.add(day);
            tem.add(quant);
            tem.add(vac_no);
            hp.set_slotlist(tem);

            System.out.println("Slot added by: " + uid + " for Day " + day + ", Available Quantity: " + quant + " of Vaccine " + vaccines.get(vac_no).get_vacname());
            numslots--;
        }
        slots.put(uid, hp);
        System.out.println(slots);
    }

    public void book_slot() throws IOException{
        String uid;
        int choice;
        Hospital hp;

        System.out.println("Enter patient Unique ID: ");
        uid = br.readLine();
        System.out.println("1. Search by area");
        System.out.println("2. Search by Vaccine");
        System.out.println("3. Exit");
        System.out.println("Enter option: ");
        choice = Integer.parseInt(br.readLine());

        if(choice==1){
            int pincode, hpid;
            System.out.println("Enter PinCode: ");
            pincode = Integer.parseInt(br.readLine());
            for(Hospital hosp: hospitals){
                if(hosp.get_pincode()==pincode){
                    System.out.print(hosp.get_uniqueid() + " " + hosp.get_hospitalname());
                }
            }
            System.out.println("Enter Unique ID: ");
            hpid = Integer.parseInt(br.readLine());
            hp = slots.get(hpid);
            ArrayList<ArrayList<Integer>> slot = hp.get_slotlist();
        }
    }
}

class Vaccine{
    private Integer doses, dosesgap;
    private String vac_name;

    public Integer get_doses(){
        return doses;
    }
    public void set_doses(Integer doses){
        this.doses = doses;
    }
    public Integer get_gaps(){
        return dosesgap;
    }
    public void set_dosesgap(Integer dosesgap){
        this.dosesgap = dosesgap;
    }
    public String get_vacname(){
        return vac_name;
    }
    public void set_vacname(String vac_name){
        this.vac_name = vac_name;
    }
}

class Hospital{
    private Integer unique_id, pincode;
    private String hospitalname;
    private ArrayList<ArrayList<Integer>> slotlist = new ArrayList<ArrayList<Integer>>();

    public Integer get_pincode(){
        return pincode;
    }
    public void set_pincode(Integer pincode){
        this.pincode = pincode;
    }
    public String get_hospitalname(){
        return hospitalname;
    }
    public void set_hospitalname(String hospitalname){
        this.hospitalname = hospitalname;
    }
    public Integer get_uniqueid(){
        return unique_id;
    }
    public void set_uniqueid(Integer uid){
        this.unique_id = uid;
    }
    public void set_slotlist(ArrayList<Integer> slot){
        this.slotlist.add(slot);
    }
    public ArrayList<ArrayList<Integer>> get_slotlist(){
        return slotlist;
    }
}

class Citizen{
    private String citizename, uid;
    private Integer age;
    private static ArrayList<Integer> received = new ArrayList<Integer>();

    public String get_citizenname(){
        return citizename;
    }
    public void set_citizenname(String name){
        this.citizename = name;
    }
    public String get_uid(){
        return uid;
    }
    public void set_uid(String uid){
        this.uid = uid;
    }
    public Integer get_age(){
        return age;
    }
    public void set_age(Integer age){
        this.age = age;
    }
    public void set_received(ArrayList<Integer> dose){
        Citizen.received = dose;
    }
    public static ArrayList<Integer> get_received(){
        return received;
    }
}