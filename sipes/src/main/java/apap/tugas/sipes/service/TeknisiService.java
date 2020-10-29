package apap.tugas.sipes.service;

import apap.tugas.sipes.model.TeknisiModel;

import java.math.BigInteger;
import java.util.List;

public interface TeknisiService {
    // Method menambah teknisi
    void addTeknisi(TeknisiModel teknisi);

    // Method mendapatkan daftar teknisi yang tersimpan
    List<TeknisiModel> getTeknisiList();

    // Method mendapatkan data sebuah teknisi beradasarkan id
    TeknisiModel getTeknisiByIdTeknisi(Long idTeknisi);

}
