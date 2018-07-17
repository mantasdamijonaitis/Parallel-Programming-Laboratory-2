import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutputInt;

public class SenderProcess implements CSProcess {

    private final int[] data;
    private final ChannelOutputInt outputToServiceProcess;

    public SenderProcess(int[] data,
                         ChannelOutputInt outputToServiceProcess) {
        this.data = data;
        this.outputToServiceProcess = outputToServiceProcess;
    }

    @Override
    public void run() {
        for (int i = 0; i < data.length; i++) {
            outputToServiceProcess.write(data[i]);
        }
    }
}
