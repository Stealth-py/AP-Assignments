package Assignment_1;

import java.io.*;
import java.util.*;

public class Cowin{
    public ArrayList<Vaccine> vaccines;
    private ArrayList<Hospital> hospitals;
    private HashMap<Integer, Hospital> slots;
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Cowin(){
        vaccines = new ArrayList<Vaccine>();
        hospitals = new ArrayList<Hospital>();
        slots = new HashMap<Integer, Hospital>();
    }

    public static void main(String[] args) throws IOException{
        Cowin vac = new Cowin();
        int i = 2;
        while(i>0){
            vac.add_vaccine();
            System.out.println("\n-------------------------------------");
            i--;
        }
        i = 2;
        while(i>0){
            vac.register_hospital();
            System.out.println();
            System.out.println("\n-------------------------------------");
            i--;
        }
        vac.create_slots();
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
        hp.set_hospitalname(name);
        hp.set_pincode(a);
        hospitals.add(hp);

        System.out.print("Hospital Name: " + name + ", PinCode: " + a + ", Unique ID: " + Hospital.get_uniqueid());
    }

    public void register_citizens() throws IOException{
        String name, a, b;

        System.out.print("Citizen Name: ");
        name = br.readLine();
        System.out.print("Age: ");
        a = br.readLine();
        System.out.print("Unique ID: ");
        b = br.readLine();

        System.out.println("Vaccine Name: " + name + ", Number of Doses: " + a + ", Gap Between Doses: " + b);
    }

    public void create_slots() throws IOException{
        int uid, numslots, day, quant, vac_no;
        ArrayList<Integer> tem = new ArrayList<Integer>();
        Hospital hp = new Hospital();

        System.out.print("Enter Hospital ID: ");
        uid = Integer.parseInt(br.readLine());
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
    private static Integer unique_id = 99999;
    private Integer pincode;
    private String hospitalname;
    private static ArrayList<Integer> slotlist = new ArrayList<Integer>();

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
    public static Integer get_uniqueid(){
        return unique_id;
    }
    public void set_slotlist(ArrayList<Integer> slot){
        Hospital.slotlist = slot;
    }
    public static ArrayList<Integer> get_slotlist(){
        return slotlist;
    }
}