import de.ralleytn.simple.json.JSONObject;
import de.ralleytn.simple.json.JSONParseException;

import java.io.*;
import java.nio.file.Paths;

public class DB {

    private final File dbFile;
    public JSONObject jsonObject;

    public DB() {
        this.dbFile = Paths.get("DB/src/db.json").toFile();
        try {
            if (this.dbFile.createNewFile()) {
                System.out.println("DB File created: " + this.dbFile.getName());
            }
            loadDB();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void loadDB() {
        try {
            FileReader reader = new FileReader(this.dbFile);
            this.jsonObject = new JSONObject(reader);
        } catch (IOException | JSONParseException e) {
            System.out.println(e);
        }
    }

    public void saveDB() {
        try {
            PrintWriter prw = new PrintWriter(this.dbFile);
            this.jsonObject.write(prw);
            prw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
