package apap.tugas.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable {
    @Id
    @Range(min=1, max=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "maskapai", nullable = false)
    private String maskapai;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomorSeri", nullable = false, unique = true)
    private String nomorSeri;

    @NotNull
    @Size(max = 255)
    @Column(name = "tempatDibuat", nullable = false)
    private String tempatDibuat;

    @NotNull
    @Column(name = "tanggalDibuat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate tanggalDibuat;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenisPesawat", nullable = false)
    private String jenisPesawat;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "idTipe", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private TipeModel tipe;

    @OneToMany(mappedBy = "pesawat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PenerbanganModel> listPenerbangan;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "pesawat_teknisi",
            joinColumns = @JoinColumn(name = "id_pesawat", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_teknisi", referencedColumnName = "id"))
    private List<TeknisiModel> listTeknisi = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaskapai() {
        return maskapai;
    }

    public void setMaskapai(String maskapai) {
        this.maskapai = maskapai;
    }

    public String getNomorSeri() {
        return nomorSeri;
    }

    public void setNomorSeri(String nomorSeri) {
        this.nomorSeri = nomorSeri;
    }

    public String getTempatDibuat() {
        return tempatDibuat;
    }

    public void setTempatDibuat(String tempatDibuat) {
        this.tempatDibuat = tempatDibuat;
    }

    public LocalDate getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(LocalDate tanggalDibuat) {
        this.tanggalDibuat = tanggalDibuat;
    }

    public String getJenisPesawat() {
        return jenisPesawat;
    }

    public void setJenisPesawat(String jenisPesawat) {
        this.jenisPesawat = jenisPesawat;
    }

    public TipeModel getTipe() {
        return tipe;
    }

    public void setTipe(TipeModel tipe) {
        this.tipe = tipe;
    }

    public List<PenerbanganModel> getListPenerbangan() {
        return listPenerbangan;
    }

    public void setListPenerbangan(List<PenerbanganModel> listPenerbangan) {
        this.listPenerbangan = listPenerbangan;
    }

    public List<TeknisiModel> getListTeknisi() {
        return listTeknisi;
    }

    public void setListTeknisi(List<TeknisiModel> listTeknisi) {
        this.listTeknisi = listTeknisi;
    }
}