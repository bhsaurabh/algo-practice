public class SortTest {
    public static void main(String[] args) {
        Integer[] arr = {0, 5, 1, 2, 4, 3, 9, 7, 6, 8};
        Shell.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}