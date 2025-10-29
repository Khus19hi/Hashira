import java.math.BigInteger;
import java.util.*;

public class Khushi {

    public static BigInteger convertToDecimal(String value, int base) {
        return new BigInteger(value, base);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of roots (n): ");
        int n = sc.nextInt();
        System.out.print("Enter k minimum roots required: ");
        int k = sc.nextInt();
        sc.nextLine();

        int m = k - 1;

        List<BigInteger> decodedRoots = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nEnter details for root " + i + ":");
            System.out.print("Base: ");
            int base = sc.nextInt();
            sc.nextLine();
            System.out.print("Value: ");
            String value = sc.nextLine();

            BigInteger decimalValue = convertToDecimal(value, base);
            System.out.println("Decoded Root " + i + " = " + decimalValue);

            if (decodedRoots.size() < k) {
                decodedRoots.add(decimalValue);
            }
        }

        BigInteger product = BigInteger.ONE;
        for (BigInteger r : decodedRoots) {
            product = product.multiply(r);
        }

        if (m % 2 != 0) {
            product = product.negate();
        }

        BigInteger constantTerm = product;

        System.out.println("Degree of polynomial (m): " + m);
        System.out.println("Constant term (c): " + constantTerm);

    }
}