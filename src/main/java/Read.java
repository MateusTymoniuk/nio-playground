import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Read {
    public static void main(String[] args) {

        Path path = FileSystems.getDefault().getPath(Constants.filePath, Constants.fileName);
        String userInput;
        ByteBuffer bb = ByteBuffer.allocate(Constants.BUFFER_SIZE);
        try (SeekableByteChannel sbc =
                     Files.newByteChannel(path, StandardOpenOption.READ)) {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Press n to read next " + Constants.BUFFER_SIZE + " bytes or e to exit");
                userInput = scanner.nextLine();

                if ("e".equals(userInput)) break;

                sbc.size();

                sbc.read(bb);

                System.out.println("has remaining: " + bb.hasRemaining());
                System.out.println(new String(bb.array(), 0, Constants.BUFFER_SIZE));
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(bb);
                // this will only print when we reach the end of the file
                System.out.println(charBuffer.array());
                bb.clear();
            }
        } catch (IOException e) {
            System.out.println("Can't read the file");
        }
    }
}
