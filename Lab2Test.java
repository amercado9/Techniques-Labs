import Lab2.Lab2;

public class Lab2Test {
    public static void main(String[] args) {
        Lab2 lab = new Lab2();
        String binary = lab.binaryToArm("11100000000000010000000000000010");
        System.out.println(binary); // Expected: "0100 0000 0001 0010"
    }
}