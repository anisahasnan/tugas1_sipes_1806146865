package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;

import java.math.BigInteger;
import java.util.List;

public interface PenerbanganService {
    // Method menambah penerbangan
    void addPenerbangan(PenerbanganModel penerbangan);

    // Method mendapatkan daftar penerbangan yang tersimpan
    List<PenerbanganModel> getPenerbanganList();

    // Method mendapatkan daftar penerbangan yang belum memiliki pesawat
    List<PenerbanganModel> getPenerbanganListNoPesawat();

    // Method mendapatkan data sebuah penerbangan beradasarkan id
    PenerbanganModel getPenerbanganByIdPenerbangan(Long idPenerbangan);

    //Method untuk update penerbangan
    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);

    //Method untuk delete penerbangan
    void deletePenerbangan(Long idPenerbangan);

}
