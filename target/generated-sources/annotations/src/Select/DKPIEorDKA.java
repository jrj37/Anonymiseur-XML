package src.Select;

import DataGeneration.CarteSecu;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class DKPIEorDKA {
    private String Secu;
    public void ParseXSI(Document document, String getGenereSecu) {
        try {
            // Récupération des balises Identite
            NodeList identiteList = document.getElementsByTagName("Identite");

            // Parcours des balises Identite
            for (int i = 0; i < identiteList.getLength(); i++) {
                Element identite = (Element) identiteList.item(i);
                // Vérification du type xsi:type
                if (identite.hasAttribute("xsi:type")) {
                    String typeIdentite = identite.getAttribute("xsi:type");
                    if (typeIdentite.equals("Simple_NIR")){
                            identite.setTextContent(getGenereSecu);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getSecu(){
        return Secu;
    }
    public void GenereSecu(){
        CarteSecu carteSecu=new CarteSecu();
        Secu= carteSecu.RandomNumSecu();
    }
}
