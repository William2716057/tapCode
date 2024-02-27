import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TapEncode {
    public static void main(String[] args) {
        // Take input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter message: ");
        String message = scanner.nextLine().toUpperCase();
        scanner.close();

        String tapCode = encodeTapCode(message);
        System.out.println("Tap code for '" + message + "': " + tapCode);
    }
	//function to encode message into tap code
    public static String encodeTapCode(String message) {
	//create tap code grid
        char[][] tapCodeGrid = {
                {'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'J'},
                {'L', 'M', 'N', 'O', 'P'},
                {'Q', 'R', 'S', 'T', 'U'},
                {'V', 'W', 'X', 'Y', 'Z'}
        };
	//map to store tap codes for each letter
        Map<Character, String> tapCodeMap = new HashMap<>();
        for (int i = 0; i < tapCodeGrid.length; i++) {
            for (int j = 0; j < tapCodeGrid[i].length; j++) {
                char letter = tapCodeGrid[i][j];
                tapCodeMap.put(letter, encodePair(i, j));
            }
        }
	//encode message for each letter
        StringBuilder encodedMessage = new StringBuilder();
        for (char letter : message.toCharArray()) {
            letter = Character.toUpperCase(letter);
            if (letter == 'K') {
                continue;
            }
            if (!tapCodeMap.containsKey(letter)) {
                continue;
            }
            encodedMessage.append(tapCodeMap.get(letter)).append(" ");
        }

        return encodedMessage.toString();
    }
	//encode row and col into Tap code
    public static String encodePair(int row, int col) {
	//construct the tap code
        StringBuilder tapCode = new StringBuilder();
        for (int i = 0; i < row + 1; i++) {
            tapCode.append(".");
        }
	//append dots for the row
        tapCode.append(" ");//add space
        for (int i = 0; i < col + 1; i++) {
            tapCode.append("."); //append dots for the column
        }

        return tapCode.toString();
    }
}

