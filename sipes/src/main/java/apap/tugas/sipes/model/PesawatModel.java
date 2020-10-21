package apap.tugas.sipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pesawat")
public class PesawatModel implements Serializable {
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

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
    private Date tanggalDibuat;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "pesawat_teknisi",
            joinColumns = { @JoinColumn(name = "id_pesawat") },
            inverseJoinColumns = { @JoinColumn(name = "id_teknisi") }
    )
    private Set<TeknisiModel> listTeknisi;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public Date getTanggalDibuat() {
        return tanggalDibuat;
    }

    public void setTanggalDibuat(Date tanggalDibuat) {
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

    public Set<TeknisiModel> getListTeknisi() {
        return listTeknisi;
    }

    public void setListTeknisi(Set<TeknisiModel> listTeknisi) {
        this.listTeknisi = listTeknisi;
    }
}