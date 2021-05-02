package unittest.customer;

//Gercek ORACLE Musteri Repository

public class MusteriRepository {

    public void kaydet (Musteri musteri) {
        System.out.println("kaydedildi");
    }

    public void sil (Integer musteriId) {
        System.out.println("musteri silindi");
    }
}
