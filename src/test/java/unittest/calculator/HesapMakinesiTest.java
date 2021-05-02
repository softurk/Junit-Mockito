package unittest.calculator;

import org.junit.Assert;
import org.junit.Test;

public class HesapMakinesiTest {

    @Test
    public void testTopla() {

        // ön hazırlık
        HesapMakinesi hesapMakinesi = new HesapMakinesi();
        int sayi1 = 5;
        int sayi2 = 19;

        // method calıstırılır
        int toplam =  hesapMakinesi.topla(sayi1,sayi2);

        //sonucu kontrol et
        Assert.assertEquals(24, toplam);

    }

    @Test
    public void testCikart() {

        // ön hazırlık - GIVEN
        HesapMakinesi hesapMakinesi = new HesapMakinesi();
        int sayi1 = 5;
        int sayi2 = 19;

        // method calıstırılır - WHEN
        int cikartToplam =  hesapMakinesi.cikart(sayi1,sayi2);

        //sonucu kontrol et - THEN
        Assert.assertEquals(-14, cikartToplam);

    }




}

