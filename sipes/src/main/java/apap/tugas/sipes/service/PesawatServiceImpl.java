package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService{

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public void addPesawat(PesawatModel pesawat){
        pesawatDb.save(pesawat);
    }

    @Override
    public List<PesawatModel> getPesawatList(){
        return pesawatDb.findAll();
    }

    @Override
    public PesawatModel getPesawatByIdPesawat(BigInteger idPesawat){
        try {
            return pesawatDb.findById(idPesawat).get();
        }
        catch(NoSuchElementException nullException){
            return null;
        }
    }

    @Override
    public PesawatModel updatePesawat(PesawatModel pesawat){
        PesawatModel targetPesawat = pesawatDb.findById(pesawat.getId()).get();

        try{
            targetPesawat.setMaskapai(pesawat.getMaskapai());
            targetPesawat.setTanggalDibuat(pesawat.getTanggalDibuat());
            targetPesawat.setTempatDibuat(pesawat.getTempatDibuat());
            targetPesawat.setJenisPesawat(pesawat.getJenisPesawat());
            pesawatDb.save(targetPesawat);
            return targetPesawat;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }

}