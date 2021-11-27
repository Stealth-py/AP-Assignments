package Assignment_4;

import java.io.*;
import java.util.*;

abstract class Helper<T>{
    private ArrayList<T> to_be_sorted;
    Helper(ArrayList<T> obj, int n){
        this.to_be_sorted = obj;
    }
    public boolean check_duplicate(){
        Set<T> set = new HashSet<T>(to_be_sorted);
        return set.size()!=to_be_sorted.size();
    }
    public ArrayList<T> get_list(){
        return this.to_be_sorted;
    }
    abstract ArrayList<Integer> sort_obj(ArrayList<Integer> indices);
}

public class Library {
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<String> isbns = new ArrayList<>();
    private static ArrayList<String> barcodes = new ArrayList<>();
    private static ArrayList<Book> books = new ArrayList<>();
    private static int n = 0;
    private static int k = 0;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        ArrayList<Integer> indices = new ArrayList<>();
        Book b;
        while(true){
            System.out.println("Enter the number of books: ");
            n = Integer.parseInt(br.readLine());
            System.out.println("Enter the number of racks: ");
            k = Integer.parseInt(br.readLine());
            if(n%k>0 || k==0 || n==0){
                System.out.println("Please enter a valid number of racks(should be a multiple of n), the number of books and racks should not be 0");
            }else{
                break;
            }
        }
        System.out.println("Enter the names of the books, their isbn and the barcode, separated by space:");
        for(int i = 0; i<n; i++){
            System.out.println("Enter(Format -> <NAME> <ISBN> <BARCODE>");
            String[] s = br.readLine().split(" ");
            names.add(s[0]);
            isbns.add(s[1]);
            barcodes.add(s[2]);
            indices.add(i);
            b = new Book(s[0], s[1], s[2]);
            books.add(b);
        }
        Helper<String> help = new Helper<String>(names, n){
            public ArrayList<Integer> sort_obj(ArrayList<Integer> x){
                ArrayList<String> hp = get_list();
                Collections.sort(
                    x,
                    new Comparator<Integer>() {
                        public int compare(Integer i1, Integer i2){
                            return hp.get(i1).compareTo(hp.get(i2));
                        }
                    }
                );
                return x;
            }
        };
        if(help.check_duplicate()){
            help = new Helper<String>(names, n){
                public ArrayList<Integer> sort_obj(ArrayList<Integer> x){
                    ArrayList<String> hp = get_list();
                    Collections.sort(
                        x,
                        new Comparator<Integer>() {
                            public int compare(Integer i1, Integer i2){
                                return hp.get(i1).compareTo(hp.get(i2));
                            }
                        }
                    );
                    return x;
                }
            };
            if(help.check_duplicate()){
                help = new Helper<String>(names, n){
                    public ArrayList<Integer> sort_obj(ArrayList<Integer> x){
                        ArrayList<String> hp = get_list();
                        Collections.sort(
                            x,
                            new Comparator<Integer>() {
                                public int compare(Integer i1, Integer i2){
                                    return hp.get(i1).compareTo(hp.get(i2));
                                }
                            }
                        );
                        return x;
                    }
                };
                indices = help.sort_obj(indices);
                print_books_in_order(indices);
            }else{
                indices = help.sort_obj(indices);
                print_books_in_order(indices);
            }
        }else{
            indices = help.sort_obj(indices);
            print_books_in_order(indices);
        }
    }
    public static void print_books_in_order(ArrayList<Integer> indices){
        int c = 0;
        ArrayList<Book> tempbooks = new ArrayList<>();
        for(int i = 0; i<n; i+=k){
            System.out.println("Rack " + ((int)c+1));
            for(int j = i; j<i+k; j++){
                System.out.println(names.get(indices.get(j)) + " -> " + isbns.get(indices.get(j)) + " -> " + barcodes.get(indices.get(j)));
                tempbooks.add(new Book(names.get(indices.get(j)), isbns.get(indices.get(j)), barcodes.get(indices.get(j))));
            }
            c+=1;
        }
        books = tempbooks;
    }
}

class Book{
    private String name;
    private String isbn;
    private String barcode;
    
    Book(String name, String isbn, String barcode){
        this.name = name;
        this.isbn = isbn;
        this.barcode = barcode;
    }

    public String get_name(){
        return this.name;
    }
    public String get_isbn(){
        return this.isbn;
    }
    public String get_barcode(){
        return this.barcode;
    }
}