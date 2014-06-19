public class CollectionTest {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<Character>();
        char[] arr = {'u', 'a', '-', 'i', 'e', '-', '-', 'o', '-', '-'};
        for (char c : arr) {
            if (c == '-') {
                System.out.print(stack.pop() + " ");
            } else {
                stack.push(c);
            }
        }
        System.out.println();
    }
}