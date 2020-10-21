package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;

import java.math.BigInteger;
import java.util.List;

public interface PesawatService {
    // Method menambah pesawat
    void addPesawat(PesawatModel pesawat);

    // Method mendapatkan daftar pesawat yang tersimpan
    List<PesawatModel> getPesawatList();

    // Method mendapatkan data sebuah pesawat beradasarkan id
    PesawatModel getPesawatByIdPesawat(BigInteger idPesawat);

    //Method untuk update pesawat
    PesawatModel updatePesawat(PesawatModel pesawat);

}
