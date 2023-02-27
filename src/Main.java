import entities.Commands.CommandsSingleton;

public class Main {
    public static void main(String[] args) {
        System.out.println(CommandsSingleton.valueOf("back"));
    }

}