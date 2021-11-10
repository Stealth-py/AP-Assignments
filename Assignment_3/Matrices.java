package Assignment_3;

import java.io.*;
import java.util.*;

interface common_operations{
    public void set_types(Integer type);
}

public class Matrices {
    static private HashMap<Integer, String> hm = new HashMap<>();
    static private HashMap<String, Matrix> input = new HashMap<String, Matrix>();
    static private ArrayList<Matrix> matrices = new ArrayList<>();
    static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        boolean flag = true;
        int choice = -1;
        while(flag){
            System.out.println("================================================");
            System.out.println(
                "1. Take matrices as input and label them with appropriate matrix-types.\n"+
                "2. Create matrices of requested matrix-types and label them with appropriate matrix-types.\n"+
                "3. Change the elements of a matrix as long as the fixed matrix-type labels remain valid.\n"+
                "4. Display all the matrix-type labels of a requested matrix.\n"+
                "5. Perform addition, subtraction, multiplication & division.\n"+
                "6. Perform element-wise operations.\n"+
                "7. Transpose matrices.\n"+
                "8. Inverse matrices.\n"+
                "9. Compute means: row-wise mean, column-wise mean, mean of all the elements.\n"+
                "10. Compute determinants.\n"+
                "11. Use singleton matrices as scalars, if requested.\n"+
                "12. Compute A+AT, for a matrix A.\n"+
                "13. Compute Eigen vectors and values.\n"+
                "14. Solve sets of linear equations using matrices.\n"+
                "15. Retrieve all the existing matrices (entered or created) having requested matrix-type labels.\n"
            );
            System.out.println("Enter your choice: ");
            choice = Integer.parseInt(br.readLine());
            if(choice==1){
                Matrix mat;
                ArrayList<ArrayList<Integer>> matrix;
                String t = br.readLine();
                int n = t.length();
                if((t.charAt(n-1))==']'){
                    ArrayList<Integer> temp;
                    matrix = new ArrayList<>();
                    mat = new Matrix();
                    int z = 0;
                    for(int i = 0; i<n; i++){
                        if(t.charAt(i)=='['){
                            z = i;
                            break;
                        }
                    }
                    String t1 = t.substring(z+1, n-1);
                    String[] s = t1.split(";");
                    for(String x: s){
                        temp = new ArrayList<>();
                        String[] s1 = x.split(",");
                        for(String c: s1){
                            temp.add(Integer.parseInt(c));
                        }
                        matrix.add(temp);
                    }
                    String var = t.substring(0, z-1);
                    mat.set_matrix(matrix);
                    input.put(var, mat);
                    matrices.add(mat);
                }
                System.out.println(input);
            }else if(choice==4){
                System.out.println("Which matrix do you want the type of: ");
                String var = br.readLine();
                ArrayList<Integer> types = input.get(var).get_types();
                for(Integer each: types){
                    System.out.println(get_type_string(each));
                }
            }
        }
    }

    public static String get_type_string(Integer type){
        hm.put(1, "Rectangular Matrix");
        hm.put(2, "Row Matrix");
        hm.put(3, "Column Matrix");
        hm.put(4, "Square Matrix");
        hm.put(5, "Symmetric Matrix");
        hm.put(6, "Skew-symmetric Matrix");
        hm.put(7, "Upper-triangular Matrix");
        hm.put(8, "Lower-triangular Matrix");
        hm.put(9, "Singular Matrix");
        hm.put(10, "Diagonal Matrix");
        hm.put(11, "Scalar Matrix");
        hm.put(12, "Identity Matrix");
        hm.put(13, "Singleton Matrix");
        hm.put(14, "Ones Matrix");
        hm.put(15, "Null Matrix");
        return hm.get(type);
    }
}

class Matrix implements common_operations{
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> types = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> get_matrix(){
        return this.matrix;
    }
    public void set_matrix(ArrayList<ArrayList<Integer>> mat){
        this.matrix = mat;
    }
    public ArrayList<Integer> get_types(){
        return this.types;
    }
    public void set_types(Integer type){
        this.types.add(type);
    }
}

class Operations extends Matrix{
    private Matrix mat = new Matrix();

    Operations(Matrix mat){
        this.mat = mat;
    }

    public ArrayList<ArrayList<Integer>> get_transpose(ArrayList<ArrayList<Integer>> tr){
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        int r = tr.size();
        int c = tr.get(0).size();
        for(int i = 0; i<r; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0; j<c; j++){
                temp.add(tr.get(j).get(i));
            }
            transpose.add(temp);
        }
        return transpose;
    }

    @Override
    public void set_types(Integer type){
        ArrayList<ArrayList<Integer>> matrix = mat.get_matrix();
        int r = matrix.size();
        int c = matrix.get(0).size();
        if(r==c){
            if(r==1 && c==1){
                mat.set_types(13);
            }else{
                mat.set_types(4);
                ArrayList<ArrayList<Integer>> transpose = get_transpose(matrix);
                boolean flag = true;
                for(int i = 0; i<r; i++){
                    for(int j = 0; j<c; j++){
                        if((matrix.get(i).get(j))!=transpose.get(j).get(i)){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag){
                    mat.set_types(5);
                }else{
                    flag = true;
                    for(int i = 0; i<r; i++){
                        for(int j = 0; j<c; j++){
                            if((matrix.get(i).get(j))!=((transpose.get(j).get(i))*-1)){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(flag){
                        mat.set_types(6);
                    }
                }
            }
        }else{
            mat.set_types(1);
            if(r==1){
                mat.set_types(2);
            }else{
                mat.set_types(3);
            }
        }
    }
}