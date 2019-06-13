package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import beans.Fone;
import beans.Pessoa;
import persistencia.PessoaDAO;

@ManagedBean(name = "pessoaC")
@SessionScoped
public class PessoaCtrl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa = new Pessoa();
    private String filtro = "";
    private Fone fone = new Fone();

    public Fone getFone() {
        return fone;
    }

    public void setFone(Fone fone) {
        this.fone = fone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getListagem() {
        return PessoaDAO.listagem(filtro);
    }

    public String actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (pessoa.getId() == 0) {
            PessoaDAO.inserir(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            PessoaDAO.alterar(pessoa);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
        return "/publico/cadastro?faces-redirect=true";
    }

    public String actionInserir() {
        pessoa = new Pessoa();
        return "/admin/lista_pessoa";
    }

    public String deletPessoa() {
        PessoaDAO.excluir(pessoa);
        return "/admin/lista_pessoa";
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Pessoa Selecionada",
                String.valueOf(((Pessoa) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String addFone() {
        Fone fone = new Fone();
        fone.setPessoa(pessoa);
        pessoa.getFones().add(fone);
        return "/publico/cadastro";
    }

    public String deletFone(Fone fone) {
        PessoaDAO.excluirFone(fone);
        pessoa.setFones(PessoaDAO.listagemFone(pessoa));
        return "/publico/cadastro";
    }

}
