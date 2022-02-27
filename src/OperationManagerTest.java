import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class OperationManagerTest {

    @org.junit.jupiter.api.Test
        void IAmDownloadingTest1() {
        Assertions.assertEquals(false, OperationManager.IAmDownloading(1));
        Assertions.assertEquals(true, OperationManager.IAmDownloading(8));
    }

    @org.junit.jupiter.api.Test
    void SomeoneGetsErasedTest1() {
        Assertions.assertEquals(false, OperationManager.IAmDownloading(1));
        Assertions.assertEquals(true, OperationManager.IAmDownloading(8));
        Assertions.assertEquals(false, OperationManager.IAmDownloading(1337));
    }
}