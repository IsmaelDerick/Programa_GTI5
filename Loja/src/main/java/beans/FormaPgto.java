package beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "forma_Pgto")
public class FormaPgto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fpg_id")
    private int id;
    @Column(name = "fpg_descricao", length = 20, nullable = true)
    private String descricao;
    @Column(name = "fpg_num_max_parc", nullable = true)
    private int numMaxParc;
    @Column(name = "fpg_num_padrao_parc", nullable = true)
    private int numPadraoParc;
    @Column(name = "fpg_intervalo_dias", nullable = true)
    private int intervaloDias;
    @Column(name = "fpg_percentual_acres", nullable = true)
    private int percentualAcres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getNumMaxParc() {
        return numMaxParc;
    }

    public void setNumMaxParc(int numMaxParc) {
        this.numMaxParc = numMaxParc;
    }

    public int getNumPadraoParc() {
        return numPadraoParc;
    }

    public void setNumPadraoParc(int numPadraoParc) {
        this.numPadraoParc = numPadraoParc;
    }

    public int getIntervaloDias() {
        return intervaloDias;
    }

    public void setIntervaloDias(int intervaloDias) {
        this.intervaloDias = intervaloDias;
    }

    public int getPercentualAcres() {
        return percentualAcres;
    }

    public void setPercentualAcres(int percentualAcres) {
        this.percentualAcres = percentualAcres;
    }

    @OneToMany(mappedBy = "formaPgto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<Pedido>();

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

}
