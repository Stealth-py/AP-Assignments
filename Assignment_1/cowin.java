package Assignment_1;
import java.util.*;

public class cowin{
    public ArrayList<HashMap<String, ArrayList<Integer>>> vaccines;
    private ArrayList<HashMap<String, ArrayList<Integer>>> hospitals;
    private ArrayList<HashMap<String, ArrayList<Integer>>> citizens;
    private int unique_id = 100000;

    private static Scanner sc = new Scanner(System.in);

    cowin(){
        vaccines = new ArrayList<HashMap<String, ArrayList<Integer>>>();
        hospitals = new ArrayList<HashMap<String, ArrayList<Integer>>>();
        citizens = new ArrayList<HashMap<String, ArrayList<Integer>>>();
    }

    public static void main(String[] args) {
        cowin vac = new cowin();
        vac.add_vaccine();
    }

    public void add_vaccine(){
        HashMap<String, ArrayList<Integer>> hm = new HashMap<String, ArrayList<Integer>>();
        ArrayList<Integer> ar = new ArrayList<Integer>();
        String name;
        int a, b;
        System.out.print("Vaccine Name: ");
        name = sc.nextLine();
        System.out.print("Number of Doses: ");
        a = sc.nextInt();
        System.out.print("Gap between Doses: ");
        b = sc.nextInt();
        ar.add(a);
        ar.add(b);
        hm.put(name, ar);
        vaccines.add(hm);
        System.out.println("Vaccine Name: " + name + ", Number of Doses: " + a + ", Gap Between Doses: " + b);      
    }

    public void register_hospital(){
        HashMap<String, ArrayList<Integer>> hm = new HashMap<String, ArrayList<Integer>>();
        ArrayList<Integer> ar = new ArrayList<Integer>();
        String name;
        int a;
        System.out.print("Hospital Name: ");
        name = sc.nextLine();
        System.out.print("PinCode: ");
        a = sc.nextInt();
        ar.add(a);
        ar.add(unique_id);
        hm.put(name, ar);
        hospitals.add(hm);
        System.out.println("Hospital Name: " + name + ", PinCode: " + a + ", Unique ID: " + unique_id);
        unique_id++;
    }
}
