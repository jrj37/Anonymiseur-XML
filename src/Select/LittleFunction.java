package Select;
public class LittleFunction {
    public String getLastFolder(String filePath) {
        String[] parties = filePath.split("\\\\|/");
        return parties[parties.length - 1];
    }

    public String extract(String filename) {
        String[] mots = filename.split("-");
        return mots[0];
    }
}

