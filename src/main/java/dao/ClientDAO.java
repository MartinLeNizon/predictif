package dao;


import metier.modele.Client;
import metier.modele.Consultation;
import metier.modele.Employe;
import java.util.List;
import javax.persistence.TypedQuery;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mnguyen
 */
public class ClientDAO {
    
    public ClientDAO() {}
    
    public void creer(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    public Client chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);   
    }
    
     public List<Client> chercherParMail(String mail, String motDePasse) {
        String s = "select c from Client c where c.mail = :unMail and c.motDePasse = :unMotDePasse";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Client.class);
        query.setParameter("unMail", mail);
        query.setParameter("unMotDePasse", motDePasse);
        return query.getResultList();
    }
     
     public Client chercherParEmploye(Employe monEmploye) {
        String s = "select c.client from Consultation c where c.employe = :unEmploye and c.etat = :etat";
        TypedQuery <Client> query = JpaUtil.obtenirContextePersistance().createQuery(s,Client.class);
        query.setParameter("unEmploye", monEmploye);
        query.setParameter("etat", "Demandée");
        return query.getResultList().get(0);
    }
}
