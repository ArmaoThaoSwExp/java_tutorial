import java.io.*;
import java.util.List;
import java.nio.file.*;
import java.util.*;



public class FileTest {
    final public int FILE_READ_CHUNK_SIZE = 10;

    /**
     * Create a file and write the data in data into the file.
     * @param filename
     * @param data
     */
    public static void createFile(String filename, List<String> data) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename),
                "utf-8"))) {
            for (String item: data) {
                writer.write(item);
            }
        }
        catch (IOException ex) {
            System.out.print(String.format("An error occurred while trying to open/write to file {}", filename));
            ex.printStackTrace();
        }
    }

    /**
     * Read file specified. Even though chunking is specified, this method will still
     * return a string representation of the entire file in raw data.
     * This is just to show file reading concepts.
     * @param filename
     * @param chunkSize
     * @return
     */
    public static String readFile(String filename, int chunkSize) {
        StringBuilder result = new StringBuilder();
        char[] buffer = new char[chunkSize];
        try (Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename),
                "utf-8"))) {
            int i = 0;
            while (true) {
                int bytesRead = reader.read(buffer, 0, chunkSize);
                if ((bytesRead == -1) || (bytesRead == 0)) {
                    break; // No more data to read
                }

                result.append(buffer);
                if (bytesRead < chunkSize) {
                    break;  // Last chunk
                }
            }
        }
        catch (IOException ex) {
            System.out.print(String.format("An error occurred while trying to open/write to file {}", filename));
            ex.printStackTrace();
        }
        return result.toString();
    }

    public static void main(String args[]) {
        // Create file name
        Path filepath = Paths.get(System.getProperty("user.dir"));
        String filename = Paths.get(filepath.toString(), "FileTest.txt").toString();
        System.out.println(String.format("Welcome to File Test! \nfilename=%s\nfilepath=%s", filepath, filename));

        // Instantiate class
        FileTest fileTest = new FileTest();
        List<String> data = new ArrayList<String>();

        for (int i = 0; i < 10; i++) {
            data.add("Number " + i + "\n");
        }

        // Create the file in memory
        fileTest.createFile(filename, data);

        // Read data from file
        String dataFromFile = fileTest.readFile(filename, fileTest.FILE_READ_CHUNK_SIZE);
        System.out.println(dataFromFile);

        // Massage the input data into a single string
        StringBuilder dataAsString = new StringBuilder();
        for (String item : data) {
            dataAsString.append(item);
        }

        // Let user know results
        if (dataAsString.toString().equals(dataFromFile)) {
            System.out.println("Data created equals data read from file");
        }
        else {
            System.out.println("Data created does not equal data read from file");
        }
    }
}
