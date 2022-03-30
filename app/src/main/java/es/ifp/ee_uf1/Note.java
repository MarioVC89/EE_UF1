package es.ifp.ee_uf1;

public class Note {

    protected int id;
    protected String nota;


    public Note(int id, String nota)
    {
        this.id=id;
        this.nota=nota;
    }

    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public String getNota()
    {
        return this.nota;
    }
    public void setNota(String nota)
    {
        this.nota=nota;
    }
}
