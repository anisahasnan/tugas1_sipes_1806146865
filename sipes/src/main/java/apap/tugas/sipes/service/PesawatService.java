package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;

import java.math.BigInteger;
import java.util.List;

public interface PesawatService {
    // Method menambah pesawat
    void addPesawat(PesawatModel pesawat);

    // Method mendapatkan daftar pesawat yang tersimpan
    List<PesawatModel> getPesawatList();

    // Method mendapatkan data sebuah pesawat beradasarkan id
    PesawatModel getPesawatByIdPesawat(Long idPesawat);

    //Method untuk update pesawat
    PesawatModel updatePesawat(PesawatModel pesawat);

    //Method untuk delete penerbangan
    void deletePesawat(Long idPesawat);

    // Method mendapatkan daftar pesawat yang berusia lebih dari 10 tahun
    List<PesawatModel> getPesawatTuaList();

    // Method mendapatkan daftar pesawat yang berusia lebih dari 10 tahun
    List<Integer> getPesawatTuaUsiaList();

    // Method mendapatkan jumlah teknisi untuk semua pesawat
    List<Integer> getJumlahTeknisi();
}
