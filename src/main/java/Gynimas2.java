import org.jcsp.lang.*;

public class Gynimas2 {
    public static void main(String[] args) {

        int arraySize = 10;

        One2OneChannelInt firstSenderToReceiverChannel = Channel.one2oneInt();
        One2OneChannelInt secondSenderToReceiverChannel = Channel.one2oneInt();
        One2OneChannelInt thirdSenderToReceiverChannel = Channel.one2oneInt();
        One2OneChannelInt fourthSenderToReceiverChannel = Channel.one2oneInt();
        One2OneChannel<Double> serviceToReceiverChannel = Channel.one2one();

        int[] firstChannelData = new int[arraySize];
        int[] secondChannelData = new int[arraySize];
        int[] thirdChannelData = new int[arraySize];
        int[] fourthChannelData = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            firstChannelData[i] = i + 1;
            secondChannelData[i] = i + 10;
            thirdChannelData[i] = i + 20;
            fourthChannelData[i] = i + 30;
        }

        SenderProcess firstSenderProcess = new SenderProcess(firstChannelData, firstSenderToReceiverChannel.out());
        SenderProcess secondSenderProcess = new SenderProcess(secondChannelData, secondSenderToReceiverChannel.out());
        SenderProcess thirdSenderProcess = new SenderProcess(thirdChannelData, thirdSenderToReceiverChannel.out());
        SenderProcess fourthSenderProcess = new SenderProcess(fourthChannelData, fourthSenderToReceiverChannel.out());
        ServiceProcess serviceProcess = new ServiceProcess(
                firstSenderToReceiverChannel.in(),
                secondSenderToReceiverChannel.in(),
                thirdSenderToReceiverChannel.in(),
                fourthSenderToReceiverChannel.in(),
                serviceToReceiverChannel.out());
        ReceiverProcess receiverProcess = new ReceiverProcess(
                serviceToReceiverChannel.in());
        Parallel parallel = new Parallel();
        parallel.addProcess(firstSenderProcess);
        parallel.addProcess(secondSenderProcess);
        parallel.addProcess(thirdSenderProcess);
        parallel.addProcess(fourthSenderProcess);
        parallel.addProcess(serviceProcess);
        parallel.addProcess(receiverProcess);
        parallel.run();


    }
}
