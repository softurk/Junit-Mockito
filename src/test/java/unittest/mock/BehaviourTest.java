package unittest.mock;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;

public class BehaviourTest {

    private DummyCustomerService customerService;

    @Before
    public void setUp() throws Exception {
        customerService = mock(DummyCustomerService.class);
    }


    @Test
    public void testBehaviour() throws Exception {

        customerService.addCustomer("istanbul");
        customerService.addCustomer("ankara");

        verify(customerService).addCustomer("istanbul");
        verify(customerService).addCustomer("ankara");

    }

    @Test
    public void testKacKereCagrildi() throws Exception {

        customerService.addCustomer("istanbul");
        customerService.addCustomer("istanbul");
        customerService.addCustomer("istanbul");
        customerService.addCustomer("ankara");

        // addCustomer(istanbul) 3 kez cagrıldı
        verify(customerService, times(3)).addCustomer("istanbul");
        verify(customerService, times(1)).addCustomer("ankara");

        // removeCustomer(hiçbir string parametresi ile) hic bir zaman cagrılmadı
        verify(customerService, never()).removeCustomer(anyString());

        verify(customerService, never()).addCustomer("izmir");

        //en az 2 defa cagrıldı
        verify(customerService, atLeast(1)).addCustomer("ankara");

    }

    @Test
    public void testSirasiniKontrolEt() {

        customerService.addCustomer("istanbul");
        customerService.addCustomer("ankara");
        customerService.addCustomer("izmir");


        //istedigimiz kadar mock nesnesi verebiliriz
        InOrder inOrder = inOrder(customerService);

        //verildigi sırada cagrılsın
        inOrder.verify(customerService).addCustomer("istanbul"); //0
        inOrder.verify(customerService).addCustomer("ankara");   //1
        inOrder.verify(customerService).addCustomer("izmir");    //2

    }

    @Test
    public void testBaskaKontrolYok() {

        DummyCustomerService customerService2 = mock(DummyCustomerService.class);

        customerService.addCustomer("istanbul");
        customerService.removeCustomer("ankara");

        customerService2.addCustomer("istanbul");
        customerService2.removeCustomer("ankara");

        verify(customerService).addCustomer("istanbul");
        verify(customerService).removeCustomer("ankara");

        verify(customerService2).addCustomer("istanbul");
        verify(customerService2).removeCustomer("ankara");

        verifyNoMoreInteractions(customerService);  //yukarıdaki verify cagrımı dısında, hiç bir cagrım olmasın
        verifyZeroInteractions(customerService2);  //hiç cağrılmasın
    }


    @Test
    //Methoda davranıssal olarak bırseyler yukledık
    public void testWhen() {
        //NOT: when/then kullanıldıgında her zaman gerı donus olması gereklidir

        //when(customerService.getCustomer(anyString())).thenReturn("Muhammed");
        when(customerService.getCustomer(eq("istanbul"))).thenReturn("Muhammed");

        String customerName = customerService.getCustomer("istanbul");
        System.out.println("customerName " + customerName);

        assertEquals("Muhammed", customerName);

    }

    @Test
    //Methodun exception fırlatması
    public void testWhenReturnException() {
        //NOT: when/then kullanıldıgında her zaman gerı donus olması gereklidir

        when(customerService.getCustomer(eq("istanbul"))).thenThrow(RuntimeException.class);

        String customerName = customerService.getCustomer("istanbul");
        System.out.println("customerName " + customerName);

        assertEquals("Muhammed", customerName);

    }

    @Test
    public void testWhenReturnVoidMethod() throws Exception {

        //void geri veren methodlarda nasıl when kullanılır ?

        //do ile baslayan methodlarla
        doNothing().when(customerService).addCustomer(anyString());
        doThrow(RuntimeException.class).when(customerService).addCustomer(anyString());

    }

}
