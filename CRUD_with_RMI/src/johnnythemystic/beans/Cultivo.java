package johnnythemystic.beans;


public class Cultivo
{
  private int id;
  private String description;
  
  public Cultivo() {}
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) { this.id = id; }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) { this.description = description; }
  
  public Cultivo(int id, String description)
  {
    this.id = id;
    this.description = description;
  }
  

  public String toString()
  {
    return description;
  }
}