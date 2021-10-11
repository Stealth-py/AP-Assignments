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
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Cowin(){
        vaccines = new ArrayList<Vaccine>();
        hospitals = new ArrayList<Hospital>();
        citizens = new ArrayList<Citizen>();
        slots = new HashMap<Integer, Hospital>();
        booked_slot = new HashMap<String, Citizen>();
    }

    public static void main(String[] args) throws IOException{
        Cowin vac = new Cowin();
        int choice;
        System.out.println("CoWin Portal Initialised....");
        System.out.print("---------------------------------");
        while(true){
            System.out.println("1. Add Vaccine");
            System.out.println("2. Register Hospital");
            System.out.println("3. Register Citizen");
            System.out.println("4. Add Slot for Vaccination");
            System.out.println("5. Book Slot for Vaccination");
            System.out.println("6. List all slots for a hospital");
            System.out.println("7. Check Vaccination Status");
            System.out.println("8. Exit");
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(br.readLine());
            if(choice==1){
                vac.add_vaccine();
            }else if(choice==2){
                vac.register_hospital();
            }else if(choice==3){
                vac.register_citizens();
            }else if(choice==4){
                vac.create_slots();
            }else if(choice==5){
                vac.book_slot();
            }else if(choice==6){
                vac.available_slots();
            }else if(choice==7){
                vac.vaccination_status();
            }else if(choice==8){
                System.out.print("---------------------------------");
                break;
            }
            System.out.print("---------------------------------");
        }
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
        }else if(ct.get_status().equals("REGISTERED") || ct.get_status().equals("PARTIALLY VACCINATED") || ct.get_status().equals("FULLY VACCINATED")){
            System.out.println("You have already registered, or have been vaccinated. If you haven't been vaccinated yet, kindly proceed to booking a slot.");
        }else{
            ct.set_age(a);
            ct.set_citizenname(name);
            ct.set_uid(b);
            citizens.add(ct);
            ct.set_status("REGISTERED");
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
        String uid, name;
        int choice, slotchoice, pincode, hpid, qty;
        Hospital hp;
        Citizen citiz = new Citizen();
        ArrayList<Integer> cur = new ArrayList<Integer>();
        ArrayList<Integer> tem = new ArrayList<Integer>();

        System.out.println("Enter patient Unique ID: ");
        uid = br.readLine();
        System.out.println("1. Search by area");
        System.out.println("2. Search by Vaccine");
        System.out.println("3. Exit");
        System.out.println("Enter option: ");
        choice = Integer.parseInt(br.readLine());

        if(choice==1){
            System.out.println("Enter PinCode: ");
            pincode = Integer.parseInt(br.readLine());
            for(Hospital hosp: hospitals){
                if(hosp.get_pincode()==pincode){
                    System.out.print(hosp.get_uniqueid() + " " + hosp.get_hospitalname());
                    break;
                }
            }
        }else if(choice == 2){
            System.out.println("Enter Vaccine name: ");
            name = br.readLine();
            for(Hospital temp: hospitals){
                for(ArrayList<Integer> curr: temp.get_slotlist()){
                    if(vaccines.get(curr.get(2)).get_vacname().equals(name)){
                        System.out.println(temp.get_uniqueid() + " " + temp.get_hospitalname());
                        break;
                    }
                }
            }
        }

        if(choice == 1 || choice == 2){
            System.out.println("Enter hospital id: ");
            hpid = Integer.parseInt(br.readLine());
            hp = slots.get(hpid);
            ArrayList<ArrayList<Integer>> slot = hp.get_slotlist();
            int c = 0;
            if(slot.size()>0){
                for(ArrayList<Integer> curr: slot){
                    System.out.println(c + "-> Day: " + curr.get(0) + ", Available Qty: " + curr.get(1) + ", Vaccine: " + vaccines.get(curr.get(2)).get_vacname());
                    c++;
                }
                System.out.println("Choose Slot: ");
                slotchoice = Integer.parseInt(br.readLine());
                for(Citizen ct: citizens){
                    if(ct.get_uid().equals(uid)){
                        citiz = ct;
                        break;
                    }
                }
                System.out.println(citiz.get_citizenname() + " vaccinated with " + vaccines.get(slot.get(slotchoice).get(2)).get_vacname());
                if(citiz.get_status().equals("REGISTERED")){
                    citiz.set_status("PARTIALLY VACCINATED");
                    citiz.set_doses(1);
                    if(vaccines.get(slot.get(slotchoice).get(2)).get_doses()==1){
                        citiz.set_status("FULLY VACCINATED");
                    }
                }else if(citiz.get_status().equals("PARTIALLY VACCINATED")){
                    int doz = citiz.get_doses();
                    doz++;
                    citiz.set_doses(doz);
                    if(doz==vaccines.get(slot.get(slotchoice).get(2)).get_doses()){
                        citiz.set_status("FULLY VACCINATED");
                    }
                }
                citiz.set_received(vaccines.get(slot.get(slotchoice).get(2)).get_vacname());
                booked_slot.put(uid, citiz);

                cur = slot.get(slotchoice);
                qty = cur.get(1);
                if(qty>0)   qty--;
                tem.add(cur.get(0));
                tem.add(qty);
                tem.add(cur.get(2));
                hp.set_slotlist(tem);

                citiz.set_day(tem.get(0));
            }else{
                System.out.println("No slots available for this hospital");
            }
        }
    }

    public void available_slots() throws IOException{
        int uid;
        Hospital curr;
        System.out.println("Enter Hospital ID: ");
        uid = Integer.parseInt(br.readLine());
        curr = slots.get(uid);
        for(ArrayList<Integer> arr: curr.get_slotlist()){
            System.out.println("Day: " + arr.get(0) + ", Vaccine: " + vaccines.get(arr.get(2)).get_vacname() + ", Available Qty: " + arr.get(1));
        }
    }

    public void vaccination_status() throws IOException{
        String uid;
        Citizen ct = new Citizen();
        System.out.println("Enter Patient ID: ");
        uid = br.readLine();
        for(Citizen temp: citizens){
            if(temp.get_uid().equals(uid)){
                ct = temp;
                break;
            }
        }
        if(ct.get_status().equals("REGISTERED")){
            System.out.println("Citizen REGISTERED");
        }else if(ct.get_status().equals("PARTIALLY VACCINATED")){
            System.out.println(ct.get_status());
            System.out.println("Vaccine Given: " + ct.get_received());
            System.out.println("Number of doses given: " + ct.get_doses());
            for(Vaccine vac: vaccines){
                if(vac.get_vacname().equals(ct.get_received())){
                    int res = vac.get_gaps() + ct.get_day();
                    System.out.println("Next dose due date: " + res);
                    break;
                }
            }
        }else{
            System.out.println(ct.get_status());
            System.out.println("Vaccine Given: " + ct.get_received());
            System.out.println("Number of doses given: " + ct.get_doses());
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
    private String citizename, uid, status, vac_received;
    private Integer age, daynum, dosesgiven;

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
    public void set_received(String dose){
        this.vac_received = dose;
    }
    public String get_received(){
        return vac_received;
    }
    public String get_status(){
        return status;
    }
    public void set_status(String status){
        this.status = status;
    }
    public Integer get_day(){
        return daynum;
    }
    public void set_day(int day){
        this.daynum = day;
    }
    public Integer get_doses(){
        return dosesgiven;
    }
    public void set_doses(int doses){
        this.dosesgiven = doses;
    }
}