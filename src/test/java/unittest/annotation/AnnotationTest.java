package unittest.annotation;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import unittest.customer.BilgilendirmeService;
import unittest.customer.Musteri;
import unittest.customer.MusteriRepository;
import unittest.customer.MusteriService;

@RunWith(MockitoJUnitRunner.class)
public class AnnotationTest {

    @InjectMocks
    private MusteriService musteriService;

    @Mock
    private MusteriRepository musteriRepository;

    @Mock
    private BilgilendirmeService bilgilendirmeService;

    @Test
    public void testAnnotation() throws Exception {

        Musteri musteri = new Musteri();
        musteriService.musteriKaydet(musteri);

        Mockito.verify(musteriRepository).kaydet(musteri);
        Mockito.verify(bilgilendirmeService).yeniKayitBilgilendirme(musteri);

    }
}
