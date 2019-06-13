package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_id")
    private int id;
    @Column(name = "ped_data", nullable = true)
    private Date ped_dataEmissao;
    @Column(name = "ped_status", length = 20, nullable = true)
    private String ped_status;
    @Column(name = "ped_dataautorizacao", nullable = true)
    private Date ped_dataAutorizacao;
    @Column(name = "ped_valor", nullable = true)
    private float ped_total;
    @Column(name = "ped_desconto", nullable = true)
    private float ped_desconto;
    @Column(name = "ped_qtdparcelas", nullable = true)
    private int ped_qtdeParc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPed_dataEmissao() {
        return ped_dataEmissao;
    }

    public void setPed_dataEmissao(Date ped_dataEmissao) {
        this.ped_dataEmissao = ped_dataEmissao;
    }

    public String getPed_status() {
        return ped_status;
    }

    public void setPed_status(String ped_status) {
        this.ped_status = ped_status;
    }

    public Date getPed_dataAutorizacao() {
        return ped_dataAutorizacao;
    }

    public void setPed_dataAutorizacao(Date ped_dataAutorizacao) {
        this.ped_dataAutorizacao = ped_dataAutorizacao;
    }

    public float getPed_total() {
        return ped_total;
    }

    public void setPed_total(float ped_total) {
        this.ped_total = ped_total;
    }

    public float getPed_desconto() {
        return ped_desconto;
    }

    public void setPed_desconto(float ped_desconto) {
        this.ped_desconto = ped_desconto;
    }

    public int getPed_qtdeParc() {
        return ped_qtdeParc;
    }

    public void setPed_qtdeParc(int ped_qtdeParc) {
        this.ped_qtdeParc = ped_qtdeParc;
    }

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItensPedido> itensPedido = new ArrayList<ItensPedido>();

    public List<ItensPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItensPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    @ManyToOne
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @ManyToOne
    @JoinColumn(name = "fpg_id")
    private FormaPgto formaPgto;

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

}
