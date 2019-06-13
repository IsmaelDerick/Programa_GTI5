package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pes_id")
    private int id;
    @Column(name = "pes_nome", length = 60, nullable = true)
    private String nome;
    @Column(name = "pes_cpf", length = 14, nullable = true)
    private String cpf;
    @Column(name = "pes_rg", length = 20, nullable = true)
    private String rg;
    @Column(name = "pes_data_nasc", nullable = true)
    private Date data_nasc;
    @Column(name = "pes_rua", length = 60, nullable = true)
    private String rua;
    @Column(name = "pes_bairro", length = 30, nullable = true)
    private String bairro;
    @Column(name = "pes_cidade", length = 30, nullable = true)
    private String cidade;
    @Column(name = "pes_uf", length = 2, nullable = true)
    private String uf;
    @Column(name = "pes_cep", nullable = true)
    private int cep;
    @Column(name = "pes_email", length = 40, nullable = true)
    private String email;
    @Column(name = "pes_senha", length = 32, nullable = true)
    private String senha;
    @Column(name = "pes_tipo", length = 30, nullable = true)
    private String tipo;
    //@Column(name = "pes_situacao", length = 10, nullable = false)
    //private Boolean situacao;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getCep() {
        return cep;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Fone> fones = new ArrayList<Fone>();

    public List<Fone> getFones() {
        return fones;
    }

    public void setFones(List<Fone> fones) {
        this.fones = fones;
    }

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedido = new ArrayList<Pedido>();

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(List<Pedido> pedido) {
        this.pedido = pedido;
    }

}
