package apap.tugas.sipes.service;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.repository.PenerbanganDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PenerbanganServiceImpl implements PenerbanganService{

    @Autowired
    PenerbanganDb penerbanganDb;

    @Override
    public void addPenerbangan(PenerbanganModel penerbangan){
        penerbanganDb.save(penerbangan);
    }

    @Override
    public List<PenerbanganModel> getPenerbanganList(){
        return penerbanganDb.findAll();
    }

    @Override
    public PenerbanganModel getPenerbanganByIdPenerbangan(Long idPenerbangan){
        try {
            return penerbanganDb.findById(idPenerbangan).get();
        }
        catch(NoSuchElementException nullException){
            return null;
        }
    }

    @Override
    public PenerbanganModel updatePenerbangan(PenerbanganModel penerbangan){
        PenerbanganModel targetPenerbangan = penerbanganDb.findById(penerbangan.getId()).get();

        try{
            targetPenerbangan.setKodeBandaraAsal(penerbangan.getKodeBandaraAsal());
            targetPenerbangan.setKodeBandaraTujuan(penerbangan.getKodeBandaraTujuan());
            targetPenerbangan.setWaktuBerangkat(penerbangan.getWaktuBerangkat());
            targetPenerbangan.setNomorPenerbangan(penerbangan.getNomorPenerbangan());
            penerbanganDb.save(targetPenerbangan);
            return targetPenerbangan;
        }
        catch(NullPointerException nullException){
            return null;
        }
    }

    @Override
    public void deletePenerbangan(Long idPenerbangan){
        penerbanganDb.deleteById(idPenerbangan);
        return ;
    }

    @Override
    public List<PenerbanganModel> getPenerbanganListNoPesawat() {
        List<PenerbanganModel> listPenerbanganNoPesawat = new ArrayList<>();
        List<PenerbanganModel> listPenerbangan = penerbanganDb.findAll();

        for(PenerbanganModel penerbangan: listPenerbangan){
            if(penerbangan.getPesawat() == null){
                listPenerbanganNoPesawat.add(penerbangan);
            }
        }
        return listPenerbanganNoPesawat;
    }
}