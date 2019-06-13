package negocio;

import java.io.Serializable;
import java.util.List;

import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import beans.FormaPgto;
import persistencia.FormaPgtoDAO;

@ManagedBean(name = "formaPgtoC")
@SessionScoped
public class FormaPgtoCtrl implements Serializable {

    private static final long serialVersionUID = 1L;
    private FormaPgto formaPgto = new FormaPgto();
    private String filtro = "";

    public FormaPgto getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(FormaPgto formaPgto) {
        this.formaPgto = formaPgto;
    }

    public List<FormaPgto> getListagem() {
        return FormaPgtoDAO.listagem(filtro);
    }

    public String actionGravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (formaPgto.getId() == 0) {
            FormaPgtoDAO.inserir(formaPgto);
            context.addMessage(null, new FacesMessage("Sucesso", "Inserido com sucesso!"));
        } else {
            FormaPgtoDAO.alterar(formaPgto);
            context.addMessage(null, new FacesMessage("Sucesso", "Alterado com sucesso!"));
        }
        return "/admin/lista_formaPgto";
    }

    public String addFormaPgto() {
        formaPgto = new FormaPgto();
        return "/admin/lista_formaPgto";
    }

    public String deletFormaPgto() {
        FormaPgtoDAO.excluir(formaPgto);
        return "/admin/lista_formaPgto";
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Forma de Pagamento Selecionada",
                String.valueOf(((FormaPgto) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
