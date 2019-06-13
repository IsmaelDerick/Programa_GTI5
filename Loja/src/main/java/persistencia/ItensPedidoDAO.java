package persistencia;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import beans.ItensPedido;

@Component
public class ItensPedidoDAO implements Serializable {

    private static final long serialVersionUID = 1L;

    public static void inserir(ItensPedido itensPedido) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction t = sessao.beginTransaction();
        sessao.save(itensPedido);
        t.commit();
        sessao.close();
    }

}
