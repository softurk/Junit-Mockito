package unittest.customer;

public class MusteriService {

    private MusteriRepository musteriRepository;
    private BilgilendirmeService bilgilendirmeService;

    public void musteriKaydet(Musteri musteri){
        musteriRepository.kaydet(musteri);
        bilgilendirmeService.yeniKayitBilgilendirme(musteri);
    }

    public void setMusteriRepository(MusteriRepository musteriRepository) {
        this.musteriRepository = musteriRepository;
    }

    public void setBilgilendirmeService(BilgilendirmeService bilgilendirmeService) {
        this.bilgilendirmeService = bilgilendirmeService;
    }
}
