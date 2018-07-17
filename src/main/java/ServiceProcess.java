import org.jcsp.lang.AltingChannelInputInt;
import org.jcsp.lang.CSProcess;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.lang.ChannelOutputInt;

public class ServiceProcess implements CSProcess {

    private static final int ARRAY_SIZE = 10;

    private final AltingChannelInputInt inputFromFirstSender;
    private final AltingChannelInputInt inputFromSecondSender;
    private final AltingChannelInputInt inputFromThirdSender;
    private final AltingChannelInputInt inputFromFourthSender;
    private final ChannelOutput<Double> outputToReceiverProcess;

    public ServiceProcess(AltingChannelInputInt inputFromFirstSender,
                          AltingChannelInputInt inputFromSecondSender,
                          AltingChannelInputInt inputFromThirdSender,
                          AltingChannelInputInt inputFromFourthSender,
                          ChannelOutput<Double> outputToReceiverProcess) {
        this.inputFromFirstSender = inputFromFirstSender;
        this.inputFromSecondSender = inputFromSecondSender;
        this.inputFromThirdSender = inputFromThirdSender;
        this.inputFromFourthSender = inputFromFourthSender;
        this.outputToReceiverProcess = outputToReceiverProcess;
    }

    @Override
    public void run() {
        for (int i = 0; i < ARRAY_SIZE; i++) {
            int firstSenderResult = inputFromFirstSender.read();
            int secondSenderResult = inputFromSecondSender.read();
            int thirdSenderResult = inputFromThirdSender.read();
            int fourthSenderResult = inputFromFourthSender.read();
            double firstAndSecondAverage = (firstSenderResult + secondSenderResult) / 2;
            double thirdAndFourthAverage = (thirdSenderResult + fourthSenderResult) / 2;
            outputToReceiverProcess.write(firstAndSecondAverage);
            outputToReceiverProcess.write(thirdAndFourthAverage);
        }
        outputToReceiverProcess.write(null);
    }
}
