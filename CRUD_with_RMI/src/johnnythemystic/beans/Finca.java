package johnnythemystic.beans;

import java.util.ArrayList;

public class Finca
{
  private int id;
  private String name;
  private int hectareas;
  private ArrayList<Cultivo> cultivos = new ArrayList<Cultivo>();
  
  public int getId() { return id; }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getName() { return name; }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getHectareas() { return hectareas; }
  
  public void setHectareas(int hectareas) {
    this.hectareas = hectareas;
  }
  
  public ArrayList<Cultivo> getCultivos() { return cultivos; }
  
  public void setCultivos(ArrayList<Cultivo> cultivos) {
    this.cultivos = cultivos;
  }
  
  public Finca(int id, String name, int hectareas) {
    this.id = id;
    this.name = name;
    this.hectareas = hectareas;
  }
  
  public void addCultivo(Cultivo cultivo) { cultivos.add(cultivo); }
  

  public void removeCultivo(Cultivo cultivo) { cultivos.remove(cultivo); }
  
  public void updateCultivo(int idCultivo, Cultivo newCultivo) {
    Cultivo cultivo = null;
    for (Cultivo temp : cultivos) {
      if (temp.getId() == idCultivo) {
        cultivo = temp;
      }
    }
    if (cultivo != null) {
      cultivos.remove(cultivo);
      cultivos.add(newCultivo);
    }
  }
  
  public void removeCultivo(int idCultivo) { Cultivo cultivo = null;
    for (Cultivo temp : cultivos) {
      if (temp.getId() == idCultivo) {
        cultivo = temp;
      }
    }
    
    if (cultivo != null) cultivos.remove(cultivo);
  }
  
  public Cultivo getCultivo(int idCultivo) {
    for (Cultivo cultivo : cultivos) {
      if (cultivo.getId() == idCultivo) {
        return cultivo;
      }
    }
    return null;
  }
  

  public Finca() {}
  
  public String toString()
  {
    return name;
  }
}
