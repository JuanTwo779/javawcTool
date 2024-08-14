import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        while(true){
            //1. Get user input
            Scanner scan = new Scanner(System.in);
            String command = scan.nextLine();
            String[] arr = command.split(" ");
            if (command.equals("exit")) break;

            //2. Name + action + file
            if (arr[0].equals("ccwc") && arr.length == 3){
                String action =  arr[1];
                if (action.equals("-c")){ // read bites
                    System.out.println(fileBytes(arr[2]));
                } else if (action.equals("-l")) {
                    System.out.println(fileLines(arr[2]));
                } else if (action.equals("-w")) {
                    System.out.println(fileWords(arr[2]));
                } else if (action.equals(("-m"))){
                    System.out.println(fileChars(arr[2]));
                }
            } else if (arr[0].equals("ccwc") && arr.length == 2) {
                System.out.println(
                        fileBytes(arr[1])+"\t"+
                        fileLines(arr[1])+"\t"+
                        fileWords(arr[1])+" "+
                        arr[1]);
            } else {
                System.err.println("Invalid");
            }
        }
    }

    private static int fileChars(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        String string = Files.readString(path);
        return string.length();
    }

    private static int fileWords(String fileName) {
        try {
            int res = 0;
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                res++;
                scanner.next();
            }
            return res;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int fileLines(String fileName) {
        try{
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);
            return lines.size();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long fileBytes(String fileName) {
        try {
            Path path = Paths.get(fileName);
            return Files.size(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}