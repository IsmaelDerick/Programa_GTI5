package beans;

import javax.persistence.*;

@Entity
@Table(name = "itens_pedido")
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ipe_id")
    private int id;
    @Column(name = "ipe_qtde", nullable = true)
    private float ipe_qtde;
    @Column(name = "ipe_valorunit", nullable = true)
    private float ipe_valorUnit;
    @Column(name = "ipe_total", nullable = true)
    private float ipe_subTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getIpe_qtde() {
        return ipe_qtde;
    }

    public void setIpe_qtde(float ipe_qtde) {
        this.ipe_qtde = ipe_qtde;
    }

    public float getIpe_valorUnit() {
        return ipe_valorUnit;
    }

    public void setIpe_valorUnit(float ipe_valorUnit) {
        this.ipe_valorUnit = ipe_valorUnit;
    }

    public float getIpe_subTotal() {
        return ipe_subTotal;
    }

    public void setIpe_subTotal(float ipe_subTotal) {
        this.ipe_subTotal = ipe_subTotal;
    }

    @ManyToOne
    @JoinColumn(name = "ped_id")
    private Pedido pedido;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Produto produto;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
