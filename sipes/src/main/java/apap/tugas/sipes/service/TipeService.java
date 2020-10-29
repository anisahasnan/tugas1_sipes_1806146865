package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TipeModel;

import java.math.BigInteger;
import java.util.List;

public interface TipeService {
    // Method menambah tipe
    void addTipe(TipeModel tipe);

    // Method mendapatkan daftar tipe yang tersimpan
    List<TipeModel> getTipeList();

    // Method mendapatkan data sebuah tipe beradasarkan id
    TipeModel getTipeByIdTipe(Long idTipe);

}
