package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.*;
import apap.tugas.sipes.service.PenerbanganService;
import apap.tugas.sipes.service.PesawatService;
import apap.tugas.sipes.service.TipeService;
import apap.tugas.sipes.service.TeknisiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PesawatController{
    @Qualifier("penerbanganServiceImpl")
    @Autowired
    private PenerbanganService penerbanganService;

    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),
                false, 10));
    }

    @GetMapping("/pesawat")
    public String viewAllPesawat(Model model){
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
        model.addAttribute("listPesawat", listPesawat);
        return "view-all-pesawat";
    }

    @GetMapping("/pesawat/tambah")
    public String addPesawatFormPage(Model model){

        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listAllTeknisi = teknisiService.getTeknisiList();

        PesawatModel pesawat = new PesawatModel();
        List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();
        listTeknisi.clear();

        TeknisiModel teknisiBaru = new TeknisiModel();
        listTeknisi.add(teknisiBaru);

        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listAllTeknisi", listAllTeknisi);

        model.addAttribute("listTeknisi", listTeknisi);

        return "form-add-pesawat";
    }

    @PostMapping(value="/pesawat/tambah", params={"addRow"})
    public String pesawatAddRow(
            @ModelAttribute PesawatModel pesawat,
            Model model)
    {
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listAllTeknisi = teknisiService.getTeknisiList();

        List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();

        TeknisiModel teknisiBaru = new TeknisiModel();
        listTeknisi.add(teknisiBaru);

        model.addAttribute("pesawat", pesawat);
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listAllTeknisi", listAllTeknisi);

        model.addAttribute("listTeknisi", listTeknisi);

        return "form-add-pesawat";
    }

    @PostMapping(value="/pesawat/tambah", params={"simpan"})
    public String addPesawatSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model){

        List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();
        for(TeknisiModel teknisi: listTeknisi){
            teknisi.getListPesawat().add(pesawat);
        }

        pesawatService.addPesawat(pesawat);
        model.addAttribute("pesawat", pesawat);
        return "add-pesawat";
    }

    @GetMapping("/pesawat/{idPesawat}")
    public String viewDetailPesawat(
            @PathVariable Long idPesawat,
            Model model){

        if(idPesawat != null){
            PesawatModel pesawat = pesawatService.getPesawatByIdPesawat(idPesawat);
            if (pesawat != null) {
                List<PenerbanganModel> listAllPenerbangan = penerbanganService.getPenerbanganListNoPesawat();
                List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();
                List<PenerbanganModel> listPenerbangan = pesawat.getListPenerbangan();

                model.addAttribute("pesawat", pesawat);
                model.addAttribute("listAllPenerbangan", listAllPenerbangan);
                model.addAttribute("listTeknisi", listTeknisi);
                model.addAttribute("listPenerbangan", listPenerbangan);

                return "view-pesawat";
            }
            else{
                return "pesawat-not-found";
            }
        }
        else{
            return "pesawat-not-found";
        }
    }

    @GetMapping("/pesawat/ubah/{idPesawat}")
    public String changePesawatFormPage(
            @PathVariable Long idPesawat,
            Model model){

        PesawatModel pesawat = pesawatService.getPesawatByIdPesawat(idPesawat);

        if(pesawat != null){
            model.addAttribute("pesawat", pesawat);
            return "form-update-pesawat";
        }
        else{
            return "pesawat-not-found";
        }
    }

    @PostMapping("/pesawat/ubah")
    public String changePesawatFormSubmit(
            @ModelAttribute PesawatModel pesawat,
            Model model){

        if(pesawat != null){
            PesawatModel pesawatUpdated = pesawatService.updatePesawat(pesawat);
            model.addAttribute("pesawat", pesawatUpdated);
            return "update-pesawat";
        }
        else{
            return "pesawat-not-found";
        }
    }

    @GetMapping("/pesawat/hapus/{idPesawat}")
    public String deletePesawat(
            @PathVariable Long idPesawat,
            Model model){

        PesawatModel pesawat = pesawatService.getPesawatByIdPesawat(idPesawat);

        if(pesawat != null){
            pesawatService.deletePesawat(idPesawat);
            model.addAttribute("pesawat", pesawat);
            return "delete-pesawat";
        }
        else{
            return "pesawat-not-found";
        }
    }

    @PostMapping("/pesawat/{idPesawat}/tambah-penerbangan")
    public String addPesawatFormPage(
            @PathVariable Long idPesawat,
            @ModelAttribute PesawatModel pesawat,
            Model model){

        List<PenerbanganModel> listPenerbanganLama = pesawat.getListPenerbangan();
        PenerbanganModel penerbangan = listPenerbanganLama.get(0);
        penerbangan.setPesawat(pesawat);

        PesawatModel pesawatFromDb = pesawatService.getPesawatByIdPesawat(idPesawat);
        List<PenerbanganModel> listPenerbangan = pesawatFromDb.getListPenerbangan();

        PenerbanganModel penerbanganNewest = listPenerbangan.get(listPenerbangan.size()-1);

        model.addAttribute("pesawat", pesawatFromDb);
        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("listAllPenerbangan", penerbanganService.getPenerbanganListNoPesawat());
        model.addAttribute("listTeknisi", pesawatFromDb.getListTeknisi());
        model.addAttribute("penerbanganNewest", penerbanganNewest);

        return "view-pesawat-tambah-penerbangan";
    }

    @GetMapping("/pesawat/filterForm")
    public String filterPesawatForm(Model model){

        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisi = teknisiService.getTeknisiList();

        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listTeknisi", listTeknisi);
        return "filter-pesawat";
    }

    @GetMapping("/pesawat/filter")
    public String filterPesawatFormSubmit(
            @RequestParam(value = "idPenerbangan") Long idPenerbangan,
            @RequestParam(value = "idTipe") Long idTipe,
            @RequestParam(value = "idTeknisi") Long idTeknisi,
            Model model){

        List<PesawatModel> listPesawatFilter = new ArrayList<>();
        List<PesawatModel> listAllPesawat = pesawatService.getPesawatList();

        for(PesawatModel pesawat: listAllPesawat){
            //Penerbangan
            boolean matchPenerbangan;
            if(idPenerbangan != 0){
                List<PenerbanganModel> listPenerbangan = pesawat.getListPenerbangan();
                PenerbanganModel penerbangan = penerbanganService.getPenerbanganByIdPenerbangan(idPenerbangan);
                matchPenerbangan = listPenerbangan.contains(penerbangan);
                //Hanya munculin penerbangan yg difilter
                if(matchPenerbangan){
                    listPenerbangan.clear();
                    listPenerbangan.add(penerbangan);
                }
            }
            else {
                matchPenerbangan = true;
            }

            //Tipe
            boolean matchTipe;
            if(idTipe != 0){
                TipeModel tipe = tipeService.getTipeByIdTipe(idTipe);
                matchTipe = (pesawat.getTipe()).equals(tipe);
            }
            else {
                matchTipe = true;
            }

            //Teknisi
            boolean matchTeknisi;
            if(idTeknisi != 0){
                List<TeknisiModel> listTeknisi = pesawat.getListTeknisi();
                TeknisiModel teknisi = teknisiService.getTeknisiByIdTeknisi(idTeknisi);
                matchTeknisi = listTeknisi.contains(teknisi);
                //Hanya munculin teknisi yg difilter
                if(matchTeknisi){
                    listTeknisi.clear();
                    listTeknisi.add(teknisi);
                }
            }
            else {
                matchTeknisi = true;
            }

            if(matchPenerbangan & matchTipe & matchTeknisi){
                listPesawatFilter.add(pesawat);
            }
        }

        model.addAttribute("listPesawatFilter", listPesawatFilter);

        List<PenerbanganModel> listPenerbangan = penerbanganService.getPenerbanganList();
        List<TipeModel> listTipe = tipeService.getTipeList();
        List<TeknisiModel> listTeknisi = teknisiService.getTeknisiList();

        model.addAttribute("listPenerbangan", listPenerbangan);
        model.addAttribute("listTipe", listTipe);
        model.addAttribute("listTeknisi", listTeknisi);
        return "filter-pesawat";
    }

    @GetMapping("/pesawat/pesawat-tua")
    public String viewPesawatTua(Model model){
        List<PesawatModel> listPesawatTua = pesawatService.getPesawatTuaList();
        List<Integer> listUsiaPesawatTua = pesawatService.getPesawatTuaUsiaList();

        model.addAttribute("listPesawatTua", listPesawatTua);
        model.addAttribute("listUsiaPesawatTua", listUsiaPesawatTua);
        return "view-pesawat-tua";
    }

    @GetMapping("/pesawat/bonus")
    public String viewTotalTeknisiPesawat(Model model){
        List<PesawatModel> listPesawat = pesawatService.getPesawatList();
        List<Integer> listJumlahTeknisi = pesawatService.getJumlahTeknisi();

        model.addAttribute("listPesawat", listPesawat);
        model.addAttribute("listJumlahTeknisi", listJumlahTeknisi);
        return "pesawat-bonus";
    }

}