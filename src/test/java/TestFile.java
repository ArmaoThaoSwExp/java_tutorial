
import org.junit.*;

import org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class TestFile {
    private FileTest tf;

    static class CannotRemoveFileException extends Exception {
        /* Custom error for delete file failure */
        public CannotRemoveFileException(String message) {
            super(message);
        }
    }

    @Before
    public void init() {
        this.tf = new FileTest();
    }

    @After
    public void close() {
        /* End of each test */
        /* Do nothing for now */
    }

    private String createFileNameFullPath(String filename) {
        Path filepath = Paths.get(System.getProperty("user.dir"));
        String result = Paths.get(filepath.toString(), filename).toString();
        return result;
    }

    private static void removeFile(String filename) throws CannotRemoveFileException {
        File userFile = new File(filename);
        // Try removing a file up to 3 times in case system issues.
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                throw new CannotRemoveFileException("Failed to delete file " + filename);
            }
            if (userFile.exists()) {
                userFile.delete();
            }
            else {
                break;
            }
        }
    }

    private boolean fileExists(String filename) {
        File userFile = new File(filename);
        return userFile.exists();
    }

    @Test
    public void createFileBasic() {
        List<String> data = new ArrayList<String>();

        /* Create filename; remove previous file if one exists*/
        String filename = createFileNameFullPath("createFileBasic.txt");
        try {
            removeFile(filename);
        } catch (CannotRemoveFileException exc) {
            // Purposely assert failure
            Assert.assertTrue("\n" + exc.toString() + "\n", false);
        }
        for (int i = 0; i < 10; i++) {
            data.add("Number " + i + "\n");
        }

        // Create the file in memory
        tf.createFile(filename, data);

        // Read data from file
        String dataFromFile = tf.readFile(filename, tf.FILE_READ_CHUNK_SIZE);
        System.out.println(dataFromFile);

        // Massage the input data into a single string
        StringBuilder dataAsString = new StringBuilder();
        for (String item : data) {
            dataAsString.append(item);
        }

        Assert.assertEquals("Data written to file does not match data read from file",
                dataAsString.toString(), dataFromFile.toString());

    }
}