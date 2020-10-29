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
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "penerbangan")
public class PenerbanganModel implements Serializable {
    @Id
    @Range(min=1, max=20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "kodeBandaraAsal", nullable = false)
    private String kodeBandaraAsal;

    @NotNull
    @Size(max = 255)
    @Column(name = "kodeBandaraTujuan", nullable = false)
    private String kodeBandaraTujuan;

    @NotNull
    @Column(name = "waktuBerangkat", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate waktuBerangkat;

    @NotNull
    @Size(max = 255)
    @Column(name = "nomorPenerbangan", nullable = false, unique = true)
    private String nomorPenerbangan;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "idPesawat", referencedColumnName = "id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private PesawatModel pesawat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKodeBandaraAsal() {
        return kodeBandaraAsal;
    }

    public void setKodeBandaraAsal(String kodeBandaraAsal) {
        this.kodeBandaraAsal = kodeBandaraAsal;
    }

    public String getKodeBandaraTujuan() {
        return kodeBandaraTujuan;
    }

    public void setKodeBandaraTujuan(String kodeBandaraTujuan) {
        this.kodeBandaraTujuan = kodeBandaraTujuan;
    }

    public LocalDate getWaktuBerangkat() {
        return waktuBerangkat;
    }

    public void setWaktuBerangkat(LocalDate waktuBerangkat) {
        this.waktuBerangkat = waktuBerangkat;
    }

    public String getNomorPenerbangan() {
        return nomorPenerbangan;
    }

    public void setNomorPenerbangan(String nomorPenerbangan) {
        this.nomorPenerbangan = nomorPenerbangan;
    }

    public PesawatModel getPesawat() {
        return pesawat;
    }

    public void setPesawat(PesawatModel pesawat) {
        this.pesawat = pesawat;
    }
}