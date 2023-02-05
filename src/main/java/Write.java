import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Write {
    public static void main(String[] args) throws IOException {
        Path path = FileSystems.getDefault().getPath(Constants.filePath, Constants.fileName);
        File file = new File(path.toUri());

        boolean newFile = file.createNewFile();

        if (!newFile) {
            System.out.println("File already existed");
        }

        String userInput;
        try (SeekableByteChannel sbc =
                     Files.newByteChannel(path, StandardOpenOption.APPEND)) {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Type the next line (chars beyond the length of 10 will be cut):");
                userInput = scanner.nextLine();

                String transformedString;

                if (userInput.length() < Constants.BUFFER_SIZE) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < Constants.BUFFER_SIZE; i++) {
                        sb.append(' ');
                    }
                    transformedString = sb.substring(userInput.length()) + userInput;
                    System.out.println("padded: " + transformedString);
                } else {
                    transformedString = userInput.substring(0, Constants.BUFFER_SIZE);
                }

                ByteBuffer bb = ByteBuffer.wrap(transformedString.getBytes(StandardCharsets.UTF_8));
                sbc.write(bb);
            }
        }
    }
}
