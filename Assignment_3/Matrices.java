package Assignment_3;

import java.io.*;
import java.util.*;

public class Matrices {
    static private HashMap<Integer, String> hm = new HashMap<>();
    static private HashMap<String, Matrix> input = new HashMap<String, Matrix>();
    static private ArrayList<Matrix> matrices = new ArrayList<>();
    static private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        boolean flag = true;
        int choice = -1;
        Matrix mat = new Matrix();
        while(flag){
            Operations op = new Operations();
            mat = new Matrix();
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
                System.out.println("Enter the matrix, follow this format -> a=[1,2;3,4]");

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
                    op.set_types(mat);
                    input.put(var, mat);
                    matrices.add(mat);
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==2){
                System.out.println("What type of matrix do you want, enter an integer input from 1-15: ");
                int ch = Integer.parseInt(br.readLine());
                System.out.println("What should be the variable name for the matrix: ");
                String var = br.readLine();

                mat.set_matrix(op.get_a_matrix(ch));
                op.set_types(mat);
                matrices.add(mat);
                input.put(var, mat);
                System.out.println("-------------------------------------------------------");
            }else if(choice==3){
                ArrayList<ArrayList<Integer>> m;
                ArrayList<ArrayList<Integer>> m1 = new ArrayList<>();
                System.out.println("Which matrix do you want to change: ");
                String var = br.readLine();
                mat = input.get(var);
                m = mat.get_matrix();
                ArrayList<Integer> m_types = mat.get_types();

                Matrix mat1 = new Matrix();
                System.out.println("Enter the edited matrix: ");
                String t = br.readLine();
                int n = t.length();
                if((t.charAt(n-1))==']'){
                    ArrayList<Integer> temp;
                    m1 = new ArrayList<>();
                    mat1 = new Matrix();
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
                        m1.add(temp);
                    }
                }
                mat1.set_matrix(m1);
                op.set_types(mat1);
                ArrayList<Integer> m1_types = mat1.get_types();
                Collections.sort(m_types);
                Collections.sort(m1_types);
                if(m_types.equals(m1_types)){
                    input.put(var, mat1);
                    System.out.println("Both matrices have the same types, hence, the matrix has been edited successfully");
                    mat1.print_matrix();
                }else{
                    System.out.println("The edited matrix has types different from the original matrix, hence, it wasn't overwritten.");
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==4){
                System.out.println("Which matrix do you want the type of: ");
                String var = br.readLine();

                ArrayList<Integer> types = input.get(var).get_types();
                for(Integer each: types){
                    System.out.println(get_type_string(each));
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==5){
                System.out.println("Which matrices do you want to perform the functions on(example: a b, separated by a space and in the required order)");
                String[] s3 = br.readLine().split(" ");
                String var1 = s3[0], var2 = s3[1];
                Matrix mat1 = input.get(var1), mat2 = input.get(var2);
                System.out.println("Which operation?\n 1) Addition\n 2) Subtraction\n 3) Multiplication\n 4) Division");
                int ch = Integer.parseInt(br.readLine());

                ArrayList<Integer> types = mat1.get_types();
                ArrayList<Integer> types1 = mat2.get_types();
                ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                ArrayList<ArrayList<Integer>> m2 = mat2.get_matrix();

                if(types.get(types.size()-1)==15){
                    if(ch==3){
                        for(int i = 0; i<m2.size(); i++){
                            for(int j = 0; j<m2.get(0).size(); j++){
                                System.out.print("0 ");
                            }
                            System.out.print("\n");
                        }
                    }else if(ch==4){
                        System.out.println("Can't perform this operation using a null matrix");
                    }
                }else if(types1.get(types1.size()-1)==15){
                    if(ch==3){
                        for(int i = 0; i<m1.size(); i++){
                            for(int j = 0; j<m1.get(0).size(); j++){
                                System.out.print("0 ");
                            }
                            System.out.print("\n");
                        }
                    }else if(ch==4){
                        System.out.println("Can't perform this operation using a null matrix");
                    }
                }else{
                    ArrayList<ArrayList<Double>> res = new ArrayList<>();
                    if(ch==1 || ch==2){
                        if(m1.size()==m2.size() && m1.get(0).size()==m2.get(0).size()){
                            res = op.perform_func_on_matrices(mat1, mat2, ch);    
                        }else{
                            System.out.println("Can't perform this operation on matrices of different dimensions");
                            continue;
                        }
                    }else if(ch==3){
                        if(m1.get(0).size()==m2.size()){
                            res = op.perform_func_on_matrices(mat1, mat2, ch);
                        }else{
                            System.out.println("Can't perform this operation on matrices with inappropriate dimensions(columns of mat1 != rows of mat2)");
                            continue;
                        }
                    }else{
                        if(m2.size()==m2.get(0).size()){
                            if(m1.get(0).size()==m2.size()){
                                res = op.perform_func_on_matrices(mat1, mat2, ch);
                            }else{
                                System.out.println("Can't perform this operation on matrices with inappropriate dimensions(columns of mat1 != rows of mat2)");
                                continue;
                            }
                        }else{
                            System.out.println("Can't perform this operation on matrices with inappropriate dimensions(inverse can only be found on a square matrix)");
                            continue;
                        }
                    }

                    for(ArrayList<Double> each: res){
                        System.out.println(each);
                    }
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==6){
                System.out.println("Which matrices do you want to perform the functions on(example: a b, separated by a space and in the required order)");
                String[] s3 = br.readLine().split(" ");
                String var1 = s3[0], var2 = s3[1];
                Matrix mat1 = input.get(var1), mat2 = input.get(var2);
                System.out.println("Which operation?\n 1) Addition\n 2) Subtraction\n 3) Multiplication\n 4) Division");
                int ch = Integer.parseInt(br.readLine());
                ArrayList<ArrayList<Double>> res = new ArrayList<>();
                ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                ArrayList<ArrayList<Integer>> m2 = mat2.get_matrix();

                if(m1.size()==m2.size() && m1.get(0).size()==m2.get(0).size()){
                    res = op.element_wise_ops(mat1, mat2, ch);
                }else{
                    System.out.println("Can't perform this operation on matrices of different dimensions");
                    continue;
                }

                for(ArrayList<Double> each: res){
                    System.out.println(each);
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==7){
                System.out.println("Which matrix would you like to perform transposition on (enter the variable name)?");
                String var = br.readLine();
                Matrix mat1 = input.get(var);
                ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                ArrayList<ArrayList<Integer>> m1_t = op.get_transpose(m1);

                for(ArrayList<Integer> each: m1_t){
                    System.out.println(each);
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==8){
                System.out.println("Which matrix would you like to perform transposition on (enter the variable name)?");
                String var = br.readLine();
                Matrix mat1 = input.get(var);
                ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                if(m1.size()==m1.get(0).size()){
                    ArrayList<ArrayList<Double>> m1_i = op.find_inverse(m1);
                    for(ArrayList<Double> each: m1_i){
                        System.out.println(each);
                    }
                }else{
                    System.out.println("Can't find inverse of a rectangular matrix(not square)");
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==9){
                System.out.println("Which matrix would you like to perform these operations on (enter the variable name)?");
                String var = br.readLine();
                System.out.println("Which operation would you like to perform?\n 1) Row wise mean\n 2) Column wise mean\n 3) Mean of all elements");
                int ch = Integer.parseInt(br.readLine());
                Matrix mat1 = input.get(var);
                ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                ArrayList<ArrayList<Double>> res = op.find_means(m1, ch);
                for(ArrayList<Double> each: res){
                    System.out.println(each);
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==10){
                System.out.println("Which matrix would you like to find the determinant of (enter the variable name)?");
                String var = br.readLine();
                Matrix mat1 = input.get(var);
                ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                int determinant = op.get_determinant(m1);
                System.out.println("Determinant: " + determinant);
                System.out.println("-------------------------------------------------------");
            }else if(choice==11){
                System.out.println("Do you allow using singleton matrices as a scalar value?(yes/y for yes, no/n for no)");
                String reply = br.readLine();
                if(reply.equals("yes") || reply.equals("y")){
                    System.out.println("Then choose a matrix from a-g");
                    String var1 = br.readLine();
                    System.out.println("Choose a singleton matrix now.");
                    String var2 = br.readLine();
                    Matrix mat1 = input.get(var1), mat2 = input.get(var2);
                    ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                    ArrayList<ArrayList<Integer>> m2 = mat2.get_matrix();
                    int scalar = m2.get(0).get(0);
                    ArrayList<ArrayList<Double>> res = op.element_wise_ops(m1, scalar);
                    System.out.println(var1 + "*" + var2 + " is:");
                    for(ArrayList<Double> each: res){
                        System.out.println(each);
                    }
                }else{
                    System.out.println("Negative response received");
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==12){
                System.out.println("Which matrix do you want to calculate the sum of it's transpose with (enter the variable name)? (It must be a square matrix): ");
                String var = br.readLine();
                Matrix mat1 = input.get(var);
                ArrayList<ArrayList<Integer>> m1 = mat1.get_matrix();
                ArrayList<ArrayList<Integer>> m1_t = op.get_transpose(m1);
                ArrayList<ArrayList<Integer>> res = new ArrayList<>();
                ArrayList<Integer> temp = new ArrayList<>();
                int r = m1.size(), c = m1.get(0).size();
                for(int i = 0; i<r; i++){
                    for(int j = 0; j<c; j++){
                        temp.add(m1.get(i).get(j) + m1_t.get(i).get(j));
                    }
                    res.add(temp);
                }
                for(ArrayList<Integer> each: res){
                    System.out.println(each);
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==13){
                System.out.println("Which square matrix do you want to find the eigenvectors/eigenvalues of?");
                String var = br.readLine();
                Matrix mat1 = input.get(var);
                System.out.println(mat1.get_matrix());
                System.out.println("-------------------------------------------------------");
            }else if(choice==14){
                mat = new Matrix();
                ArrayList<ArrayList<Integer>> a = new ArrayList<>();
                ArrayList<ArrayList<Integer>> c = new ArrayList<>();
                int eqns, vars;
                System.out.println("Enter the equations in a matrix form(Ax = B): ");
                System.out.println("Enter the number of equations: ");
                eqns = Integer.parseInt(br.readLine());
                System.out.println("Enter the number of variables: ");
                vars = Integer.parseInt(br.readLine());
                System.out.println("Enter the matrix A:");
                
                String t = br.readLine();
                int n = t.length();
                if((t.charAt(n-1))==']'){
                    ArrayList<Integer> temp;
                    a = new ArrayList<>();
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
                        for(String c1: s1){
                            temp.add(Integer.parseInt(c1));
                        }
                        a.add(temp);
                    }
                    mat.set_matrix(a);
                }

                mat = new Matrix();
                System.out.println("Enter the constants in a matrix(example: [constant1;constant2;constant3]): ");
                t = br.readLine();
                n = t.length();
                if((t.charAt(n-1))==']'){
                    ArrayList<Integer> temp;
                    a = new ArrayList<>();
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
                        for(String c1: s1){
                            temp.add(Integer.parseInt(c1));
                        }
                        c.add(temp);
                    }
                    mat.set_matrix(c);
                    mat = new Matrix();
                }
                ArrayList<ArrayList<Double>> res = op.solve_eqn(a, c);
                System.out.println("The variables are: ");
                for(ArrayList<Double> each: res){
                    System.out.println(each);
                }
                System.out.println("-------------------------------------------------------");
            }else if(choice==15){
                System.out.println("All square matrices:");
                for(Matrix each: matrices){
                    if(each.is_square()){
                        each.print_matrix();
                    }
                }
                System.out.println("-------------------------------------------------------");
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

class Matrix extends Matrices{
    private ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> types = new ArrayList<>();

    public boolean is_square(){
        return this.matrix.size()==this.matrix.get(0).size();
    }

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

    public void print_matrix(){
        for(ArrayList<Integer> each: this.matrix){
            System.out.println(each);
        }
    }
}

class Operations{
    private Matrix mat = new Matrix();

    public ArrayList<ArrayList<Double>> solve_eqn(ArrayList<ArrayList<Integer>> m, ArrayList<ArrayList<Integer>> c){
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        ArrayList<ArrayList<Double>> m_i = find_inverse(m);
        ArrayList<Double> temp = new ArrayList<>();
        for(int i = 0; i<m_i.size(); i++){
            for(int j = 0; j<c.get(0).size(); j++){
                for(int k = 0; k<c.size(); j++){
                    temp.add((double)(m_i.get(i).get(k) * c.get(k).get(j)));
                }
            }
            res.add(temp);
        }
        return res;
    }

    public ArrayList<ArrayList<Double>> element_wise_ops(ArrayList<ArrayList<Integer>> m, Integer scalar){
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        int r = m.size();
        int c = m.get(0).size();
        for(int i = 0; i<r; i++){
            ArrayList<Double> temp = new ArrayList<>();
            for(int j = 0; j<c; j++){
                int z = m.get(i).get(j);
                temp.add((double)z*scalar);
            }
            res.add(temp);
        }
        return res;
    }

    public ArrayList<ArrayList<Double>> find_means(ArrayList<ArrayList<Integer>> m1, int choice){
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        int r = m1.size();
        int c = m1.get(0).size();
        ArrayList<Double> temp = new ArrayList<>();
        if(choice==1){
            for(int i = 0; i<r; i++){
                temp = new ArrayList<>();
                int x = 0;
                for(int j = 0; j<c; j++){
                    x+=m1.get(i).get(j);
                }
                temp.add((double)x/c);
                res.add(temp);
            }
        }else if(choice==2){
            for(int i = 0; i<c; i++){
                temp = new ArrayList<>();
                int x = 0;
                for(int j = 0; j<r; j++){
                    x+=m1.get(j).get(i);
                }
                temp.add((double)x/c);
            }
            res.add(temp);
        }else{
            int x = 0;
            for(int i = 0; i<r; i++){
                for(int j = 0; j<c; j++){
                    x+=m1.get(i).get(j);
                }
            }
            temp.add((double)x/c);
            res.add(temp);
        }
        return res;
    }

    public ArrayList<ArrayList<Double>> element_wise_ops(Matrix mat1, Matrix mat2, int choice){
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        ArrayList<Double> temp = new ArrayList<>();
        ArrayList<ArrayList<Integer>> matr1 = mat1.get_matrix();
        ArrayList<ArrayList<Integer>> matr2 = mat2.get_matrix();
        for(int i = 0; i<matr1.size(); i++){
            temp = new ArrayList<>();
            for(int j = 0; j<matr1.get(0).size(); j++){
                if(choice==1){
                    temp.add((double)matr1.get(i).get(j)+matr2.get(i).get(j));
                }else if(choice==2){
                    temp.add((double)matr1.get(i).get(j)-matr2.get(i).get(j));
                }else if(choice==3){
                    temp.add((double)matr1.get(i).get(j)*matr2.get(i).get(j));
                }else{
                    temp.add((double)matr1.get(i).get(j)/matr2.get(i).get(j));
                }
            }
            res.add(temp);
        }
        return res;
    }

    public ArrayList<ArrayList<Double>> perform_func_on_matrices(Matrix mat1, Matrix mat2, int choice){
        ArrayList<ArrayList<Integer>> matr1 = mat1.get_matrix();
        ArrayList<ArrayList<Integer>> matr2 = mat2.get_matrix();
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        ArrayList<Double> temp = new ArrayList<>();
        if(choice!=4 && choice!=3){
            for(int i = 0; i<matr1.size(); i++){
                temp = new ArrayList<>();
                for(int j = 0; j<matr1.get(0).size(); j++){
                    if(choice==1){
                        temp.add((double)matr1.get(i).get(j) + matr2.get(i).get(j));
                    }else if(choice==2){
                        temp.add((double)matr1.get(i).get(j) - matr2.get(i).get(j));
                    }
                }
            }
        }else if(choice==3){
            res = multiply_matrices(matr1, matr2);
        }else{
            ArrayList<ArrayList<Double>> inverse = find_inverse(matr2);
            for(int i = 0; i<matr1.size(); i++){
                temp = new ArrayList<>();
                for(int j = 0; j<matr1.get(0).size(); j++){
                    temp.add((double)matr1.get(i).get(j)*inverse.get(i).get(j));
                }
                res.add(temp);
            }
        }
        return res;
    }

    public Double helper(int a, int b, int c, int d){
        return (double)a*b - c*d;
    }

    public ArrayList<ArrayList<Double>> multiply_matrices(ArrayList<ArrayList<Integer>> m1, ArrayList<ArrayList<Integer>> m2){
        ArrayList<ArrayList<Double>> res = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> temp = new ArrayList<>();
        for(int i = 0; i<m1.size(); i++){
            for(int j = 0; j<m2.get(0).size(); j++){
                for(int k = 0; k<m2.size(); j++){
                    temp.add((double)(m1.get(i).get(k) * m2.get(k).get(j)));
                }
            }
            res.add(temp);
        }
        return res;
    }

    public ArrayList<ArrayList<Double>> find_inverse(ArrayList<ArrayList<Integer>> matr){
        ArrayList<ArrayList<Double>> res = new ArrayList<>();
        int det = get_determinant(matr);
        int r = matr.size();
        int c = matr.get(0).size();
        if(r==1 && c==1){
            ArrayList<Double> temp = new ArrayList<>();
            temp.add((double)matr.get(0).get(0));
            res.add(temp);
        }else{
            if(r==2){
                ArrayList<Double> temp = new ArrayList<>();
                temp.add((double)(matr.get(1).get(1)/det));
                temp.add((double)(matr.get(0).get(0)/det));
                res.add(temp);
                temp = new ArrayList<>();
                temp.add((double)(-1*matr.get(0).get(1)/det));
                temp.add((double)(-1*matr.get(1).get(0)/det));
                res.add(temp);
            }else{

                for(int i = 0; i<r; i++){
                    ArrayList<Double> temp = new ArrayList<>();
                    for(int j = 0; j<c; j++){
                        int sign = -1;
                        if((i+j)%2 == 1){
                            sign = -1;
                        }else{
                            sign = 1;
                        }
                        ArrayList<ArrayList<Integer>> newmat = new ArrayList<>();
                        ArrayList<Integer> newtemp = new ArrayList<>();
                        for(int v = 0; v<r; v++){
                            newtemp = new ArrayList<>();
                            for(int w = 0; w<c; w++){
                                if(v!=i || w!=j){
                                    newtemp.add(matr.get(v).get(w));
                                }
                            }
                            newmat.add(newtemp);
                        }
                        temp.add((double)get_determinant(newmat)*sign/det);
                    }
                    res.add(temp);
                }
            }
        }
        return res;
    }

    public ArrayList<ArrayList<Integer>> get_transpose(ArrayList<ArrayList<Integer>> tr){
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();
        int r = tr.size();
        int c = tr.get(0).size();
        for(int i = 0; i<c; i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0; j<r; j++){
                temp.add(tr.get(j).get(i));
            }
            transpose.add(temp);
        }
        return transpose;
    }

    public Integer get_determinant(ArrayList<ArrayList<Integer>> matrix){
        int det = 0;
        if(matrix.size()==1){
            det = matrix.get(0).get(0);
        }else if(matrix.size()==2){
            det = (matrix.get(0).get(0))*(matrix.get(1).get(1)) - (matrix.get(1).get(0))*(matrix.get(0).get(1));
        }else{
            int x = (matrix.get(0).get(0))*((matrix.get(1).get(1))*(matrix.get(2).get(2)) - (matrix.get(2).get(1))*(matrix.get(1).get(2)));
            int y = (matrix.get(0).get(1))*((matrix.get(1).get(0))*(matrix.get(2).get(2)) - (matrix.get(2).get(0))*(matrix.get(1).get(2)));
            int z = (matrix.get(0).get(2))*((matrix.get(1).get(0))*(matrix.get(2).get(1)) - (matrix.get(2).get(0))*(matrix.get(1).get(1)));
            det = x-y+z;
        }
        return det;
    }

    public ArrayList<ArrayList<Integer>> get_a_matrix(Integer type){
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        if(type==1){
            int[][] m = {{1, 2, 3}, {4, 5, 6}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==2){
            int[][] m = {{1, 2}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==3){
            int[][] m = {{1}, {2}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==4){
            int[][] m = {{1, 2}, {3, 4}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==5){
            int[][] m = {{1, 2}, {2, 4}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==6){
            int[][] m = {{5, 2}, {-2, 6}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==7){
            int[][] m = {{1, 2}, {0, 4}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==8){
            int[][] m = {{1, 0}, {3, 4}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==9){
            int[][] m = {{1, 2}, {1, 2}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==10){
            int[][] m = {{1, 0}, {0, 4}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==11){
            int[][] m = {{3, 0}, {0, 3}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==12){
            int[][] m = {{1, 0}, {0, 1}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==13){
            int[][] m = {{3}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==14){
            int[][] m = {{1, 1}, {1, 1}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }else if(type==15){
            int[][] m = {{0, 0}, {0, 0}};
            for(int i = 0; i<m.length; i++){
                temp = new ArrayList<>();
                for(int j = 0; j<m[0].length; j++){
                    temp.add(m[i][j]);
                }
                matrix.add(temp);
            }
        }
        return matrix;
    }

    public void set_types(Matrix matr){
        mat = matr;
        ArrayList<ArrayList<Integer>> matrix = mat.get_matrix();
        int r = matrix.size();
        int c = matrix.get(0).size();
        
        if(r==c){
            int determinant = get_determinant(matrix);
            ArrayList<ArrayList<Integer>> transpose = get_transpose(matrix);
            if(r==1 && c==1){
                mat.set_types(13);
            }else{
                mat.set_types(4);
                if(r==2 && c==2){
                    if(matrix.get(1).get(0)==0 && matrix.get(0).get(0)!=0 && matrix.get(1).get(1)!=0 && matrix.get(0).get(1)!=0){
                        mat.set_types(7);
                    }else if(matrix.get(1).get(0)!=0 && matrix.get(0).get(0)!=0 && matrix.get(1).get(1)!=0 && matrix.get(0).get(1)==0){
                        mat.set_types(8);
                    }
                }else{
                    boolean flag2 = true;
                    for(int i = 0; i<r; i++){
                        for(int j = 0; j<c; j++){
                            if((i>j && matrix.get(i).get(j)==0) || (i<=j && matrix.get(i).get(j)!=0)){
                                flag2 = true;
                            }else{
                                flag2 = false;
                                break;
                            }
                        }
                    }
                    if(flag2){
                        mat.set_types(7);
                    }else{
                        flag2 = true;
                        for(int i = 0; i<r; i++){
                            for(int j = 0; j<c; j++){
                                if((i<j && matrix.get(i).get(j)==0) || (i>=j && matrix.get(i).get(j)!=0)){
                                    flag2 = true;
                                }else{
                                    flag2 = false;
                                    break;
                                }
                            }
                        }
                        if(flag2){
                            mat.set_types(8);
                        }
                    }
                }
                boolean flag = true;
                for(int i = 0; i<r; i++){
                    for(int j = 0; j<c; j++){
                        if((matrix.get(i).get(j))!=transpose.get(i).get(j)){
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
                            if((matrix.get(i).get(j))!=((transpose.get(i).get(j))*-1)){
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(flag){
                        mat.set_types(6);
                    }
                }
                if(determinant==0){
                    mat.set_types(9);
                }
                flag = true;
                for(int i = 0; i<r; i++){
                    for(int j = 0; j<c; j++){
                        if((i==j && matrix.get(i).get(j)>0) || (i!=j && matrix.get(i).get(j)==0)){
                            flag = true;
                        }else{
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag){
                    mat.set_types(10);
                    if(matrix.get(0).get(0)==matrix.get(1).get(1)){
                        if(r==3 && c==3){
                            if(matrix.get(1).get(1)==matrix.get(2).get(2)){
                                if(matrix.get(0).get(0)==1){
                                    mat.set_types(12);
                                }else{
                                    mat.set_types(11);
                                }
                            }
                        }else{
                            if(matrix.get(0).get(0)==1){
                                mat.set_types(12);
                            }else{
                                mat.set_types(11);
                            }
                        }
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
        boolean flag1 = true;
        for(int i = 0; i<r; i++){
            for(int j = 0; j<c; j++){
                if(matrix.get(i).get(j)==1){
                    flag1 = true;
                }else{
                    flag1 = false;
                    break;
                }
            }
        }
        if(flag1){
            mat.set_types(14);
        }else{
            flag1 = true;
            for(int i = 0; i<r; i++){
                for(int j = 0; j<c; j++){
                    if(matrix.get(i).get(j)==0){
                        flag1 = true;
                    }else{
                        flag1 = false;
                        break;
                    }
                }
            }
            if(flag1){
                mat.set_types(15);
            }
        }
    }
}