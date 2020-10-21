package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;

import java.math.BigInteger;
import java.util.List;

public interface PenerbanganService {
    // Method menambah penerbangan
    void addPenerbangan(PenerbanganModel penerbangan);

    // Method mendapatkan daftar penerbangan yang tersimpan
    List<PenerbanganModel> getPenerbanganList();

    // Method mendapatkan data sebuah penerbangan beradasarkan id
    PenerbanganModel getPenerbanganByIdPenerbangan(BigInteger idPenerbangan);

    //Method untuk update penerbangan
    PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan);

}
