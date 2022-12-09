package com.example.contactosdb.entidades;

public class Contactos
{
    private int id;
    private String matricula;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String fechanacimiento;

    public Contactos()
    {

    }


    public Contactos(int id, String matricula,String nombre, String apellidos, String sexo, String fechanacimiento)
    {
        this.id = id;
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechanacimiento = fechanacimiento;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public String getSexo()
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    public String getFechanacimiento()
    {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento)
    {
        this.fechanacimiento = fechanacimiento;
    }
}
