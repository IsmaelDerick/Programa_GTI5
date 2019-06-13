package negocio;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import beans.FormaPgto;
import beans.ItensPedido;
import beans.Pedido;
import beans.Pessoa;
import beans.Produto;
import persistencia.PedidoDAO;
import persistencia.PessoaDAO;

@ManagedBean(name = "carrinhoC")
@SessionScoped
public class CarrinhoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pedido pedido = new Pedido();
    private Produto produto = new Produto();
    private FormaPgto formaPgto = new FormaPgto();
    private Pessoa pessoa;
    private boolean parcelas = true;

    public String addProdCar(Produto produto) {
        ItensPedido itensPedido = new ItensPedido();
        itensPedido.setPedido(pedido);
        itensPedido.setProduto(produto);
        itensPedido.setIpe_valorUnit(produto.getPreco());
        itensPedido.setIpe_qtde(1);
        itensPedido.setIpe_subTotal(produto.getPreco());
        this.pedido.getItensPedido().add(itensPedido);
        return "/publico/index?faces-redirect=true ";
    }

    public Float calcValorTotal() {
        Float total = 0f;

        if (pedido.getItensPedido().isEmpty()) {
            return total;
        } else {
            for (int i = 0; i < pedido.getItensPedido().size(); i++) {
                total += (pedido.getItensPedido().get(i).getIpe_subTotal());
            }
            return total;
        }
    }

    public String actionAddOne(ItensPedido itensPedido) {
        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            if (itensPedido.getProduto().getNome().equals(pedido.getItensPedido().get(i).getProduto().getNome())) {
                itensPedido.setIpe_qtde(itensPedido.getIpe_qtde() + 1);
            }
        }
        return "/cliente/carrinho?faces-redirect=true";
    }

    public String actionRemoveOne(ItensPedido itensPedido) {
        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            if (itensPedido.getProduto().getNome().equals(pedido.getItensPedido().get(i).getProduto().getNome())) {
                itensPedido.setIpe_qtde(itensPedido.getIpe_qtde() - 1);
            }
        }
        return "/cliente/carrinho?faces-redirect=true";
    }

    public void valorDoPedido() {
        float vltotal = 0;
        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            vltotal += pedido.getItensPedido().get(i).getIpe_valorUnit()
                    * pedido.getItensPedido().get(i).getIpe_qtde();
        }
        this.pedido.setPed_total(vltotal);
    }

    public String irCarrinho() {
        ativarProd();
        valorDoPedido();
        return "/cliente/carrinho?faces-redirect=true";
    }

    public void ativarProd() {
        float total = 0;
        float soma = 0;
        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            total = soma + pedido.getItensPedido().get(i).getProduto().getPreco();
            pedido.getItensPedido().get(i).setIpe_valorUnit(pedido.getItensPedido().get(i).getProduto().getPreco());
            pedido.getItensPedido().get(i).setIpe_subTotal(pedido.getItensPedido().get(i).getProduto().getPreco());
            pedido.getItensPedido().get(i).setIpe_qtde(1);
        }
        pedido.setPed_total(total);
    }

    public int getQtdeTotal() {
        if (pedido.getItensPedido().isEmpty()) {
            return 0;
        }
        int total = 0;
        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            total += (this.pedido.getItensPedido().get(i).getIpe_qtde());
        }
        return total;
    }

    public String gravarPed() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Pessoa clienteLogado = PessoaDAO.searchUsuarioLogado(getUsuarioLogado());

            pedido.setPessoa(clienteLogado);
            pedido.setFormaPgto(formaPgto);
            pedido.setPed_dataEmissao(new Date());
            pedido.setPed_status("ABERTO");
            pedido.setPed_dataAutorizacao(new Date());
            PedidoDAO.inserir(pedido);
        } catch (Exception e) {
            System.out.println("Erro ao salvar o pedido!" + e.getMessage());
        }

        pedido.getItensPedido().clear();
        context.addMessage(null, new FacesMessage("Sucesso", "Compra realizada com sucesso"));
        return "/publico/index?faces-redirect=true";
    }

    public String definParcelas() {
        if (this.formaPgto.getId() == 6) {
            this.parcelas = false;
        } else {
            this.parcelas = true;
            pedido.setPed_qtdeParc(0);
        }
        return null;
    }

    public String calcQuantidadeProduto(ItensPedido itensPedido) {
        itensPedido.setIpe_valorUnit(itensPedido.getProduto().getPreco());
        itensPedido.setIpe_subTotal(itensPedido.getIpe_qtde() * itensPedido.getIpe_valorUnit());
        valorDoPedido();
        return null;
    }

    public String formaDePag() {
        return "/cliente/forma_de_pagamento?faces-redirect=true";
    }

    public String deletProdCar(ItensPedido itensPedido) {
        for (int i = 0; i < pedido.getItensPedido().size(); i++) {
            if (pedido.getItensPedido().size() >= 1) {
                pedido.getItensPedido().remove(itensPedido);

            }
        }
        irCarrinho();
        return "/cliente/carrinho?faces-redirect=true";
    }

    public String getUsuarioLogado() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
