package unittest.customer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MusteriServiceTest {

    private MusteriService musteriService;
    private MusteriRepository musteriRepository;
    private BilgilendirmeService bilgilendirmeService;

    // Her testden önce yapılması gereken şeyleri yapmamızı sağlar.
    // Böylece her test metodunda MusteriService nesnesi olusturmama gerek kalmadı.
    @Before //NOT1
    public void setUp() throws Exception {
        musteriService = new MusteriService();
        //NOT2
        musteriRepository = Mockito.mock(MusteriRepository.class);
        bilgilendirmeService = Mockito.mock(BilgilendirmeService.class);

        musteriService.setBilgilendirmeService(bilgilendirmeService);
        musteriService.setMusteriRepository(musteriRepository);
    }

    @Test
    public void testMusteriKaydet() {
        //NOT1
        //MusteriService musteriService = new MusteriService();
        //before method sonrası bunu eklemeye gerek kalmadı, nesne en basında(before) olusturuldu

        Musteri musteri = new Musteri();
        musteriService.musteriKaydet(musteri);

        // kontrol etmemiz gerekiyor.

        //NOT2
        //Yukarıda bir gercek bir veritabanı ve bir email servisi kullandıgımız için kontrol işlemini yapmak zorlasıyor
        // veritabanında kayıt olup-olmadıgına yada mail servisinden bir mail gönderilip-gönderilmediğini kontrol etmek gerekecek yani bunun için extra kontroller gerekecek
        // bununla beraber testlerin birbirine bagımlılıgı olmamalı ve heryerde (ister local ister gercek ortam) çalışabilmeli
        // bunu gerceklestirmek için ise Mock kullanıyoruz.Yani sahte nesne kullanıcaz.
        // yukarıda repo ve bilgilendirmeyi mock haline getiriyoruz fakat MusteriService i bunu bilmiyor.


        //herhangi bir mock nesnesinin bir metodu cagrılıp-cagrılmadıgını kontrol edebılırız
        Mockito.verify(musteriRepository).kaydet(musteri);
        Mockito.verify(bilgilendirmeService).yeniKayitBilgilendirme(musteri);

    }

}

