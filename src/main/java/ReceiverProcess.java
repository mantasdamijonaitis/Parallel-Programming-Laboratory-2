import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;

public class ReceiverProcess implements CSProcess {

    private final AltingChannelInput<Double> inputFromServiceProcess;
    int elementAmount = 0;

    public ReceiverProcess(AltingChannelInput<Double> inputFromServiceProcess) {
        this.inputFromServiceProcess = inputFromServiceProcess;
    }

    @Override
    public void run() {
        boolean keepLooking = true;
        while (keepLooking) {
            Double receivedValue = this.inputFromServiceProcess.read();
            if (receivedValue == null) {
                keepLooking = false;
            } else {
                System.out.println("Eil. nr.: " + (++elementAmount) + " receivedValue: " + receivedValue);
            }
        }
    }
}
