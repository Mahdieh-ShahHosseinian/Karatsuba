import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            new Multiplication(toArrayList(sc.next()), toArrayList(sc.next()));
        }
    }

    private static ArrayList<Integer> toArrayList(String str) {

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < str.length(); i++) {
            arrayList.add(str.charAt(i) - '0');
        }
        return arrayList;
    }
}
