import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {

        GameProgress gameProgress1 = new GameProgress(100, 5, 35, 254.32);
        GameProgress gameProgress2 = new GameProgress(75, 2, 50, 760.21);
        GameProgress gameProgress3 = new GameProgress(195, 7, 80, 1000.45);

        saveGame("/Users/davidgvaliya/Game/savegames/game1.dat", gameProgress1);
        saveGame("/Users/davidgvaliya/Game/savegames/game2.dat", gameProgress2);
        saveGame("/Users/davidgvaliya/Game/savegames/game3.dat", gameProgress3);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("/Users/davidgvaliya/Game/savegames/game1.dat");
        arrayList.add("/Users/davidgvaliya/Game/savegames/game2.dat");
        arrayList.add("/Users/davidgvaliya/Game/savegames/game3.dat");

        zipFiles("/Users/davidgvaliya/Game/savegames/zip.zip", arrayList);

        File game1Dat = new File("/Users/davidgvaliya/Game/savegames/game1.dat");
        File game2Dat = new File("/Users/davidgvaliya/Game/savegames/game2.dat");
        File game3Dat = new File("/Users/davidgvaliya/Game/savegames/game3.dat");
        if (game1Dat.delete()) System.out.println("Файл \"game1.dat\" удален");
        if (game2Dat.delete()) System.out.println("Файл \"game2.dat\" удален");
        if (game3Dat.delete()) System.out.println("Файл \"game3.dat\" удален");

    }

    public static void saveGame(String way, GameProgress gameProgress){
        try (FileOutputStream fos = new FileOutputStream(way);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            //Запишем экземпляр файла в класс
            oos.writeObject(gameProgress);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void  zipFiles(String way, List<String> arrayList){
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(way))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
