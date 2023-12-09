import java.io.*;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class app {
    public static void main(String[] args) throws IOException {
        //String Str = "";
        //Str.split(" ");
        try {
            getData();
            System.out.println("success");
        } catch (FileSystemException e){
            System.out.println("There was an error: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("There was an error: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public static void getData() throws Exception {
        System.out.println("Input first name, last name, patronymic, birth date (in format of DD.MM.YYYY), phone number (number without delimeters) and gender (latin symbol m/f), divided by space symbol");
        String Str;
        try(BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {Str = bf.readLine();}
        catch (IOException e){
            throw new Exception("There was an error with Console. Try other data");
        }
        String[] data = Str.split(" ");
        if (data.length != 6){
            throw new Exception("Wrong parameter number");
        }
        String first_name = data[0];
        String last_name = data[1];
        String patronymic = data[2];
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date birthdate;
        String fileName;
        int phone;
        String gender;
        try { birthdate = format.parse(data[3]);}
        catch (ParseException e){
            throw new ParseException("Wrong birthdate format", e.getErrorOffset());
        }
        try { phone = Integer.parseInt(data[4]);}
        catch (NumberFormatException e){
            throw new NumberFormatException("Wrong phone number format");
        }
        gender = data[5];
        if (!gender.toLowerCase().equals("m") && !gender.toLowerCase().equals("f")){
            throw new RuntimeException("Wrong gender format");
        }
        fileName = first_name.toLowerCase() + ".txt";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, true)){
            if (file.length() > 0){
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s %s %s", first_name, last_name, patronymic, format.format(birthdate), phone, gender));
            fileWriter.close();
        }
        catch (IOException e){
            throw new FileSystemException("There is an error working with file");
        }

    }
}

 /*
     * Напишите приложение, которое будет запрашивать у пользователя следующие данные в произвольном порядке, разделенные пробелом:
Фамилия Имя Отчество дата рождения номер телефона пол

Форматы данных:
фамилия, имя, отчество - строки
датарождения - строка формата dd.mm.yyyy
номертелефона - целое беззнаковое число без форматирования
пол - символ латиницей f или m.

Приложение должно проверить введенные данные по количеству. Если количество не совпадает с требуемым, вернуть код ошибки, обработать его и показать пользователю сообщение, что он ввел меньше и больше данных, чем требуется.

Приложение должно попытаться распарсить полученные значения и выделить из них требуемые параметры. Если форматы данных не совпадают, нужно бросить исключение, соответствующее типу проблемы. Можно использовать встроенные типы java и создать свои. Исключение должно быть корректно обработано, пользователю выведено сообщение с информацией, что именно неверно.

Если всё введено и обработано верно, должен создаться файл с названием, равным фамилии, в него в одну строку должны записаться полученные данные, вида

<Фамилия><Имя><Отчество><датарождения> <номертелефона><пол>

Однофамильцы должны записаться в один и тот же файл, в отдельные строки.

Не забудьте закрыть соединение с файлом.

При возникновении проблемы с чтением-записью в файл, исключение должно быть корректно обработано, пользователь должен увидеть стектрейс ошибки.
*/