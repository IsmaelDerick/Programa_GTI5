package negocio;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.ItensPedido;
import beans.Pedido;
import beans.Produto;

@ManagedBean(name = "pedido")
@SessionScoped
public class PedidoCtrl {

    Pedido pedido = new Pedido();
    ItensPedido itensPedido = new ItensPedido();
    List<ItensPedido> itensPedidos = new ArrayList<ItensPedido>();

    public void actionInserirProduto() {
        Produto produto = new Produto();
        itensPedido.setProduto(produto);
        System.out.println("Inserido");
    }

    public void actionFinalizarCompra() {
        Produto produto = new Produto();
        ItensPedido itensPedido = new ItensPedido();
        itensPedido.setProduto(produto);
        pedido.getItensPedido().add(itensPedido);
    }

    public List<ItensPedido> getItensPedido() {
        return itensPedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }

    public void setItensPedido(ItensPedido itensPedido) {
        this.itensPedido = itensPedido;
    }

}
