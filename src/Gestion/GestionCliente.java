/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Gestion;

import CapaDatos.Conexion;
import Clases.Cliente;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.sql.ResultSet;


/**
 * @author omar
 */
public class GestionCliente implements IGestion
{
    private Cliente cliente = new Cliente ("","","",0);

    public GestionCliente() 
    {
        Conexion.setCadena("jdbc:mysql://localhost/facturacion");
        Conexion.setUsuario("root");
        Conexion.setClave("");
        
    }

    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente (Cliente cliente){
        this.cliente=cliente;
    }
    
    
    @Override
    public void Grabar() throws SQLException 
    {        
        try
        {
            Conexion.GetInstancia().Conectar();
            Conexion.GetInstancia().Ejecutar("insert into cliente (cedula, nombre, direccion, cupo) values ('"+cliente.getCedula()+"','"+cliente.getNombre()+"','"+cliente.getDireccion()+"', "+cliente.getCupo()+")");
            Conexion.GetInstancia().Desconectar();    
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
        
    }

    @Override
    public void Modificar() throws SQLException 
    {
        try
        {
            Conexion.GetInstancia().Conectar();
            Conexion.GetInstancia().Ejecutar("UPDATE cliente SET  nombre = '"+cliente.getNombre()+"', direccion = '"+cliente.getDireccion()+"', cupo = '"+cliente.getCupo()+"'  WHERE cedula = '"+cliente.getCedula()+"'");
            Conexion.GetInstancia().Desconectar();    
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    }

    @Override
    public void Nuevo() throws SQLException 
    {    
        cliente.setCedula("");
        cliente.setNombre("");
        cliente.setDireccion("");
        cliente.setCupo(0);
    }

    @Override
    public void Eliminar() throws SQLException 
    {
        try
        {
            Conexion.GetInstancia().Conectar();
            Conexion.GetInstancia().Ejecutar("DELETE FROM cliente WHERE cedula = '"+cliente.getCedula()+"'");
            Conexion.GetInstancia().Desconectar();    
        }
        catch(SQLException ex)
        {
            throw ex;            
        }
    }

    @Override
    public void Consultar() throws SQLException 
    { 
        try{
            Conexion.GetInstancia().Conectar();
            ResultSet consulta = Conexion.GetInstancia().EjecutarConsulta("SELECT cedula, nombre, direccion, cupo FROM cliente WHERE cedula = '"+cliente.getCedula()+"'");       
            while(consulta.next())
            {
               cliente.setCedula(consulta.getString(1));
               cliente.setNombre(consulta.getString(2));
               cliente.setDireccion(consulta.getString(3));
               cliente.setCupo(consulta.getDouble(4));
            }
            Conexion.GetInstancia().Desconectar();
           }
        catch(SQLException e)
        {
            throw e;
        }
    }
    
}
