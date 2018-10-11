
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

public class ControllerAgenda {

    ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;
    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            if (e.getSource() == viewAgenda.jbtn_primero) {
                try {
                    jbtn_primero_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                try {
                    jbtn_anterior_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                try {
                    jbtn_siguiente_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                try {
                    jbtn_ultimo_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(e.getSource() == viewAgenda.jbt_eliminar){
                try {
                    jbtn_eliminar();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == viewAgenda.jbt_insertar){
                try {
                    jbtn_insertar();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == viewAgenda.jbt_modificar){
                try {
                    jbtn_modificar();
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerAgenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getSource() == viewAgenda.jbt_nuevo){
                jbtn_nuevo();
            }

        }

    };

    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }

    public void initDB(){
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
    }

    public void initComponents() {
        viewAgenda.setLocationRelativeTo(null);
        viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
    }


    public void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbt_eliminar.addActionListener(actionListener);
        viewAgenda.jbt_insertar.addActionListener(actionListener);
        viewAgenda.jbt_modificar.addActionListener(actionListener);
        viewAgenda.jbt_nuevo.addActionListener(actionListener);
    }

 
    private void jbtn_primero_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_primero");
        modelAgenda.moverPrimerRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());

    }


    private void jbtn_anterior_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
    }


    private void jbtn_ultimo_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
 
    }
    private void jbtn_siguiente_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
       
  
    }
    private void jbtn_eliminar() throws SQLException {
            System.out.println("Action del boton jbtn_eliminar");
            System.out.println(modelAgenda.getEmail());
            modelAgenda.eliminarRegistro(modelAgenda.getEmail());
            JOptionPane.showMessageDialog(viewAgenda, "Registro eliminado correctamente");
            jbtn_primero_actionPerformed();
        }

        private void jbtn_insertar() throws SQLException {
            System.out.println("Action del boton jbtn_insertar");
            modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
            modelAgenda.setEmail(viewAgenda.jtf_email.getText());
            modelAgenda.insertarRegistro(modelAgenda.getNombre(),modelAgenda.getEmail());
            JOptionPane.showMessageDialog(viewAgenda, "Registro guardado correctamente");
            jbtn_primero_actionPerformed();
        }

        private void jbtn_modificar() throws SQLException {
            System.out.println("Action del boton jbtn_modificar");
            modelAgenda.modificarRegistro(viewAgenda.jtf_nombre.getText(), viewAgenda.jtf_email.getText());
            JOptionPane.showMessageDialog(viewAgenda, "Registro actualizado correctamente");
            jbtn_primero_actionPerformed();
        }

        private void jbtn_nuevo() {
            System.out.println("Action del boton jbtn_nuevo");
            modelAgenda.setEmail(null);
            modelAgenda.setNombre(null);
            viewAgenda.jtf_email.setText(modelAgenda.getEmail());
            viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
            
        }
}
