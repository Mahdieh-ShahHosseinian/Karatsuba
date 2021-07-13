import java.util.ArrayList;
import java.util.Collections;

/**
 * finally :) on my BD 15 Sha'ban 2PM
 */

public class Multiplication {

    Multiplication(ArrayList<Integer> x, ArrayList<Integer> y) {
        print(divideAndConquerMultiplication(x, y));
        System.out.println();
        print(karatsubaMultiplication(x, y));
    }

    private void print(ArrayList<Integer> result) {

        for (Integer integer : result) {
            System.out.print(integer);
        }
        System.out.println();
    }

    private ArrayList<Integer> karatsubaMultiplication(ArrayList<Integer> x, ArrayList<Integer> y) {

        ArrayList<Integer> mulRes = new ArrayList<>();

        if (x.size() == 0 || y.size() == 0) {
            return mulRes;
        } else if ((x.size() == 1 && x.get(0) == 0) || (y.size() == 1 && y.get(0) == 0)) {
            mulRes.add(0);
            return mulRes;
        } else if ((x.size() == 1 && x.get(0) == 1) || (y.size() == 1 && y.get(0) == 1)) {
            return (x.size() == 1 && x.get(0) == 1) ? y : x;
        } else if (x.size() == 1 && y.size() == 1) {
            String s = (x.get(0) * y.get(0)) + "";
            for (int i = 0; i < s.length(); i++) mulRes.add(i, s.charAt(i) - '0');
            return mulRes;
        } else if (x.size() != y.size()) {

            int diff = Math.abs(x.size() - y.size());
            if (x.size() > y.size()) {
                int l = y.size();
                for (int i = 0; i < diff; i++) y.add(i, 0);
            } else {
                int l = x.size();
                for (int i = 0; i < diff; i++) x.add(i, 0);
            }
        }

        int len = x.size();
        ArrayList<Integer> x0 = new ArrayList<>();
        ArrayList<Integer> y0 = new ArrayList<>();
        for (int i = 0; i < (len + 1) / 2; i++) {
            x0.add(x.get(i));
            y0.add(y.get(i));
        }
        ArrayList<Integer> x1 = new ArrayList<>();
        ArrayList<Integer> y1 = new ArrayList<>();
        for (int i = (len + 1) / 2; i < len; i++) {
            x1.add(x.get(i));
            y1.add(y.get(i));
        }

        ArrayList<Integer> k0 = karatsubaMultiplication(x0, y0);
        ArrayList<Integer> k2 = karatsubaMultiplication(x1, y1);
        ArrayList<Integer> k1 = karatsubaMultiplication(add(x0, x1), add(y0, y1));
        k1 = sub(k1, add(k0, k2));

        for (int i = 0; i < len / 2 + len / 2; i++) k0.add(0);
        for (int i = 0; i < len / 2; i++) k1.add(0);

        mulRes = add(add(k0, k1), k2);

        if (mulRes.size() > 1 && mulRes.get(0) == 0) {

            int indexes = 0;
            for (Integer i : mulRes) {
                if (i != 0) break;
                indexes++;
            }
            if (indexes > 0) mulRes.subList(0, indexes).clear();
        }

        return mulRes;
    }

    private ArrayList<Integer> divideAndConquerMultiplication(ArrayList<Integer> x, ArrayList<Integer> y) {

        ArrayList<Integer> mulRes = new ArrayList<>();

        if (x.size() == 0 || y.size() == 0) {
            return mulRes;
        } else if ((x.size() == 1 && x.get(0) == 0) || (y.size() == 1 && y.get(0) == 0)) {
            mulRes.add(0);
            return mulRes;
        } else if (x.size() == 1 && y.size() == 1) {
            String s = (x.get(0) * y.get(0)) + "";
            for (int i = 0; i < s.length(); i++) mulRes.add(i, s.charAt(i) - '0');
            return mulRes;
        }

        ArrayList<Integer> x0 = new ArrayList<>();
        for (int i = 0; i < (x.size() + 1) / 2; i++) x0.add(x.get(i));
        ArrayList<Integer> x1 = new ArrayList<>();
        for (int i = (x.size() + 1) / 2; i < x.size(); i++) x1.add(x.get(i));
        ArrayList<Integer> y0 = new ArrayList<>();
        for (int i = 0; i < (y.size() + 1) / 2; i++) y0.add(y.get(i));
        ArrayList<Integer> y1 = new ArrayList<>();
        for (int i = (y.size() + 1) / 2; i < y.size(); i++) y1.add(y.get(i));

        ArrayList<Integer> s0 = divideAndConquerMultiplication(x0, y0);
        ArrayList<Integer> s1 = divideAndConquerMultiplication(x0, y1);
        ArrayList<Integer> s2 = divideAndConquerMultiplication(x1, y0);
        ArrayList<Integer> s3 = divideAndConquerMultiplication(x1, y1);

        for (int i = 0; i < x.size() / 2 + y.size() / 2; i++) s0.add(0);
        for (int i = 0; i < x.size() / 2; i++) s1.add(0);
        for (int i = 0; i < y.size() / 2; i++) s2.add(0);

        mulRes = add(add(add(s0, s1), s2), s3);

        return mulRes;
    }

    private ArrayList<Integer> add(ArrayList<Integer> x, ArrayList<Integer> y) {

        if (x.size() == 0 || y.size() == 0) return y.size() == 0 ? x : y;

        ArrayList<Integer> addRes = new ArrayList<>();

        Collections.reverse(x);
        Collections.reverse(y);

        int sum, carry = 0;
        for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
            sum = x.get(i) + y.get(i) + carry;
            if (sum < 10) {
                addRes.add(sum);
                carry = 0;
            } else {
                addRes.add(sum % 10);
                carry = sum / 10;
            }
        }
        if (x.size() > y.size()) {
            for (int i = y.size(); i < x.size(); i++) {
                sum = x.get(i) + carry;
                if (sum < 10) {
                    addRes.add(sum);
                    carry = 0;
                } else {
                    addRes.add(sum % 10);
                    carry = sum / 10;
                }
            }
        } else {
            for (int i = x.size(); i < y.size(); i++) {
                sum = y.get(i) + carry;
                if (sum < 10) {
                    addRes.add(sum);
                    carry = 0;
                } else {
                    addRes.add(sum % 10);
                    carry = sum / 10;
                }
            }
        }
        if (carry != 0) {
            addRes.add(carry);
        }

        Collections.reverse(addRes);
        Collections.reverse(x);
        Collections.reverse(y);

        return addRes;
    }

    // in this case x > y
    private ArrayList<Integer> sub(ArrayList<Integer> x, ArrayList<Integer> y) {

        if (x.size() == 0 || y.size() == 0) return y.size() == 0 ? x : y;

        ArrayList<Integer> subRes = new ArrayList<>();

        boolean minus1 = false;
        int result;

        Collections.reverse(x);
        Collections.reverse(y);

        for (int i = 0; i < y.size(); i++) {

            result = x.get(i) - y.get(i);
            if (minus1) {
                result -= 1;
                minus1 = false;
            }
            if (result < 0) {
                result += 10;
                minus1 = true;
            }
            subRes.add(result);
        }
        for (int i = y.size(); i < x.size(); i++) {

            result = x.get(i);
            if (minus1) {
                result -= 1;
                minus1 = false;
            }
            if (result < 0) {
                result += 10;
                minus1 = true;
            }
            subRes.add(result);
        }

        Collections.reverse(subRes);
        Collections.reverse(x);
        Collections.reverse(y);

        return subRes;
    }
}
