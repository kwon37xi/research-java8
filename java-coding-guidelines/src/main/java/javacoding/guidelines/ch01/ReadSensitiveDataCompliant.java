package javacoding.guidelines.ch01;

import heapdumps.HeapDumper;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 민감한 데이터는 String 으로 읽으면 안된다. 메모리에 데이터가 GC 될 때까지 남는다.
 * <p>
 * NIO ByteBuffer로 만들고 사용직후 clear 하라.
 *
 * @see <a href="https://gs.saro.me/#!m=elec&jn=537">Java NIO ByteBuffer란?</a>
 */
public class ReadSensitiveDataCompliant {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);

        try (FileChannel rdr = (new FileInputStream("README.md")).getChannel()) {

            while (rdr.read(buffer) > 0) { // byte buffer에 채널에 있는 데이터를 채운다.
                // do something
                buffer.clear(); // 이것도 try/catch/finally에 있어야 하지 않을까?
                // direct buffer는 가비지 수집이 되지 않으므로 명시적으로 삭제해야함.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        HeapDumper.dumpHeap("/tmp/ch01_read_sensitive_data_compliant.hprof", true);
    }

}
