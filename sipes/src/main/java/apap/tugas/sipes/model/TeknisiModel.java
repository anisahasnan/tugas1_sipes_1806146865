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
import java.util.Set;

@Entity
@Table(name = "teknisi")
public class TeknisiModel implements Serializable {
    @Id
    @Size(max = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Column(name = "nomorTelepon", nullable = false)
    private Long nomorTelepon;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "teknisi")
    private Set<PesawatModel> listPesawat;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(Long nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public Set<PesawatModel> getListPesawat() {
        return listPesawat;
    }

    public void setListPesawat(Set<PesawatModel> listPesawat) {
        this.listPesawat = listPesawat;
    }
}