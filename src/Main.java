public class Main {
    public static void main(String[] args) {
        Config config = null;
        if (args.length == 0) {
            config = new Config("config.txt");
        } else if (args[0].trim().toLowerCase().equals("staging")) {
            config = new Config("config-staging.txt");
        } else if (args[0].trim().toLowerCase().equals("development")) {
            config = new Config("config-dev.txt");
        }

        System.out.println(config.get("application.name"));
    }
}
