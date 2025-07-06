import java.io.File;
import java.io.IOException;

public class installADB {
    private static final String ADB_PATH = "C:\\\\Android\\\\Sdk\\\\platform-tools\\\\adb.exe";
    private static final String APK_PATH = "K:\\\\Android-Mod-Menu\\\\app\\\\build\\\\outputs\\\\apk\\\\debug\\\\app-debug.apk";

    public static void main(String[] args) {
        installAPK(APK_PATH);
    }

    public static void installAPK(String apkPath) {
        File apkFile = new File(apkPath);

        if (!apkFile.exists()) {
            System.err.println("❌ APK not found at: " + apkFile.getAbsolutePath());
            return;
        }
        String command = "\"" + ADB_PATH + "\" install -r \"" + apkFile.getAbsolutePath() + "\"";

        try {
            Process process = Runtime.getRuntime().exec(command);
            int result = process.waitFor();

            if (result == 0) {
                System.out.println("APK installed successfully!");
            } else {
                System.err.println("APK installation failed with code: " + result);
                printProcessOutput(process);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printProcessOutput(Process process) throws IOException {
        try (var reader = new java.io.BufferedReader(new java.io.InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.err.println(line);
            }
        }
    }
}
