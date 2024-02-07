import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class UserInputApp {
    public static void main(String[] args) {
        try {
            String userInput = "Иванов Иван Иванович 01.01.1990 1234567890 m";
            processUserData(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processUserData(String userInput) throws Exception {
        String[] data = userInput.split("\\s+");

        if (data.length != 6) {
            System.out.println("Ошибка: Неверное количество данных.");
            return;
        }

        try {
            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            UserData userData = new UserData(lastName, firstName, middleName, birthDate, phoneNumber, gender);
            writeToUserFile(userData);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Неверный формат данных для номера телефона.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void writeToUserFile(UserData userData) throws IOException {
        String fileName = userData.lastName + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(userData.lastName + " " + userData.firstName + " " + userData.middleName + " "
                    + userData.birthDate + " " + userData.phoneNumber + " " + userData.gender);
            writer.newLine();
        } catch (IOException e) {
            throw new IOException("Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
