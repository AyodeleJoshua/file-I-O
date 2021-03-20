import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Config {
    String name;
    HashMap<String,String> configStore = new HashMap<String, String>();

    public Config(String name) {
        this.name = name;
        Extract();
    }

    private void Extract() {
        FileReader file = null;
        boolean containsApplication = false;
        try {
            file = new FileReader(new File(name));
            BufferedReader reader = new BufferedReader(file);

            String line = null;

            while ((line = reader.readLine()) != null) {
                if (line.contains("application")) containsApplication = true;
                if (containsApplication) {
                    setKeyAndValue("application", line);
                } else {
                    setKeyandValue(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                file.close();
            } catch (IOException ex) {
                ex.getMessage();
            }
        }

    }

    private void setKeyAndValue(String prefix, String keyAndValue) {
        String[] arr = keyAndValue.split("=");
        if (!configStore.containsKey("application." + arr[0]) && arr.length == 2) {
            configStore.put(("application." + arr[0]), arr[1]);
        }
    }

    private void setKeyandValue(String keyAndValue) {
        String[] arr = keyAndValue.split("=");
        if (!configStore.containsKey(arr[0]) && arr.length == 2) {
            configStore.put(arr[0], arr[1]);
        }
    }

    public String get(String key) {
        if (configStore.containsKey(key)) {
            return configStore.get(key);
        } else {
            return "Invalid Key";
        }
    }
}
